package com.slomaxonical.forbidden_arcanus.common.blockEntity.forge;

import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.Property;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class HephaestusForgeBlockEntity extends LockableContainerBlockEntity {
    private final DefaultedList<ItemStack> inventoryContents = DefaultedList.ofSize(9, ItemStack.EMPTY);
//    private final PropertyDelegate hephaestusForgeData;
//    private final RitualManager ritualManager = new RitualManager(this);
//    private final EssenceManager essenceManager = new EssenceManager(this);
//    private final MagicCircle magicCircle = new MagicCircle(this.ritualManager);
//    private HephaestusForgeLevel forgeLevel = HephaestusForgeLevel.ONE;
//    private List<LivingEntity> entities = new ArrayList<>();

    private int displayCounter;

    public HephaestusForgeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected Text getContainerName() {
        return null;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return null;
    }

    public HephaestusForgeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.HEPHAESTUS_FORGE, pos, state);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStack(int slot) {
        return null;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return null;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return null;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }

    @Override
    public void clear() {

    }
}
