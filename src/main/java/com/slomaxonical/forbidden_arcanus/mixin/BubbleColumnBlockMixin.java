package com.slomaxonical.forbidden_arcanus.mixin;

import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BubbleColumnBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Bubble Column Block Mixin <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.mixin.BubbleColumnBlockMixin
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-11-21
 */
@Mixin(BubbleColumnBlock.class)
public class BubbleColumnBlockMixin {

    @Inject(at = @At(value = "HEAD"), method = "getBubbleState", cancellable = true)
    private static void forbiddenArcanus_getColumnState(BlockState state, CallbackInfoReturnable<BlockState> cir) {
        if (state.isOf(BlockRegistry.SOULLESS_SAND)) {
            cir.setReturnValue(Blocks.BUBBLE_COLUMN.getDefaultState().with(BubbleColumnBlock.DRAG, false));
        }
    }
}
