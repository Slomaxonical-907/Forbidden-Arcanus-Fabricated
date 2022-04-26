package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.network.packet.s2c.play.BlockEventS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.Random;

public class StrangeRootBlock extends CropBlock {
    private static final int MAX_AGE = 3;

    public static final IntProperty AGE = Properties.AGE_3;

    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 14.0D, 6.0D, 14.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 15.0D, 9.0D, 15.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D)
    };
    public StrangeRootBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(AGE,0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(AGE)];
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
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isRegionLoaded(pos,  pos.add(1,1,1)) || world.getBaseLightLevel(pos, 0) >= 5) {
            return;
        }

        int age = this.getAge(state);

//        if (age < this.getMaxAge() && ForgeHooks.onCropsGrowPre(world, pos, state, random.nextInt((int) ((25.0F / getGrowthSpeed(this, world, pos)) + 1)) == 0)) {
        if (age < this.getMaxAge()) {
            world.setBlockState(pos, state.cycle(AGE), 2);
//            ForgeHooks.onCropsGrowPost(world, pos, state);
        }
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.BASE_STONE_OVERWORLD);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ItemRegistry.STRANGE_ROOT;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
