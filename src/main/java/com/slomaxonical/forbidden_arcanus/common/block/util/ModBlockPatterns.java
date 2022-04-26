package com.slomaxonical.forbidden_arcanus.common.block.util;


import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.function.MaterialPredicate;

public class ModBlockPatterns {

    public static final BlockPattern HEPHAESTUS_PATTERN = BlockPatternBuilder.start()
            .aisle("***~~~***", "***PPP***")
            .aisle("*~~~~~~~*", "*PPPAPPP*")
            .aisle("*~~~~~~~*", "*PAPPPAP*")
            .aisle("~~~~~~~~~", "PPPPCPPPP")
            .aisle("~~~~^~~~~", "PAPCACPAP")
            .aisle("~~~~~~~~~", "PPPPCPPPP")
            .aisle("*~~~~~~~*", "*PAPPPAP*")
            .aisle("*~~~~~~~*", "*PPPAPPP*")
            .aisle("***~~~***", "***PPP***")
            .where('^', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SMITHING_TABLE)))
            .where('A', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegistry.ARCANE_CHISELED_POLISHED_DARKSTONE)))
            .where('C', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegistry.CHISELED_ARCANE_POLISHED_DARKSTONE)))
            .where('P', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegistry.POLISHED_DARKSTONE)))
            .where('~', CachedBlockPosition.matchesBlockState(MaterialPredicate.create(Material.AIR)))
            .where('*', CachedBlockPosition.matchesBlockState(BlockStatePredicate.ANY))
            .build();

    public static final BlockPattern BASE_HEPHAESTUS_PATTERN = BlockPatternBuilder.start()
            .aisle("***PPP***")
            .aisle("*PPPAPPP*")
            .aisle("*PAPPPAP*")
            .aisle("PPPPCPPPP")
            .aisle("PAPCACPAP")
            .aisle("PPPPCPPPP")
            .aisle("*PAPPPAP*")
            .aisle("*PPPAPPP*")
            .aisle("***PPP***")
            .where('A', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegistry.ARCANE_CHISELED_POLISHED_DARKSTONE)))
            .where('C', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegistry.CHISELED_ARCANE_POLISHED_DARKSTONE)))
            .where('P', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegistry.POLISHED_DARKSTONE)))
            .where('~', CachedBlockPosition.matchesBlockState(MaterialPredicate.create(Material.AIR)))
            .where('*', CachedBlockPosition.matchesBlockState(BlockStatePredicate.ANY))
            .build();

    public static final BlockPattern ARCANE_CRYSTAL_OBELISK_PATTERN = BlockPatternBuilder.start()
            .aisle("#", "#", "X")
            .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegistry.ARCANE_CRYSTAL_BLOCK)))
            .where('X', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegistry.ARCANE_POLISHED_DARKSTONE)))
            .build();
}
