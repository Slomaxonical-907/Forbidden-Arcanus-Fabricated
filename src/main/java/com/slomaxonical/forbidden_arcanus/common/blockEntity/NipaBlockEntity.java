package com.slomaxonical.forbidden_arcanus.common.blockEntity;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NipaBlockEntity extends BlockEntity {
    private int lastSpeck = 0;
    private int speckHeight = 10;

    private Map<UUID, Integer> players = new HashMap<>();

    private int cachedPower = 0;

    public NipaBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.NIPA, pos, state);
    }
    public static void clientTick(World world, BlockPos pos, BlockState state, NipaBlockEntity blockEntity) {
        if (blockEntity.speckHeight < 75) {
            blockEntity.speckHeight++;
        }
    }
    public static void serverTick(World world, BlockPos pos, BlockState state, NipaBlockEntity blockEntity) {
        if (!state.get(FABlockProperties.SPECK)) {
            blockEntity.lastSpeck++;

            if (blockEntity.lastSpeck >= 3600) {
                world.setBlockState(pos, state.with(FABlockProperties.SPECK, true));
                blockEntity.lastSpeck = 0;
            }
        }

        if (world.getTime() % 20 == 0) {
            Box axisAlignedBB = new Box(pos).expand(3);
            List<PlayerEntity> playersInRange = world.getNonSpectatingEntities(PlayerEntity.class, axisAlignedBB);

            Map<UUID, Integer> players = new HashMap<>();

            for (PlayerEntity player : playersInRange) {
                UUID uuid = player.getUuid();

                players.put(uuid, blockEntity.players.getOrDefault(uuid, 0) + 1);

//                if (players.get(uuid) == 30) {
//                    AurealHelper.increaseAureal(player, 1);
//                    players.remove(uuid);
//                }
            }

            blockEntity.players = players;
        }
    }
    @Override
    public void readNbt(@Nonnull NbtCompound tag) {
        super.readNbt(tag);
        this.lastSpeck = tag.getInt("LastSpeck");

        this.players.clear();

        NbtList players = tag.getList("Players", 10);

        players.forEach(nbt -> {
            if (nbt instanceof NbtCompound player) {
                this.players.put(player.getUuid("Player"), player.getInt("Time"));
            }
        });
    }

    @Override
    public void writeNbt(@Nonnull NbtCompound tag) {
        super.writeNbt(tag);
        tag.putInt("LastSpeck", this.lastSpeck);

        NbtList players = new NbtList();

        this.players.forEach((uuid, integer) -> {
            NbtCompound player = new NbtCompound();
            player.putUuid("Player", uuid);
            player.putInt("Time", integer);

            players.add(player);
        });
        tag.put("Players", players);
    }


    public double getSpeckHeight() {
        return speckHeight / 100.0F;
    }

    public void setSpeckHeight(int speckHeight) {
        this.speckHeight = speckHeight;
    }

    public int getCachedPower() {
        return cachedPower;
    }

    public void setCachedPower(int cachedPower) {
        this.cachedPower = cachedPower;
    }
}
