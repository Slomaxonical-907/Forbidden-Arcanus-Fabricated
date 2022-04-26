package com.slomaxonical.forbidden_arcanus.common.blockEntity;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.POIRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestStorage;

public class ArcaneCrystalObeliskBlockEntity extends BlockEntity {
    public ArcaneCrystalObeliskBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.ARCANE_CRYSTAL_OBELISK, pos, state);
    }
    public static void serverTick(World world, BlockPos pos, BlockState state, ArcaneCrystalObeliskBlockEntity blockEntity) {
        if (world.getTime() % 100 != 0) {
            return;
        }

        PointOfInterestStorage manager = ((ServerWorld) world).getPointOfInterestStorage();

        BlockPos forgePos = manager.getInCircle(poiType -> poiType == POIRegistry.HEPHAESTUS_FORGE, pos, 4, PointOfInterestStorage.OccupationStatus.ANY).map(PointOfInterest::getPos).findFirst().orElse(null);

        if (forgePos != null && world.getBlockEntity(forgePos) instanceof HephaestusForgeBlockEntity forgeBlockEntity) {
//            forgeBlockEntity.getEssenceManager().increaseAureal(1);
            ForbiddenArcanus.LOGGER.debug("ArcaneCrystalForgeCode");
        }
    }
}
