package com.slomaxonical.forbidden_arcanus.client.gui.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.HephaestusForgeLevel;
import com.slomaxonical.forbidden_arcanus.common.inventory.EnhancerSlot;
import com.slomaxonical.forbidden_arcanus.common.inventory.HephaestusForgeMenu;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Hephaestus Forge Screen
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.gui.screen.HephaestusForgeScreen
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-06-28
 */
public class HephaestusForgeScreen extends HandledScreen<HephaestusForgeMenu> {

    private static final Identifier TEXTURES = new Identifier(ForbiddenArcanus.ID, "textures/gui/container/hephaestus_forge.png");

    public HephaestusForgeScreen(HephaestusForgeMenu container, PlayerInventory inventory, Text title) {
        super(container, inventory, title);
        this.backgroundWidth += 52;
        this.titleX += 26;
        this.titleY -= 2;
        this.playerInventoryTitleX += 26;
        this.playerInventoryTitleY += 2;
    }

    @Override
    public void render(@Nonnull MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        super.render(matrices, mouseX, mouseY, partialTicks);

        for(int i = 0; i < this.handler.slots.size(); i++) {
            Slot slot = this.handler.slots.get(i);

            if (slot instanceof EnhancerSlot) {
                int posX = mouseX - this.x;
                int posY = mouseY - this.y;

                if (posX >= (slot.x - 1) && posX < (slot.x + 16 + 1) && posY >= (slot.y - 1) && posY < (slot.y + 16 + 1)) {
                    focusedSlot = slot;
                }
            }
        }

        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, this.title, (float) this.titleX, (float) this.titleY, 4210752);
        this.textRenderer.draw(matrices, this.playerInventoryTitle, (float) this.playerInventoryTitleX, (float) this.playerInventoryTitleY, 4210752);
    }

    @Override
    protected void drawBackground(@Nonnull MatrixStack matrices, float partialTicks, int x, int y) {
        this.renderBackground(matrices);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURES);

        this.drawTexture(matrices, this.x + 26, this.y, 0, 0, this.backgroundWidth - 52, this.backgroundHeight);

        this.drawTexture(matrices, this.x, this.y + 16, 176, 61, 29, 51);
        this.drawTexture(matrices, this.x + 26 + 172, this.y + 16, 206, 61, 29, 51);

        for (int i = 0; i <= 3; i++) {
            if (!((EnhancerSlot) this.handler.getSlot(i)).isUnlocked()) {
                this.drawTexture(matrices, this.x + (i < 2 ? 30 : 126) + 26, this.y + (i == 0 || i == 3 ? 22 : 44), 196, 40, 20, 20);
            }
        }

        HephaestusForgeLevel level = HephaestusForgeLevel.getFromIndex(this.handler.getHephaestusForgeData().get(0));

        this.renderBar(matrices, 1, level.getMaxAureal(), 8, 177);
        this.renderBar(matrices, 2, level.getMaxCorruption(), 8, 183);
        this.renderBar(matrices, 3, level.getMaxSouls(), 20, 189);
        this.renderBar(matrices, 4, level.getMaxBlood(), 151, 195);
        this.renderBar(matrices, 5, level.getMaxExperience(), 163, 201);

        int ySize = Math.toIntExact(Math.round(32.0F * handler.getHephaestusForgeData().get(0) / HephaestusForgeLevel.getFromIndex(handler.getHephaestusForgeData().get(0)).getMaxAureal()));
        this.drawTexture(matrices, this.x + 26 + 8, this.y + 22 + 32 - ySize, 177, 3 + 32 - ySize, 4, ySize);
    }


    @Override
    protected void drawMouseoverTooltip(MatrixStack matrices, int x, int y) {
        super.drawMouseoverTooltip(matrices, x, y);

        int posX = x - this.x - 26;
        int posY = y - this.y;

        this.renderBarsTooltip(matrices, posX, posY, x, y);

        Slot slot = focusedSlot;

        if (slot instanceof EnhancerSlot && !((EnhancerSlot) slot).isUnlocked()) {
            this.renderTooltip(matrices, new TranslatableText("gui.forbidden_arcanus.hephaestus_forge.unlocked_at_level").append(": " + ((EnhancerSlot) slot).getRequiredLevel().getName()), x, y);
        }
    }

    private void renderBarsTooltip(MatrixStack matrices, int x, int y, int screenX, int screenY) {
        if (!(y >= 19 && y <= 68)) {
            return;
        }

        PropertyDelegate data = this.handler.getHephaestusForgeData();
        HephaestusForgeLevel level = HephaestusForgeLevel.getFromIndex(data.get(0));

        if (x >= 6 && x <= 13) {
            List<Text> textComponents = new ArrayList<>();
            textComponents.add(new TranslatableText("forbidden_arcanus.aureal").append(": " + data.get(1) + "/" + level.getMaxAureal()));
            textComponents.add(new TranslatableText("forbidden_arcanus.corruption").append(": " + data.get(2) + "/" + level.getMaxCorruption()));

            this.renderOrderedTooltip(matrices, Lists.transform(textComponents, Text::asOrderedText), screenX, screenY);
        } else if (x >= 18 && x <= 25) {
            this.renderTooltip(matrices, new TranslatableText("forbidden_arcanus.souls").append(": " + data.get(3) + "/" + level.getMaxSouls()), screenX, screenY);
        } else if (x >= 149 && x <= 156) {
            this.renderTooltip(matrices, new TranslatableText("forbidden_arcanus.blood").append(": " + data.get(4) + "/" + level.getMaxBlood()), screenX, screenY);
        } else if (x >= 161 && x <= 168) {
            this.renderTooltip(matrices, new TranslatableText("forbidden_arcanus.experience").append(": " + data.get(5) + "/" + level.getMaxExperience()), screenX, screenY);
        }
    }

    private void renderBar(MatrixStack matrixStack, int data, int max, int x, int textureX) {
        int ySize = Math.toIntExact(Math.round(32.0F * this.handler.getHephaestusForgeData().get(data) / max));
        this.drawTexture(matrixStack, this.x + 26 + x, this.y + 22 + 32 - ySize, textureX, 3 + 32 - ySize, 4, ySize);
    }
}
