package com.slomaxonical.forbidden_arcanus.core.systems.multiblock;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MultiBlockStructure {
    public final ArrayList<StructurePiece> structurePieces;
    public MultiBlockStructure(ArrayList<StructurePiece> structurePieces) {
        this.structurePieces = structurePieces;
    }
    public boolean canPlace(BlockPos core, World level)
    {
        return structurePieces.stream().allMatch(p -> p.canPlace(core, level));
    }
    public void place(BlockPos core, World level)
    {
        structurePieces.forEach(s -> s.place(core, level));
    }
    public static MultiBlockStructure of(StructurePiece... pieces)
    {
        return new MultiBlockStructure(new ArrayList<>(List.of(pieces)));
    }

    public static class StructurePiece
    {
        public final Vec3i offset;
        public final BlockState state;

        public StructurePiece(int xOffset, int yOffset, int zOffset, BlockState state) {
            this.offset = new Vec3i(xOffset, yOffset, zOffset);
            this.state = state;
        }
        public boolean canPlace(BlockPos core, World level)
        {
            BlockPos pos = core.add(offset);
            BlockState existingState = level.getBlockState(pos);
            return existingState.getMaterial().isReplaceable();
        }
        public void place(BlockPos core, World level)
        {
            BlockPos pos = core.add(offset);
            level.setBlockState(pos, state, 3);
            if (level.getBlockEntity(pos) instanceof MultiBlockComponentEntity component)
            {
                component.corePos = core;
            }
        }
    }
}
