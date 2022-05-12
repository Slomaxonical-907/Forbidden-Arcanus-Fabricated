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

public class NipaFeature extends SimpleValhelsiaStructure<NipaConfig> {

    public NipaFeature(Codec<NipaConfig> codec) {
        super(codec, StructureGeneratorFactory.simple(StructureGeneratorFactory.checkForBiomeOnTop(Heightmap.Type.WORLD_SURFACE_WG), NipaFeature::generatePieces), "nipa");
    }

    private static void generatePieces(StructurePiecesCollector piecesCollector, StructurePiecesGenerator.Context<NipaConfig> context) {
        BlockPos pos = new BlockPos(context.chunkPos().getStartPos().getX(), 90, context.chunkPos().getStartPos().getZ());
        Random random = context.random();
        boolean floating = random.nextFloat() <= context.config().getFloatingProbability();

        piecesCollector.addPiece(new NipaGenerator.Piece(context.structureManager(), floating ? NipaGenerator.NIPA_FLOATING : NipaGenerator.NIPA, pos, BlockRotation.random(random), floating));
    }

    @Override
    public GenerationStep.Feature getGenerationStep() {
        return GenerationStep.Feature.SURFACE_STRUCTURES;
    }
}
