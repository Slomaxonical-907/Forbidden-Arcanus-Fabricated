package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.common.block.util.ObeliskPart;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.ArcaneCrystalObeliskBlockEntity;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.BlackHoleBlockEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.ParticleRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import com.slomaxonical.forbidden_arcanus.mixin.CheckTypeInvoker;
import dev.architectury.registry.block.BlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class ArcaneCrystalObeliskBlock extends Block implements Waterloggable, BlockEntityProvider{

    public static final EnumProperty<ObeliskPart> PART = FABlockProperties.OBELISK_PART;
    public static final BooleanProperty RITUAL = FABlockProperties.RITUAL;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final Map<ObeliskPart, VoxelShape> SHAPES = Util.make(new EnumMap<>(ObeliskPart.class), map -> {
        map.put(ObeliskPart.LOWER, VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 8, 16), Block.createCuboidShape(1, 8, 1, 15, 16, 15)));
        map.put(ObeliskPart.MIDDLE, Block.createCuboidShape(2, 0, 2, 14, 16, 14));
        map.put(ObeliskPart.UPPER, Block.createCuboidShape(3, 0, 3, 13, 14, 13));
    });

    public ArcaneCrystalObeliskBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(PART, ObeliskPart.LOWER).with(RITUAL,false).with(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.get(RITUAL) && state.get(PART) == ObeliskPart.LOWER ? new ArcaneCrystalObeliskBlockEntity(pos, state) : null;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state.get(PART));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();

        if (pos.getY() > world.getTopY() - 3 || !world.getBlockState(pos.up()).canReplace(context) || !world.getBlockState(pos.up(2)).canReplace(context)) {
            return null;
        }

        return this.getDefaultState()
                .with(RITUAL, this.isArcaneChiseledPolishedDarkstoneBelow(world, pos))
                .with(WATERLOGGED, world.getFluidState(pos).getFluid() == Fluids.WATER);
    }
    public boolean isArcaneChiseledPolishedDarkstoneBelow(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOf(BlockRegistry.ARCANE_CHISELED_POLISHED_DARKSTONE);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        ObeliskPart part = state.get(PART);

        if (direction.getAxis() == Direction.Axis.Y && ((part == ObeliskPart.LOWER == (direction == Direction.UP)) || part == ObeliskPart.MIDDLE)) {
            return neighborState.getBlock() == this && neighborState.get(PART) != part ? state : Blocks.AIR.getDefaultState();
        }

        if (part == ObeliskPart.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        super.neighborUpdate(state, world, pos, block, fromPos, notify);

        if (!fromPos.equals(pos.down())) {
            return;
        }

        BlockState newState = state.with(RITUAL, this.isArcaneChiseledPolishedDarkstoneBelow(world, pos));

        if (state != newState) {
            world.setBlockState(pos, newState);
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient() && player.getAbilities().creativeMode) {
            ObeliskPart part = state.get(PART);

            if (part != ObeliskPart.LOWER) {
                BlockPos offsetPos = pos.down(part == ObeliskPart.MIDDLE ? 1 : 2);

                world.setBlockState(offsetPos, Blocks.AIR.getDefaultState(), 35);
                world.syncWorldEvent(player, 2001, offsetPos, Block.getRawIdFromState(world.getBlockState(offsetPos)));
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), state.with(PART, ObeliskPart.MIDDLE).with(WATERLOGGED, world.getFluidState(pos.up()).getFluid() == Fluids.WATER));
        world.setBlockState(pos.up(2), state.with(PART, ObeliskPart.UPPER).with(WATERLOGGED, world.getFluidState(pos.up(2)).getFluid() == Fluids.WATER));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        ObeliskPart part = state.get(PART);
        BlockPos posDown = pos.down();

        if (part == ObeliskPart.LOWER) {
            return world.getBlockState(posDown).isSideSolidFullSquare(world, posDown, Direction.UP);
        }

        return true;
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.BLOCK;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null && state.get(PART) != ObeliskPart.LOWER) {
            if (!player.getInventory().contains(ItemRegistry.Stacks.LENS_OF_VERITATIS)) {
                return;
            }

            double j = 0.6D * rand.nextFloat();
            double k = 0.6D * rand.nextFloat();
            double posX = pos.getX() + 0.5D + (rand.nextBoolean() ? j : -j);
            double posY = (float) pos.getY() + 0.1D + rand.nextFloat() / 2;
            double posZ = pos.getZ() + 0.5D + (rand.nextBoolean() ? k : -k);
            double ySpeed = ((double) rand.nextFloat() - 0.4D) * 0.1D;

//            world.addParticle(ParticleRegistry.AUREAL_MOTE.get(), posX, posY, posZ, 0, ySpeed, 0);
            world.addParticle(ParticleTypes.ANGRY_VILLAGER, posX, posY, posZ, 0, ySpeed, 0);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (!world.isClient()) {
            return CheckTypeInvoker.invokeCheckType(type, BlockEntityRegistry.ARCANE_CRYSTAL_OBELISK, ArcaneCrystalObeliskBlockEntity::serverTick);

        }
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PART,RITUAL,WATERLOGGED);
    }
}
