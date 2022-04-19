package com.slomaxonical.forbidden_arcanus.core.systems.multiblock;

import com.slomaxonical.forbidden_arcanus.core.systems.blockEntity.SimpleBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;

public abstract class MultiBlockCoreEntity extends SimpleBlockEntity {

    ArrayList<BlockPos> componentPositions = new ArrayList<>();

    public final MultiBlockStructure structure;

    public MultiBlockCoreEntity(BlockEntityType<?> type, MultiBlockStructure structure, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.structure = structure;
        getStructure().structurePieces.forEach(p -> {
            Vec3i offset = p.offset;
            componentPositions.add(pos.add(offset));
        });
    }

    public MultiBlockStructure getStructure() {
        return structure;
    }

    @Override
    public void onBreak() {
        componentPositions.forEach(p -> {
            if (world.getBlockEntity(p) instanceof MultiBlockComponentEntity) {
                world.breakBlock(p, false);
            }
        });
        if (world.getBlockEntity(pos) instanceof MultiBlockCoreEntity) {
            world.breakBlock(pos, true);
        }
    }
}