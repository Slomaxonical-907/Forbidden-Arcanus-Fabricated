package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.common.block.util.PillarType;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

public class RunicChiseledPolishedDarkstone extends Block {
    public static final BooleanProperty ACTIVATED = FABlockProperties.ACTIVATED;

    public RunicChiseledPolishedDarkstone(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(ACTIVATED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(ItemRegistry.ARCANE_CRYSTAL) && !state.get(ACTIVATED)) {
            if (world.isClient()) {
                for (int i = 0; i < 15; i++) {
                    int j = world.getRandom().nextInt(2) * 2 - 1;
                    int k = world.getRandom().nextInt(2) * 2 - 1;
                    double d3 = world.getRandom().nextFloat() * (float) j;
                    double d4 = ((double) world.getRandom().nextFloat() - 0.5D) * 0.2D;
                    double d5 = world.getRandom().nextFloat() * (float) k;

                    world.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, d3, d4, d5);
                }
                return ActionResult.SUCCESS;
            }

            world.setBlockState(pos, state.with(ACTIVATED, true));
            ItemStackUtils.shrinkStack(player, stack);

//            level.getCapability(EntitySpawningBlockingCapability.ENTITY_SPAWNING_BLOCKING_BLOCKS_CAPABILITY).ifPresent(reg ->
//                    reg.addSpawningBlockingBlock(pos, getBlockingMode()));

            return ActionResult.CONSUME;

        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }
}
