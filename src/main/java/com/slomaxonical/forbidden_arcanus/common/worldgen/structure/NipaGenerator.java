package com.slomaxonical.forbidden_arcanus.common.worldgen.structure;


import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.world.StructureRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class NipaGenerator {

    public static final Identifier NIPA = new Identifier(ForbiddenArcanus.MOD_ID, "nipa");
    public static final Identifier NIPA_FLOATING = new Identifier(ForbiddenArcanus.MOD_ID, "nipa_floating");

    public static class Piece extends SimpleStructurePiece {

        private final boolean floating;

        public Piece(StructureManager structureManager, Identifier templateName, BlockPos templatePosition, BlockRotation BlockRotation, boolean floating) {
            super(StructureRegistry.NIPA_PIECE, 0, structureManager, templateName, templateName.toString(), makeSettings(BlockRotation), templatePosition);
            this.floating = floating;
        }

        public Piece(StructureManager manager, NbtCompound tag) {
            super(StructureRegistry.NIPA_PIECE, tag, manager, (Identifier) -> {
                return makeSettings(BlockRotation.valueOf(tag.getString("Rot")));
            });
            this.floating = tag.getBoolean("Floating");
        }

        private static StructurePlacementData makeSettings(BlockRotation BlockRotation) {
            return new StructurePlacementData().setRotation(BlockRotation).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt) {
            super.writeNbt(context, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
            nbt.putBoolean("Floating", this.floating);
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {

        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pos) {
            int i = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
            this.pos = new BlockPos(this.pos.getX(), this.floating ? i + 60 : i - 3, this.pos.getZ());

            super.generate(world, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, pos);
        }

    }
}
