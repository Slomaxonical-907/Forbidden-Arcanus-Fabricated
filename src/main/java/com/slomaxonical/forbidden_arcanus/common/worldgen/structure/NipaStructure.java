package com.slomaxonical.forbidden_arcanus.common.worldgen.structure;

import com.mojang.serialization.Codec;
import com.slomaxonical.forbidden_arcanus.common.worldgen.structure.config.NipaConfig;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.structure.StructurePiecesGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.valhelsia.valhelsia_core.common.world.SimpleValhelsiaStructure;

import java.util.Random;

public class NipaStructure extends SimpleValhelsiaStructure<NipaConfig> {

    public NipaStructure(Codec<NipaConfig> codec) {
        super(codec, StructureGeneratorFactory.simple(StructureGeneratorFactory.checkForBiomeOnTop(Heightmap.Type.WORLD_SURFACE_WG), NipaStructure::generatePieces), "nipa");
    }

    private static void generatePieces(StructurePiecesCollector piecesCollector, StructurePiecesGenerator.Context<NipaConfig> context) {
        BlockPos pos = new BlockPos(context.chunkPos().getStartPos().getX(), 90, context.chunkPos().getStartPos().getZ());
        Random random = context.random();
        boolean floating = random.nextFloat() <= context.config().getFloatingProbability();

        piecesCollector.addPiece(new NipaPieces.Piece(context.structureManager(), floating ? NipaPieces.NIPA_FLOATING : NipaPieces.NIPA, pos, BlockRotation.random(random), floating));
    }

    @Override
    public GenerationStep.Feature getGenerationStep() {
        return GenerationStep.Feature.SURFACE_STRUCTURES;
    }
//    @Override
//    public StructureFeatureConfiguration getFeatureConfiguration() {
//        return ModStructures.SeparationSettings.NIPA;
//    }

//    public static class Start extends StructureStart<NipaConfig> {
//
//        public Start(StructureFeature<NipaConfig> p_163595_, ChunkPos p_163596_, int p_163597_, long p_163598_) {
//            super(p_163595_, p_163596_, p_163597_, p_163598_);
//        }
//
//        @Override
//        public void generatePieces(RegistryAccess registryAccess, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, NipaConfig config, LevelHeightAccessor level) {
//            BlockPos pos = new BlockPos(chunkPos.getWorldPosition().getX(), 90, chunkPos.getWorldPosition().getZ());
//
//            boolean floating = random.nextFloat() <= config.getFloatingProbability();
//            this.pieces.add(new NipaPieces.Piece(structureManager, floating ? NipaPieces.NIPA_FLOATING : NipaPieces.NIPA, pos, Rotation.getRandom(this.random), floating));
//
//            this.getBoundingBox();
//        }
//    }
}
