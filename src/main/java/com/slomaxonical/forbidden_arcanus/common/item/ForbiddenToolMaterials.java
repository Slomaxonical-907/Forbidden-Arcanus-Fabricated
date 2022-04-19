package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ForbiddenToolMaterials implements ToolMaterial {

    DRACO_ARCANUS(4, 2661, 12.0F, 7.0F, 20, Ingredient.ofItems(ItemRegistry.DRAGON_SCALE)),
    ARCANE_GOLDEN(3, 1861, 9.0F, 3.5F, 26, Ingredient.ofItems(ItemRegistry.ARCANE_GOLD_INGOT)),
    REINFORCED_ARCANE_GOLDEN(3, 2561, 9.0F, 3.5F, 26, Ingredient.ofItems(ItemRegistry.STELLARITE_PIECE)),
    BONE(1, 131, 4.0F, 1.0F, 5, Ingredient.ofItems(Items.BONE)),
    SLIMEC(3, 2061, 13.0F, 2.5F, 20, Ingredient.ofItems(ItemRegistry.ARCANE_GOLD_INGOT)),
    MYSTICAL_DAGGER(1, 561, 4.0F, 1.0F, 5, Ingredient.ofItems(ItemRegistry.DARK_RUNE));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;

    ForbiddenToolMaterials(int level, int uses, float speed, float damage, int enchantmentValue, Ingredient repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }
    @Override
    public int getDurability() {
        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
