package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

public class PixieUtremJarBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape SHAPE = VoxelShapeHelper.combineAll(
            Block.createCuboidShape(3, 0, 3, 13, 13, 13),
            Block.createCuboidShape(5, 13, 5, 11, 15, 11)
    );

    private final Supplier<Item> pixie;

    public PixieUtremJarBlock(Supplier<Item> pixie, Settings settings) {
        super(settings);
        this.pixie = pixie;
        this.setDefaultState(this.getStateManager().getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    public String getTranslationKey() {
        return BlockRegistry.UTREM_JAR.getTranslationKey();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        boolean flag = context.getWorld().getFluidState(context.getBlockPos()).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (player.isSneaking() && stack.isEmpty()) {
            ItemStack newStack = new ItemStack(this.pixie.get());

            player.setStackInHand(hand, newStack);
            world.setBlockState(pos, BlockRegistry.UTREM_JAR.getDefaultState().with(WATERLOGGED, state.get(WATERLOGGED)));
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }
    public Item getPixie() {
        return pixie.get();
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (state.isOf(BlockRegistry.PIXIE_UTREM_JAR)) {
            if (Objects.requireNonNull(MinecraftClient.getInstance().player).getInventory().contains(new ItemStack(ItemRegistry.LENS_OF_VERITATIS))) {
                double j = 0.4D * rand.nextFloat();
                double k = 0.4D * rand.nextFloat();
                double posX = pos.getX() + 0.5D + (rand.nextBoolean() ? j : -j);
                double posY = (float) pos.getY() + 0.1D + rand.nextFloat() / 2;
                double posZ = pos.getZ() + 0.5D + (rand.nextBoolean() ? k : -k);
                double ySpeed = ((double) rand.nextFloat() - 0.4D) * 0.1D;

//                world.addParticle(ModParticles.AUREAL_MOTE.get(), posX, posY, posZ, 0, ySpeed, 0);
                world.addParticle(ParticleTypes.ASH, posX, posY, posZ, 0, ySpeed, 0);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        boolean corrupted = stack.isOf(BlockRegistry.CORRUPTED_PIXIE_UTREM_JAR.asItem());
        tooltip.add(new TranslatableText("tooltip." + ForbiddenArcanus.MOD_ID + (corrupted ? ".corrupted_pixie" : ".pixie")).formatted(Formatting.GRAY));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
