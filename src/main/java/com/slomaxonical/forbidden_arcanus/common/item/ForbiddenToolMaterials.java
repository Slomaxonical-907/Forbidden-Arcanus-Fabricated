package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ForbiddenToolMaterials implements ToolMaterial {

    DRACO_ARCANUS(4, 2661, 12.0F, 7.0F, 20, ()-> Ingredient.ofItems(ItemRegistry.DRAGON_SCALE)),
    ARCANE_GOLDEN(3, 1861, 9.0F, 3.5F, 26, ()-> Ingredient.ofItems(ItemRegistry.ARCANE_GOLD_INGOT)),
    REINFORCED_ARCANE_GOLDEN(3, 2561, 9.0F, 3.5F, 26, ()-> Ingredient.ofItems(ItemRegistry.STELLARITE_PIECE)),
    BONE(1, 131, 4.0F, 1.0F, 5, ()-> Ingredient.ofItems(Items.BONE)),
    SLIMEC(3, 2061, 13.0F, 2.5F, 20, ()-> Ingredient.ofItems(ItemRegistry.ARCANE_GOLD_INGOT)),
    MYSTICAL_DAGGER(1, 561, 4.0F, 1.0F, 5, ()-> Ingredient.ofItems(ItemRegistry.DARK_RUNE));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private ForbiddenToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<Ingredient>(repairIngredient);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
