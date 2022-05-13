package com.slomaxonical.forbidden_arcanus.client.tooltip;

import com.mojang.blaze3d.systems.RenderSystem;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ClientEdelwoodBucketTooltip implements TooltipComponent {

    private final ItemStack emptyBucket = new ItemStack(ItemRegistry.EDELWOOD_BUCKET);
    private final ItemStack filledBucket;

    private final int fullness;
    private final int capacity;

    public ClientEdelwoodBucketTooltip(EdelwoodBucketTooltip tooltip) {
        this.filledBucket = tooltip.filledBucket();
        this.fullness = tooltip.fullness();
        this.capacity = tooltip.capacity();
    }

    @Override
    public int getHeight() {
        return 19;
    }

    @Override
    public int getWidth(@Nonnull TextRenderer font) {
        return 2 + 18 * this.capacity;
    }

    @Override
    public void drawItems(@Nonnull TextRenderer font, int mouseX, int mouseY, MatrixStack matrix, @Nonnull ItemRenderer itemRenderer, int blitOffset) {
        matrix.push();

        matrix.translate(0, 0, 300);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        for (int i = 1; i <= this.capacity; i++) {
            itemRenderer.renderInGuiWithOverrides(i <= this.fullness ? this.filledBucket : this.emptyBucket,  (i - 1) * 15 + mouseX, mouseY + 1);
        }

        matrix.pop();
    }
}
