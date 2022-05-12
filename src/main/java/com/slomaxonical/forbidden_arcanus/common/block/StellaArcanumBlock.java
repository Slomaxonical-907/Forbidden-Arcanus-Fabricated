package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.core.config.BlockConfig;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.world.explosion.Explosion;

public class StellaArcanumBlock extends Block {

    public StellaArcanumBlock(Settings strength) {
        super(strength);
    }

    public static void onBrokenEvent(){
        PlayerBlockBreakEvents.AFTER.register(((world, player, pos, state, entity) -> {
            ItemStack stack = player.getMainHandStack();
            if (state.getBlock() instanceof StellaArcanumBlock && BlockConfig.STELLA_ARCANUM_EXPLODE.get() && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                world.createExplosion(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, BlockConfig.STELLA_ARCANUM_EXPLOSION_RADIUS.get(), BlockConfig.STELLA_ARCANUM_BLOCK_DAMAGE.get() ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE);
            }
        }));
    }
}
