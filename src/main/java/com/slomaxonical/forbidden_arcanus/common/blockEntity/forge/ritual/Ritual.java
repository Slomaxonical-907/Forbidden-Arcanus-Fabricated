package com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.ritual;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.HephaestusForgeBlockEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Ritual <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.tile.forge.ritual.Ritual
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-07-09
 */
public class Ritual {

    private final Identifier name;

    private final Map<Integer, Ingredient> inputs;
    private final ItemStack hephaestusForgeItem;
    private final ItemStack result;

    private final RitualEssences essences;

    private final Identifier outerTexture;
    private final Identifier innerTexture;

    private final PedestalType pedestalType;

    private final int time;

    public Ritual(Identifier name, Map<Integer, Ingredient> inputs, ItemStack hephaestusForgeItem, ItemStack result, RitualEssences essences, Identifier outerTexture, Identifier innerTexture, int time) {
        this.name = name;
        this.inputs = inputs;
        this.hephaestusForgeItem = hephaestusForgeItem;
        this.result = result;
        this.essences = essences;
        this.outerTexture = outerTexture;
        this.innerTexture = innerTexture;
        this.pedestalType = PedestalType.DARKSTONE_PEDESTAL;
        this.time = time;
    }

    public boolean canStart(List<ItemStack> inputs, HephaestusForgeBlockEntity blockEntity) {
        if (!this.getEssences().checkEssences(blockEntity)) {
            return false;
        }
        return this.checkIngredients(inputs, blockEntity);
    }

    public boolean checkIngredients(List<ItemStack> list, HephaestusForgeBlockEntity blockEntity) {
        List<ItemStack> ingredients = new ArrayList<>(list);

        for (Ingredient ingredient : this.getInputs()) {
            boolean foundStack = false;

            for (ItemStack input : ingredients) {
                if (ingredient.test(input)) {
                    ingredients.remove(input);

                    foundStack = true;
                    break;
                }
            }

            if (!foundStack) {
                return false;
            }
        }

        ItemStack stack = blockEntity.getStack(4);

        if (!ingredients.isEmpty()) {
            return false;
        }

        return this.getHephaestusForgeItem().isEmpty() ? stack.isEmpty() : this.getHephaestusForgeItem().isItemEqual(stack); //todo:unsure if isItemEqual is basically the same
    }

    public Identifier getName() {
        return this.name;
    }

    public Ingredient getInput(int slot) {
        return this.inputs.get(slot);
    }

    public List<Ingredient> getInputs() {
        return new ArrayList<>(this.inputs.values());
    }

    public ItemStack getHephaestusForgeItem() {
        return this.hephaestusForgeItem;
    }

    public ItemStack getResult() {
        return this.result.copy();
    }

    public RitualEssences getEssences() {
        return this.essences;
    }

    public Identifier getOuterTexture() {
        return this.outerTexture;
    }

    public Identifier getInnerTexture() {
        return this.innerTexture;
    }

    public PedestalType getPedestalType() {
        return this.pedestalType;
    }

    public int getTime() {
        return this.time;
    }

    public void serializeToNetwork(PacketByteBuf buffer) {
        buffer.writeIdentifier(this.name);
        buffer.writeMap(this.inputs, PacketByteBuf::writeVarInt, (packetByteBuf, ingredient) -> ingredient.write(packetByteBuf));
        buffer.writeItemStack(this.hephaestusForgeItem);
        buffer.writeItemStack(this.result);
        this.essences.serializeToNetwork(buffer);
        buffer.writeIdentifier(this.outerTexture);
        buffer.writeIdentifier(this.innerTexture);
        buffer.writeVarInt(this.time);
    }

    public static Ritual fromNetwork(PacketByteBuf buffer) {
        Identifier name = buffer.readIdentifier();
        Map<Integer, Ingredient> inputs = buffer.readMap(PacketByteBuf::readVarInt, Ingredient::fromPacket);
        ItemStack hephaestusForgeItem = buffer.readItemStack();
        ItemStack result = buffer.readItemStack();
        RitualEssences essences = RitualEssences.fromNetwork(buffer);
        Identifier outerTexture = buffer.readIdentifier();
        Identifier innerTexture = buffer.readIdentifier();
        int time = buffer.readVarInt();

        return new Ritual(name, inputs, hephaestusForgeItem, result, essences, outerTexture, innerTexture, time);
    }

    public enum PedestalType {
        DARKSTONE_PEDESTAL(BlockRegistry.DARKSTONE_PEDESTAL),
        ARCANE_DARKSTONE_PEDESTAL(BlockRegistry.ARCANE_DARKSTONE_PEDESTAL);

        private final Block block;

        PedestalType(Block block) {
            this.block = block;
        }

        public Block getBlock() {
            return block;
        }
    }
}
