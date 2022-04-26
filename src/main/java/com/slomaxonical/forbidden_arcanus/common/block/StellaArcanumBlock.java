package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.core.config.BlockConfig;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;

public class StellaArcanumBlock extends Block {
    public static boolean explode = false;
    public static World world;

    public StellaArcanumBlock(Settings strength) {
        super(strength);
    }

    @Override
    public void onBroken(WorldAccess worldAccess, BlockPos pos, BlockState state) {
        if (!worldAccess.isClient()) {
            if (explode && world != null) {
                world.createExplosion(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, BlockConfig.STELLA_ARCANUM_EXPLOSION_RADIUS.get(), BlockConfig.STELLA_ARCANUM_BLOCK_DAMAGE.get() ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE);
            }
            explode = false;
        }
        super.onBroken(worldAccess, pos, state);
    }
}
