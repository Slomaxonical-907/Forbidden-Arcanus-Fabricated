package com.slomaxonical.forbidden_arcanus.common.item;


import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * Mod Armor Materials <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.item.ModArmorMaterials
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-12-11
 */
public enum ModArmorMaterials implements ArmorMaterial {
    DRACO_ARCANUS("draco_arcanus", 40, new int[]{6, 8, 10, 6}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0F, 0.1F, Ingredient.ofItems(ItemRegistry.DRAGON_SCALE)),
    TYR("tyr", 50, new int[]{8, 10, 12, 7}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 4.0F, 0.2F, Ingredient.ofItems(ItemRegistry.GOLDEN_DRAGON_SCALE, ItemRegistry.AQUATIC_DRAGON_SCALE)),
    MORTEM("mortem", 40, new int[]{1, 4, 5, 1}, 6, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0F, 0.0F, Ingredient.ofItems(ItemRegistry.CLOTH)),
    ARCANE_GOLD("arcane_gold", 38, new int[]{4, 6, 8, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5F, 0.0F, Ingredient.ofItems(ItemRegistry.ARCANE_GOLD_INGOT));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final Identifier name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairIngredient;

    ModArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Ingredient ingredient) {
        this.name = new Identifier(ForbiddenArcanus.MOD_ID, name);
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = ingredient;
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getEntitySlotId()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return this.slotProtections[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantmentValue;
    }

    @Nonnull
    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    @Nonnull
    @Override
    public String getName() {
        return this.name.toString();
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
