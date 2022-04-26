package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.NipaBlockEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

public class NipaBlock extends PlantBlock implements BlockEntityProvider {
    public static final BooleanProperty SPECK = FABlockProperties.SPECK;
    private static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 11.0D, 14.0D);


    public NipaBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(SPECK, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NipaBlockEntity(pos,state);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return false;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(SPECK)) {
            NipaBlockEntity blockEntity = (NipaBlockEntity) world.getBlockEntity(pos);
            if (blockEntity != null) {
                this.harvestSpeck(state, world, pos, blockEntity);

                return ActionResult.success(world.isClient());
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isOf(newState.getBlock()) && state.get(SPECK)) {
            NipaBlockEntity blockEntity = (NipaBlockEntity) world.getBlockEntity(pos);
            if (blockEntity != null) {
                world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + blockEntity.getSpeckHeight(), pos.getZ() + 0.5, new ItemStack(ItemRegistry.ARCANE_CRYSTAL_DUST_SPECK)));
            }
        }
        super.onStateReplaced(state, world, pos, newState, isMoving);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        NipaBlockEntity blockEntity = (NipaBlockEntity) world.getBlockEntity(pos);
        if (blockEntity != null) {
            int power = world.getReceivedRedstonePower(pos);

            if (state.get(SPECK) && blockEntity.getCachedPower() != power && power != 0) {
                this.harvestSpeck(state, world, pos, blockEntity);
            }

            blockEntity.setCachedPower(power);
        }

        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (Objects.requireNonNull(MinecraftClient.getInstance().player).getInventory().contains(new ItemStack(ItemRegistry.LENS_OF_VERITATIS))) {
            double j = 0.4D * rand.nextFloat();
            double k = 0.4D * rand.nextFloat();
            double posX = pos.getX() + 0.5D + (rand.nextBoolean() ? j : -j);
            double posY = (float) pos.getY() + 0.1D + rand.nextFloat() / 2.5;
            double posZ = pos.getZ() + 0.5D + (rand.nextBoolean() ? k : -k);
            double ySpeed = ((double) rand.nextFloat() - 0.4D) * 0.1D;

//            world.addParticle(ModParticles.AUREAL_MOTE.get(), posX, posY, posZ, 0, ySpeed, 0);
            world.addParticle(ParticleTypes.ENCHANTED_HIT, posX, posY, posZ, 0, ySpeed, 0);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient() && state.get(SPECK)) {
//            return BaseEntityBlock.createTickerHelper(type, BlockEntityRegistry.NIPA, NipaBlockEntity::clientTick);
        } else if (!world.isClient()) {
//            return BaseEntityBlock.createTickerHelper(type, BlockEntityRegistry.NIPA, NipaBlockEntity::serverTick);
        }
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SPECK);
    }

    private void harvestSpeck(BlockState state, World world, BlockPos pos, NipaBlockEntity blockEntity) {
        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + blockEntity.getSpeckHeight(), pos.getZ() + 0.5, new ItemStack(ItemRegistry.ARCANE_CRYSTAL_DUST_SPECK)));
        world.setBlockState(pos, state.with(SPECK, false));

        blockEntity.setSpeckHeight(10);
    }
}
