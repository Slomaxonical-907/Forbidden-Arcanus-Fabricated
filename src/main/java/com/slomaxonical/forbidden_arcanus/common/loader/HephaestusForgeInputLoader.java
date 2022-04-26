package com.slomaxonical.forbidden_arcanus.common.loader;

import com.google.gson.*;
import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.inventory.InputType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HephaestusForgeInputLoader extends JsonDataLoader {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static EnumMap<InputType, Map<Item, InputData>> inputs = new EnumMap<>(InputType.class);

    public HephaestusForgeInputLoader() {
        super(GSON, "hephaestus_forge/inputs");
    }

    @Override
    protected void apply(@Nonnull Map<Identifier, JsonElement> object, @Nonnull ResourceManager resourceManager, @Nonnull Profiler profiler) {
        inputs.clear();

        for(Map.Entry<Identifier, JsonElement> entry : object.entrySet()) {
            Identifier id = entry.getKey();

            if (id.getPath().startsWith("_")) {
                continue;
            }

            try {
                InputType type = InputType.valueOf(JsonHelper.getString(entry.getValue().getAsJsonObject(), "type").toUpperCase(Locale.ROOT));
                InputData data = this.deserializeInput(id, entry.getValue().getAsJsonObject());

                inputs.computeIfAbsent(type, (inputType) -> new HashMap<>());
                inputs.get(type).put(data.stack().getItem(), data);
            } catch (IllegalArgumentException | JsonParseException jsonParseException) {
                ForbiddenArcanus.LOGGER.error("Parsing error loading hephaestus forge input {}", id, jsonParseException);
            }
        }
    }

    private static Map<InputType, Map<Item, InputData>> getInputs() {
        return inputs;
    }

    private InputData deserializeInput(Identifier input, JsonObject jsonObject) {
        Identifier id = new Identifier(JsonHelper.getString(jsonObject, "item"));
//        Item item = ForgeRegistries.ITEMS.getValue(id);
        Item item = Registry.ITEM.get(id);

        if (item == null) {
            ForbiddenArcanus.LOGGER.error("Item " + id + " does not exist.");
            throw new IllegalArgumentException();
        }

        int value = JsonHelper.getInt(jsonObject, "value");

        return new InputData(new ItemStack(item), value);
    }

    public static boolean isValidInput(InputType inputType, ItemStack stack) {
        return getInputs().containsKey(inputType) && HephaestusForgeInputLoader.getInputs().get(inputType).containsKey(stack.getItem());
    }

    public static InputData getInputData(InputType inputType, ItemStack stack) {
        return getInputs().get(inputType).get(stack.getItem());
    }

    public static void setInputs(EnumMap<InputType, Map<Item, InputData>> inputTypeMapMap) {
        HephaestusForgeInputLoader.inputs = inputTypeMapMap;
    }

    public record InputData(ItemStack stack, int value) {

        public void serializeToNetwork(PacketByteBuf buffer) {
            buffer.writeItemStack(this.stack);
            buffer.writeVarInt(this.value);
        }

        public static InputData fromNetwork(PacketByteBuf buffer) {
            return new InputData(buffer.readItemStack(), buffer.readVarInt());
        }
    }
}
