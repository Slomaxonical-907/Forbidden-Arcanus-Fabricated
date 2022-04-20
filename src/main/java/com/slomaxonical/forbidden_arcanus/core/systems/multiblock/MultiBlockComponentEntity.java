//package com.slomaxonical.forbidden_arcanus.core.systems.multiblock;
//
//import com.slomaxonical.forbidden_arcanus.core.helper.BlockHelper;
//import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
//import com.slomaxonical.forbidden_arcanus.core.systems.blockEntity.SimpleBlockEntity;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.entity.BlockEntityType;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.Hand;
//import net.minecraft.util.math.BlockPos;
//
//public class MultiBlockComponentEntity extends SimpleBlockEntity {
//
//    public BlockPos corePos;
//    public MultiBlockComponentEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
//        super(type, pos, state);
//    }
//    public MultiBlockComponentEntity(BlockPos pos, BlockState state) {
//        super(BlockEntityRegistry.MULTIBLOCK_COMPONENT, pos, state);
//    }
//
//    @Override
//    protected void writeNbt(NbtCompound tag) {
//        BlockHelper.saveBlockPos(tag, corePos);
//        super.writeNbt(tag);
//    }
//
//    @Override
//    public void readNbt(NbtCompound tag) {
//        corePos = BlockHelper.loadBlockPos(tag);
//        super.readNbt(tag);
//    }
//
//
//    @Override
//    public ActionResult onUse(PlayerEntity player, Hand hand) {
//        if (world.getBlockEntity(corePos) instanceof MultiBlockCoreEntity core)
//        {
//            return core.onUse(player, hand);
//        }
//        return super.onUse(player, hand);
//    }
//
//    @Override
//    public void onBreak() {
//        if (world.getBlockEntity(corePos) instanceof MultiBlockCoreEntity core)
//        {
//            core.onBreak();
//        }
//        super.onBreak();
//    }
//
////    @NotNull
////    @Override
////    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
////        if (level.getBlockEntity(corePos) instanceof MultiBlockCoreEntity core)
////        {
////            return core.getCapability(cap);
////        }
////        return super.getCapability(cap);
////    }
////
////    @NotNull
////    @Override
////    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
////        if (level.getBlockEntity(corePos) instanceof MultiBlockCoreEntity core)
////        {
////            return core.getCapability(cap, side);
////        }
////        return super.getCapability(cap, side);
////    }
//}
