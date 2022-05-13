package com.slomaxonical.forbidden_arcanus.common.loader;

import com.google.gson.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.ritual.Ritual;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.ritual.RitualEssences;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.recipe.Ingredient;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RitualLoader extends JsonDataLoader {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static Map<Identifier, Ritual> rituals = new HashMap<>();

    public RitualLoader() {
        super(GSON, "hephaestus_forge/rituals");
    }

    @Override
    protected void apply(@Nonnull Map<Identifier, JsonElement> object, @Nonnull ResourceManager resourceManager, @Nonnull Profiler profiler) {
        rituals.clear();

        for(Map.Entry<Identifier, JsonElement> entry : object.entrySet()) {
            Identifier resourceLocation = entry.getKey();

            if (resourceLocation.getPath().startsWith("_")) {
                continue;
            }

            try {
                Ritual ritual = this.deserializeRitual(resourceLocation, entry.getValue().getAsJsonObject());
                if (ritual != null) {
                    rituals.put(resourceLocation, ritual);
                }
            } catch (IllegalArgumentException | JsonParseException jsonParseException) {
                ForbiddenArcanus.LOGGER.error("Parsing error loading hephaestus forge input {}", resourceLocation, jsonParseException);
            }
        }
    }
    public static ItemStack getItemStack(JsonObject json) //todo hope for the best
    {
        String itemName = JsonHelper.getString(json, "item");
        Identifier itemKey = new Identifier(itemName);

        if (!Registry.ITEM.containsId(itemKey))
            throw new JsonSyntaxException("Unknown item '" + itemName + "'");

        Item item = Registry.ITEM.get(itemKey);

//        if (disallowsAirInRecipe && item == Items.AIR)
//            throw new JsonSyntaxException("Invalid item: " + itemName);

        return new ItemStack(item, JsonHelper.getInt(json, "count", 1));
    }
    public static Ritual getRitual(Identifier resourceLocation) {
        return rituals.get(resourceLocation);
    }

    public static List<Ritual> getRituals() {
        List<Ritual> rituals = new ArrayList<>();
        for (Map.Entry<Identifier, Ritual> entry : RitualLoader.rituals.entrySet()) {
            rituals.add(entry.getValue());
        }
        return rituals;
    }

    public static void setRituals(Map<Identifier, Ritual> rituals) {
        RitualLoader.rituals = rituals;
    }

    private Ritual deserializeRitual(Identifier name, JsonObject jsonObject) {
        ItemStack hephaestusForgeInput = ItemStack.EMPTY;

        if (jsonObject.has("hephaestus_forge_item")) {
            hephaestusForgeInput = new ItemStack(this.deserializeItem(new Identifier(JsonHelper.getString(jsonObject, "hephaestus_forge_item"))));
        }

        try {
            System.out.println(StringNbtReader.parse(GSON.toJson(jsonObject.get("result"))));
            ItemStack result = getItemStack(JsonHelper.getObject(jsonObject, "result"));

            return new Ritual(name, this.deserializeInputs(jsonObject), hephaestusForgeInput, result, this.deserializeEssences(jsonObject), new Identifier(ForbiddenArcanus.MOD_ID, "textures/effect/magic_circle/absolute.png"), new Identifier(ForbiddenArcanus.MOD_ID, "textures/effect/magic_circle/inner_protection.png"), 1200);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Map<Integer, Ingredient> deserializeInputs(JsonObject jsonObject) {
        Map<Integer, Ingredient> inputs = new HashMap<>();
        JsonArray jsonArray = jsonObject.getAsJsonArray("inputs");

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject input = jsonArray.get(i).getAsJsonObject();
            ItemStack stack = getItemStack(input);
            int slot = input.get("slot").getAsInt();

            if (inputs.containsKey(slot)) {
                throw new IllegalStateException("Slot " + slot + " was already assigned.");
            } else {
                inputs.put(slot, Ingredient.ofStacks(stack));
            }
        }

        return inputs;
    }

    private Item deserializeItem(Identifier name) {

        Item item = Registry.ITEM.get(name); //todo

        if (item == null) {
            ForbiddenArcanus.LOGGER.error("Item " + name + " does not exist.");
            throw new IllegalArgumentException();
        }
        return item;
    }

    private RitualEssences deserializeEssences(JsonObject jsonObject) {
        JsonObject essences = jsonObject.get("essences").getAsJsonObject();

        int aureal = JsonHelper.getInt(essences, "aureal", 0);
        int corruption = JsonHelper.getInt(essences, "corruption", 0);
        int souls = JsonHelper.getInt(essences, "souls", 0);
        int blood = JsonHelper.getInt(essences, "blood", 0);
        int experience = JsonHelper.getInt(essences, "experience", 0);

        return new RitualEssences(aureal, corruption, souls, blood, experience);
    }
}
