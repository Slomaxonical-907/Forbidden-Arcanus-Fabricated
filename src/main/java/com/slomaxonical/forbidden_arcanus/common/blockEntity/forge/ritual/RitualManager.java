package com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.ritual;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.PedestalBlockEntity;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.HephaestusForgeBlockEntity;
import com.slomaxonical.forbidden_arcanus.common.entity.CrimsonLightningBoltEntity;
import com.slomaxonical.forbidden_arcanus.common.item.util.RitualStarterItem;
import com.slomaxonical.forbidden_arcanus.common.loader.RitualLoader;
import com.slomaxonical.forbidden_arcanus.common.networking.ForbiddenS2CPacketSender;
import com.slomaxonical.forbidden_arcanus.core.registries.EntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.POIRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.ParticleRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.valhelsia.valhelsia_core.common.util.NeedsStoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
public class RitualManager implements NeedsStoring {

    private final HephaestusForgeBlockEntity blockEntity;

    private final List<BlockPos> cachedPedestals = new ArrayList<>();
    private Ritual activeRitual;
    private int counter;
    private int lightningCounter;

    public RitualManager(HephaestusForgeBlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }

    public HephaestusForgeBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public Ritual getActiveRitual() {
        return activeRitual;
    }

    public void setActiveRitual(Ritual ritual) {
        this.activeRitual = ritual;
    }

    public boolean isRitualActive() {
        return this.activeRitual != null;
    }

    public void tryStartRitual(ServerWorld world, ItemStack stack, PlayerEntity player) {
        RitualStarterItem ritualStarterItem = (RitualStarterItem) stack.getItem();
        List<ItemStack> list = new ArrayList<>();

        if (ritualStarterItem.getRemainingUses(stack) <= 0) {
            return;
        }

        this.forEachPedestal(world, PedestalBlockEntity::hasStack, pedestalBlockEntity -> list.add(pedestalBlockEntity.getStack()), true);

        for (Ritual ritual : RitualLoader.getRituals()) {
            if (ritual.canStart(list, this.blockEntity)) {
                if (!player.getAbilities().creativeMode) {
                    ritualStarterItem.setRemainingUses(stack, ritualStarterItem.getRemainingUses(stack) - 1);
                }

                this.startRitual(ritual);
                return;
            }
        }
    }

    public void startRitual(Ritual ritual) {
        this.setActiveRitual(ritual);

        ritual.getEssences().reduceEssences(this.blockEntity);
    }

    public void tick(ServerWorld world, BlockPos pos) {
        if (!this.isRitualActive()) {
            return;
        }

        Random random = world.getRandom();

        int time = this.getActiveRitual().getTime();

        this.counter++;
        this.updateCachedPedestals(world);

        if (this.lightningCounter != 0) {
            this.lightningCounter++;

            if (this.lightningCounter == 300) {
                List<ItemStack> list = new ArrayList<>();

                this.forEachPedestal(world, PedestalBlockEntity::hasStack, pedestalBlockEntity -> list.add(pedestalBlockEntity.getStack()));

                if (!this.getActiveRitual().checkIngredients(list, this.blockEntity)) {
                    this.failRitual(world);

//                    NetworkHandler.sentToTrackingChunk(world.getWorldChunk(pos), new UpdateForgeRitualPacket(pos, this.activeRitual)); todo
                    return;
                }

                this.lightningCounter = 0;
            }
        }

        this.forEachPedestal(world, PedestalBlockEntity::hasStack, pedestalBlockEntity -> {
            BlockPos pedestalPos = pedestalBlockEntity.getPos();

            if (pedestalBlockEntity.getItemHeight() != 140) {
                int height = pedestalBlockEntity.getItemHeight() + 1;
                pedestalBlockEntity.setItemHeight(height);
                for (PlayerEntity trackingPlayer : PlayerLookup.tracking(pedestalBlockEntity)) {
                    ForbiddenS2CPacketSender.send(world, pedestalPos,pedestalBlockEntity.getStack(),height);
//                UpdatePedestalPacket.send(trackingPlayer,pedestalPos,pedestalBlockEntity.getStack(),height);
                }
            }

            this.addItemParticles(world, pedestalPos, pedestalBlockEntity.getItemHeight(), pedestalBlockEntity.getStack());
        });

        if (this.counter == time / 2.0F && random.nextDouble() <= this.getFailureChance() * 2) {
            CrimsonLightningBoltEntity entity = new CrimsonLightningBoltEntity(EntityRegistry.CRIMSON_LIGHTNING_BOLT, world);
            entity.setPos(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D);
            entity.setCosmetic(true);

            world.spawnEntity(entity);

            this.lightningCounter++;

            this.forEachPedestal(world, PedestalBlockEntity::hasStack, pedestalBlockEntity -> {
                if (random.nextBoolean()) {
                    ItemStack stack = pedestalBlockEntity.getStack().copy();
                    BlockPos pedestalPos = pedestalBlockEntity.getPos();

                    world.spawnEntity(new ItemEntity(world, pedestalPos.getX() + 0.5, pedestalPos.getY() + pedestalBlockEntity.getItemHeight() / 100.0F, pedestalPos.getZ() + 0.5, stack));
                    pedestalBlockEntity.clearStack(world, pos);
                }
            });
        }

        if (this.counter == time) {
            if (random.nextDouble() > this.getFailureChance()) {
                this.finishRitual(world);
            } else {
                this.failRitual(world);
            }
        }

//        NetworkHandler.sentToTrackingChunk(world.getWorldChunk(pos), new UpdateForgeRitualPacket(pos, this.activeRitual)); todo
    }

    public void finishRitual(ServerWorld world) {
        this.blockEntity.setStack(4, this.getActiveRitual().getResult());
        this.reset();

        this.forEachPedestal(world, PedestalBlockEntity::hasStack, pedestalBlockEntity -> {
            pedestalBlockEntity.clearStack(world, pedestalBlockEntity.getPos());
        });
    }

    public void failRitual(ServerWorld world) {
        ItemStack stack = this.blockEntity.getStack(4);
        BlockPos pos = this.blockEntity.getPos();

        this.reset();

        if (!stack.isEmpty()) {
            world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, stack));

            this.blockEntity.setStack(4, ItemStack.EMPTY);
        }

        this.forEachPedestal(world, PedestalBlockEntity::hasStack, pedestalBlockEntity -> {
            pedestalBlockEntity.clearStack(world, pedestalBlockEntity.getPos());
            this.blockEntity.getEssenceManager().increaseCorruption(2);
        });

        world.spawnParticles(ParticleRegistry.HUGE_MAGIC_EXPLOSION, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0, 1.0D, 0.0D, 0.0D, 0.0D);
        world.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2F) * 0.7F);
    }

    private void addItemParticles(ServerWorld level, BlockPos pedestalPos, int itemHeight, ItemStack stack) {
        BlockPos pos = this.blockEntity.getPos();

        double posX = pedestalPos.getX() + 0.5D;
        double posY = pedestalPos.getY() + 0.1D + itemHeight / 100.0F;
        double posZ = pedestalPos.getZ() + 0.5D;
        double xSpeed = 0.1D * (pos.getX() - pedestalPos.getX());
        double ySpeed = 0.22D;
        double zSpeed = 0.1D * (pos.getZ() - pedestalPos.getZ());

        if (level.getRandom().nextDouble() < 0.6D) {
            level.spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, stack), posX, posY, posZ, 0, xSpeed, ySpeed, zSpeed, 0.9D);
        }
    }

    private void reset() {
        this.counter = 0;
        this.lightningCounter = 0;
        this.setActiveRitual(null);
    }

    public double getFailureChance() {
        return ((this.getBlockEntity().getEssenceManager().getCorruption() + 5) / (float) this.getBlockEntity().getForgeLevel().getMaxCorruption()) / 2;
    }

    @Override
    public NbtCompound save(NbtCompound tag) {
        if (this.isRitualActive()) {
            tag.putString("ActiveRitual", this.getActiveRitual().getName().toString());
            tag.putInt("Counter", this.counter);

            if (this.lightningCounter != 0) {
                tag.putInt("LightningCounter", this.lightningCounter);
            }
        }

        return tag;
    }

    @Override
    public void load(NbtCompound tag) {
        if (tag.contains("ActiveRitual")) {
            this.setActiveRitual(RitualLoader.getRitual(new Identifier(tag.getString("ActiveRitual"))));
            this.counter = tag.getInt("Counter");

            if (this.counter != 0) {
                this.blockEntity.getMagicCircle().setRotation(this.counter);
            }

            if (tag.contains("LightningCounter")) {
                this.lightningCounter = tag.getInt("LightningCounter");
            }
        }
    }

    public void updateCachedPedestals(ServerWorld world) {
        PointOfInterestStorage manager = world.getPointOfInterestStorage();

        this.cachedPedestals.clear();
        manager.getInCircle(poiType -> poiType == POIRegistry.PEDESTAL, this.blockEntity.getPos(), 4, PointOfInterestStorage.OccupationStatus.ANY).forEach(pointOfInterest -> this.cachedPedestals.add(pointOfInterest.getPos()));
    }

    public void forEachPedestal(ServerWorld world, Predicate<PedestalBlockEntity> predicate, Consumer<PedestalBlockEntity> consumer) {
        this.forEachPedestal(world, predicate, consumer, false);
    }

    public void forEachPedestal(ServerWorld world, Predicate<PedestalBlockEntity> predicate, Consumer<PedestalBlockEntity> consumer, boolean updatePedestals) {
        if (updatePedestals) {
            this.updateCachedPedestals(world);
        }
        this.cachedPedestals.stream().map(pos -> (PedestalBlockEntity) world.getBlockEntity(pos)).filter(predicate).forEach(consumer);
    }
}
