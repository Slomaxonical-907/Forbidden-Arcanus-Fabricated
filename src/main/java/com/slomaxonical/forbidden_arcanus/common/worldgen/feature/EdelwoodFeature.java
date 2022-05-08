package com.slomaxonical.forbidden_arcanus.common.worldgen.feature;

import com.mojang.serialization.Codec;
import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class EdelwoodFeature extends Feature<DefaultFeatureConfig> {

    private static final BlockState EDELWOOD_LOG = BlockRegistry.EDELWOOD_LOG.getDefaultState();
    private static final BlockState CARVED_EDELWOOD_LOG = BlockRegistry.CARVED_EDELWOOD_LOG.getDefaultState().with(FABlockProperties.LEAVES, true);
    private static final BlockState EDELWOOD_BRANCH = BlockRegistry.EDELWOOD_BRANCH.getDefaultState();

    private static final int MAX_HEIGHT = 4;
    private static final int MIN_HEIGHT = 2;

    public EdelwoodFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }
    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        BlockPos.Mutable mutable = pos.mutableCopy();
        Random random = context.getRandom();

        if (!world.isAir(pos.up()) || !world.getBlockState(pos.down()).isIn(BlockTags.DIRT)) {
            return false;
        }

        int height = 1;
        Direction direction = Direction.fromHorizontal(random.nextInt(4));

        while (world.isAir(mutable) && height <= MAX_HEIGHT) {
            if (height == MAX_HEIGHT || !world.isAir(mutable.up()) || (height >= MIN_HEIGHT && random.nextDouble() < 0.35D)) {
                world.setBlockState(mutable, CARVED_EDELWOOD_LOG.with(Properties.HORIZONTAL_FACING, direction), 2);
                break;
            } else {
                world.setBlockState(mutable, EDELWOOD_LOG, 2);
            }
            mutable.move(Direction.UP);
            height++;
        }

        if (height >= 3 && random.nextDouble() < 0.65D) {
            direction = direction.rotateYClockwise();

            mutable.move(Direction.DOWN).move(direction);
            world.setBlockState(mutable, EDELWOOD_BRANCH.with(Properties.HORIZONTAL_FACING, direction), 2);

            mutable.move(direction.getOpposite(), 2);
            world.setBlockState(mutable, EDELWOOD_BRANCH.with(Properties.HORIZONTAL_FACING, direction.getOpposite()), 2);
        }

        return true;
    }
}
