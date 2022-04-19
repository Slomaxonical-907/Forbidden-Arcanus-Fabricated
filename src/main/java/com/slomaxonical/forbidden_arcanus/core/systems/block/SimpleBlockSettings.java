package com.slomaxonical.forbidden_arcanus.core.systems.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.ToIntFunction;

public class SimpleBlockSettings extends FabricBlockSettings {
    public boolean needsPickaxe;
    public boolean needsAxe;
    public boolean needsShovel;
    public boolean needsHoe;

    public boolean needsStone;
    public boolean needsIron;
    public boolean needsDiamond;

    public boolean ignoreLootDatagen;
    public boolean ignoreBlockStateDatagen;

    public SimpleBlockSettings(Material material, MapColor mapColor) {
        super(material, mapColor);
    }
    public SimpleBlockSettings(AbstractBlock.Settings settings) {
        super(settings);
    }
    public SimpleBlockSettings ignoreLootDatagen()
    {
        ignoreLootDatagen = true;
        return this;
    }
    public SimpleBlockSettings ignoreBlockStateDatagen()
    {
        ignoreBlockStateDatagen = true;
        return this;
    }
    public SimpleBlockSettings needsPickaxe()
    {
        needsPickaxe = true;
        return this;
    }
    public SimpleBlockSettings needsAxe()
    {
        needsAxe = true;
        return this;
    }
    public SimpleBlockSettings needsShovel()
    {
        needsShovel = true;
        return this;
    }
    public SimpleBlockSettings needsHoe()
    {
        needsHoe = true;
        return this;
    }

    public SimpleBlockSettings needsStone()
    {
        needsStone = true;
        return this;
    }
    public SimpleBlockSettings needsIron()
    {
        needsIron = true;
        return this;
    }
    public SimpleBlockSettings needsDiamond()
    {
        needsDiamond = true;
        return this;
    }

    @Override
    public SimpleBlockSettings noCollision() {
        return (SimpleBlockSettings)super.noCollision();
    }

    @Override
    public SimpleBlockSettings nonOpaque() {
        return (SimpleBlockSettings)super.nonOpaque();
    }

    @Override
    public SimpleBlockSettings slipperiness(float p_60912_) {
        return (SimpleBlockSettings)super.slipperiness(p_60912_);
    }

    @Override
    public SimpleBlockSettings velocityMultiplier(float p_60957_) {
        return (SimpleBlockSettings)super.velocityMultiplier(p_60957_);
    }

    @Override
    public SimpleBlockSettings jumpVelocityMultiplier(float p_60968_) {
        return (SimpleBlockSettings)super.jumpVelocityMultiplier(p_60968_);
    }

    @Override
    public SimpleBlockSettings sounds(BlockSoundGroup p_60919_) {
        return (SimpleBlockSettings)super.sounds(p_60919_);
    }

    @Override
    public SimpleBlockSettings luminance(ToIntFunction<BlockState> p_60954_) {
        return (SimpleBlockSettings)super.luminance(p_60954_);
    }

    @Override
    public SimpleBlockSettings strength(float p_60914_, float p_60915_) {
        return (SimpleBlockSettings)super.strength(p_60914_, p_60915_);
    }

    @Override
    public SimpleBlockSettings breakInstantly() {
        return (SimpleBlockSettings)super.breakInstantly();
    }

    @Override
    public SimpleBlockSettings strength(float p_60979_) {
        return (SimpleBlockSettings)super.strength(p_60979_);
    }

    @Override
    public SimpleBlockSettings ticksRandomly() {
        return (SimpleBlockSettings)super.ticksRandomly();
    }

    @Override
    public SimpleBlockSettings dynamicBounds() {
        return (SimpleBlockSettings)super.dynamicBounds();
    }

    @Override
    public SimpleBlockSettings dropsNothing() {
        return (SimpleBlockSettings)super.dropsNothing();
    }

    @Override
    public SimpleBlockSettings dropsLike(Block p_60917_) {
        ignoreLootDatagen();
        return (SimpleBlockSettings)super.dropsLike(p_60917_);
    }

//    @Override
//    public SimpleBlockSettings lootFrom(Supplier<? extends Block> blockIn) {
//        ignoreLootDatagen();
//        return (SimpleBlockSettings) super.lootFrom(blockIn);
//    }

    @Override
    public SimpleBlockSettings air() {
        return (SimpleBlockSettings)super.air();
    }

    @Override
    public SimpleBlockSettings allowsSpawning(AbstractBlock.TypedContextPredicate<EntityType<?>> p_60923_) {
        return (SimpleBlockSettings)super.allowsSpawning(p_60923_);
    }

    @Override
    public SimpleBlockSettings solidBlock(AbstractBlock.ContextPredicate p_60925_) {
        return (SimpleBlockSettings)super.solidBlock(p_60925_);
    }

    @Override
    public SimpleBlockSettings suffocates(AbstractBlock.ContextPredicate p_60961_) {
        return (SimpleBlockSettings)super.suffocates(p_60961_);
    }

    @Override
    public SimpleBlockSettings blockVision(AbstractBlock.ContextPredicate p_60972_) {
        return (SimpleBlockSettings)super.blockVision(p_60972_);
    }

    @Override
    public SimpleBlockSettings postProcess(AbstractBlock.ContextPredicate p_60983_) {
        return (SimpleBlockSettings)super.postProcess(p_60983_);
    }

    @Override
    public SimpleBlockSettings emissiveLighting(AbstractBlock.ContextPredicate p_60992_) {
        return (SimpleBlockSettings)super.emissiveLighting(p_60992_);
    }

    @Override
    public SimpleBlockSettings requiresTool() {
        return (SimpleBlockSettings)super.requiresTool();
    }

    @Override
    public SimpleBlockSettings mapColor(MapColor p_155950_) {
        return (SimpleBlockSettings)super.mapColor(p_155950_);
    }

    @Override
    public SimpleBlockSettings hardness(float p_155955_) {
        return (SimpleBlockSettings)super.hardness(p_155955_);
    }

    @Override
    public SimpleBlockSettings resistance(float p_155957_) {
        return (SimpleBlockSettings)super.resistance (p_155957_);
    }
}
