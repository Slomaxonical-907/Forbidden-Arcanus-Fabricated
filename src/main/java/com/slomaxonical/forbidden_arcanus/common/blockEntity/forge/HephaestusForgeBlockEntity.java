package com.slomaxonical.forbidden_arcanus.common.blockEntity.forge;

import com.slomaxonical.forbidden_arcanus.common.block.HephaestusForgeBlock;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.ritual.EssenceManager;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.ritual.RitualManager;
import com.slomaxonical.forbidden_arcanus.common.inventory.HephaestusForgeMenu;
import com.slomaxonical.forbidden_arcanus.common.inventory.InputType;
import com.slomaxonical.forbidden_arcanus.common.inventory.input.HephaestusForgeInput;
import com.slomaxonical.forbidden_arcanus.common.inventory.input.HephaestusForgeInputs;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HephaestusForgeBlockEntity extends LockableContainerBlockEntity {
    private final DefaultedList<ItemStack> inventoryContents = DefaultedList.ofSize(9, ItemStack.EMPTY);
    private final PropertyDelegate hephaestusForgeData;
    private final RitualManager ritualManager = new RitualManager(this);
    private final EssenceManager essenceManager = new EssenceManager(this);
    private final MagicCircle magicCircle = new MagicCircle(this.ritualManager);
    private HephaestusForgeLevel forgeLevel = HephaestusForgeLevel.ONE;
    private List<LivingEntity> entities = new ArrayList<>();

    private int displayCounter;

    public HephaestusForgeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.HEPHAESTUS_FORGE, pos, state);
        this.hephaestusForgeData = new PropertyDelegate() {
            @Override
            public int get(int index) {
                EssenceManager manager = HephaestusForgeBlockEntity.this.getEssenceManager();

                return switch (index) {
                    case 0 -> manager.getLevel().getIndex();
                    case 1 -> manager.getAureal();
                    case 2 -> manager.getCorruption();
                    case 3 -> manager.getSouls();
                    case 4 -> manager.getBlood();
                    case 5 -> manager.getExperience();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                EssenceManager manager = HephaestusForgeBlockEntity.this.getEssenceManager();

                switch (index) {
                    case 0 -> HephaestusForgeBlockEntity.this.setLevel(HephaestusForgeLevel.getFromIndex(value));
                    case 1 -> manager.setAureal(value);
                    case 2 -> manager.setCorruption(value);
                    case 3 -> manager.setSouls(value);
                    case 4 -> manager.setBlood(value);
                    case 5 -> manager.setExperience(value);
                }
            }

            @Override
            public int size() {
                return 6;
            }
        };
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, HephaestusForgeBlockEntity blockEntity) {
        blockEntity.magicCircle.tick();
        blockEntity.displayCounter++;
    }
    public static void serverTick(World world, BlockPos pos, BlockState state, HephaestusForgeBlockEntity blockEntity) {
        for (int i = 5; i <= 8; i++) {
            ItemStack stack = blockEntity.inventoryContents.get(i);

            if (stack.isEmpty()) {
                continue;
            }

            InputType inputType = blockEntity.getInputTypeFromSlot(i);

            if (inputType == null) {
                continue;
            }

            HephaestusForgeInput input = blockEntity.getInput(stack, inputType);

            if (input != null) {
                blockEntity.fillWith(inputType, stack, input, i);

                blockEntity.markDirty();
            }
        }

        if (world.getTime() % 80 == 0) {
            ((HephaestusForgeBlock) state.getBlock()).updateState(state, world, pos);
        }

        if (world.getTime() % 20 == 0) {
            blockEntity.entities = world.getNonSpectatingEntities(LivingEntity.class, new Box(pos).expand(5, 5, 5));

            blockEntity.essenceManager.tick();
        }

        blockEntity.ritualManager.tick((ServerWorld) world, pos);
    }
    private InputType getInputTypeFromSlot(int slot) {
        return switch (slot) {
            case 5 -> InputType.AUREAL;
            case 6 -> InputType.SOULS;
            case 7 -> InputType.BLOOD;
            case 8 -> InputType.EXPERIENCE;
            default -> null;
        };
    }
    @Nullable
    private HephaestusForgeInput getInput(ItemStack stack, InputType inputType) {
        if (this.isTypeFull(inputType)) {
            return null;
        }

        return HephaestusForgeInputs.getInputs().stream().filter(input -> input.canInput(inputType, stack)).findFirst().orElse(null);
    }
    private boolean isTypeFull(InputType inputType) {
        HephaestusForgeLevel level = this.getForgeLevel();
        EssenceManager manager = this.getEssenceManager();

        return switch (inputType) {
            case AUREAL -> manager.getAureal() >= level.getMaxAureal();
            case SOULS -> manager.getSouls() >= level.getMaxSouls();
            case BLOOD -> manager.getBlood() >= level.getMaxBlood();
            case EXPERIENCE -> manager.getExperience() >= level.getMaxExperience();
        };
    }

    public HephaestusForgeLevel getForgeLevel() {
        return this.forgeLevel;
    }

    public void setLevel(HephaestusForgeLevel level) {
        this.forgeLevel = level;
    }

    public PropertyDelegate getHephaestusForgeData() {
        return hephaestusForgeData;
    }

    public EssenceManager getEssenceManager() {
        return essenceManager;
    }

    public MagicCircle getMagicCircle() {
        return magicCircle;
    }

    public List<LivingEntity> getEntities() {
        return entities;
    }

    public void fillWith(InputType inputType, ItemStack stack, HephaestusForgeInput input, int slot) {
        int value = input.getInputValue(inputType, stack, Objects.requireNonNull(this.getWorld()).getRandom());
        EssenceManager manager = this.getEssenceManager();

        switch (inputType) {
            case AUREAL -> manager.increaseAureal(value);
            case SOULS -> manager.increaseSouls(value);
            case BLOOD -> manager.increaseBlood(value);
            case EXPERIENCE -> manager.increaseExperience(value);
        }

        input.finishInput(inputType, stack, this, slot, value);
    }

    public RitualManager getRitualManager() {
        return ritualManager;
    }

    public int getDisplayCounter() {
        return displayCounter;
    }

    @Override
    protected void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);

        tag.putString("Level", this.getForgeLevel().getName());
        Inventories.writeNbt(tag, this.inventoryContents);

        tag.put("Ritual", this.getRitualManager().save(new NbtCompound()));
        tag.put("Essences", this.getEssenceManager().save(new NbtCompound()));
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        this.setLevel(HephaestusForgeLevel.getFromName(tag.getString("Level")));

        this.inventoryContents.clear();
        Inventories.readNbt(tag, this.inventoryContents);

        this.getRitualManager().load(tag.getCompound("Ritual"));
        this.getEssenceManager().load(tag.getCompound("Essences"));
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

//    public AABB ie Box getRenderBoundingBox() IforgeblockEntity todo








    @Override
    protected Text getContainerName() {
        return new TranslatableText("container.forbidden_arcanus.hephaestus_forge");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new HephaestusForgeMenu(syncId, this, this.getHephaestusForgeData(), playerInventory);
    }

    @Override
    public int size() {
        return this.inventoryContents.size();
    }

    @Override
    public boolean isEmpty() {
        return this.inventoryContents.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot) {
        return  this.inventoryContents.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = Inventories.splitStack(this.inventoryContents, slot, amount);
        if (!stack.isEmpty()) {
            this.markDirty();
        }

        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventoryContents, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.inventoryContents.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }

        if (slot == 4 && this.getWorld() != null && !this.getWorld().isClient()) {
            BlockPos pos = this.getPos();

//            NetworkHandler.sentToTrackingChunk(this.getWorld().getWorldChunk(pos), new UpdateItemInSlotPacket(pos, stack, 4)); todo
        }

        this.markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.getWorld() == null || this.getWorld().getBlockEntity(this.pos) != this) {
            return false;
        }

        return player.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void clear() {
        this.inventoryContents.clear();
    }
}
