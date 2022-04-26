package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.checkerframework.checker.units.qual.A;

public class GoldenOrchidBlock extends CropBlock {
    public static final IntProperty AGE = FABlockProperties.AGE_6;
    private static final int MAX_AGE = 6;

    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 4.0D, 11.0D),
            Block.createCuboidShape(5.0D, 0.0D, 5.0D, 13.0D, 6.0D, 13.0D),
            Block.createCuboidShape(4.0D, 0.0D, 4.0D, 14.0D, 7.0D, 14.0D),
            Block.createCuboidShape(3.0D, 0.0D, 3.0D, 15.0D, 9.0D, 15.0D),
            Block.createCuboidShape(2.0D, 0.0D, 2.0D, 15.0D, 10.0D, 15.0D),
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 16.0D, 12.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
    };
    public GoldenOrchidBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(AGE, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3 = state.getModelOffset(world, pos);
        return SHAPES[state.get(AGE)].offset(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ItemRegistry.GOLDEN_ORCHID_SEEDS;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(BlockRegistry.MAGICAL_FARMLAND);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos below = pos.down();
        return (world.getBaseLightLevel(pos, 0) >= 8 || world.isSkyVisible(pos)) && this.canPlantOnTop(world.getBlockState(below), world, below);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }
}
