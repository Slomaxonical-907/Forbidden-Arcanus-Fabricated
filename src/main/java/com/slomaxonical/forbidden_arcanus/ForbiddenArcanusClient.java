package com.slomaxonical.forbidden_arcanus;

import com.slomaxonical.forbidden_arcanus.client.particle.AurealMoteParticle;
import com.slomaxonical.forbidden_arcanus.client.particle.HugeMagicExplosionParticle;
import com.slomaxonical.forbidden_arcanus.client.particle.SoulParticle;
import com.slomaxonical.forbidden_arcanus.client.renderer.block.*;
import com.slomaxonical.forbidden_arcanus.client.renderer.entity.BoomArrowRenderer;
import com.slomaxonical.forbidden_arcanus.client.renderer.entity.CrimsonLightningBoltRenderer;
import com.slomaxonical.forbidden_arcanus.client.renderer.entity.DracoArcanusArrowRenderer;
import com.slomaxonical.forbidden_arcanus.client.renderer.entity.EnergyBallRenderer;
import com.slomaxonical.forbidden_arcanus.core.registries.EntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.ParticleRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.ExplosionLargeParticle;
import net.minecraft.client.render.RenderLayer;

public class ForbiddenArcanusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                BlockRegistry.RUNIC_CHISELED_POLISHED_DARKSTONE,
                BlockRegistry.XPETRIFIED_ORE,
                BlockRegistry.ARCANE_CRYSTAL_ORE,
                BlockRegistry.RUNIC_STONE,
                BlockRegistry.ARCANE_GOLDEN_GLASS,
                BlockRegistry.RUNIC_GLASS,
                BlockRegistry.DARK_RUNIC_GLASS,
                BlockRegistry.ARCANE_GOLDEN_GLASS_PANE,
                BlockRegistry.RUNIC_GLASS_PANE,
                BlockRegistry.DARK_RUNIC_GLASS_PANE,
                BlockRegistry.FUNGYSS,
                BlockRegistry.CHERRYWOOD_SAPLING,
                BlockRegistry.MYSTERYWOOD_SAPLING,
                BlockRegistry.GROWING_EDELWOOD,
                BlockRegistry.EDELWOOD_LOG,
                BlockRegistry.CARVED_EDELWOOD_LOG,
                BlockRegistry.EDELWOOD_BRANCH,
                BlockRegistry.ARCANE_GOLD_DOOR,
                BlockRegistry.FUNGYSS_DOOR,
                BlockRegistry.CHERRYWOOD_DOOR,
                BlockRegistry.MYSTERYWOOD_DOOR,
                BlockRegistry.EDELWOOD_DOOR,
                BlockRegistry.ARCANE_GOLD_TRAPDOOR,
                BlockRegistry.FUNGYSS_TRAPDOOR,
                BlockRegistry.CHERRYWOOD_TRAPDOOR,
                BlockRegistry.MYSTERYWOOD_TRAPDOOR,
                BlockRegistry.EDELWOOD_TRAPDOOR,
                BlockRegistry.EDELWOOD_LADDER,
                BlockRegistry.HEPHAESTUS_FORGE,
                BlockRegistry.ARCANE_CRYSTAL_OBELISK,
                BlockRegistry.UTREM_JAR,
                BlockRegistry.PIXIE_UTREM_JAR,
                BlockRegistry.CORRUPTED_PIXIE_UTREM_JAR,
                BlockRegistry.NIPA,
                BlockRegistry.PETRIFIED_ROOT,
                BlockRegistry.BLACK_HOLE,
                BlockRegistry.YELLOW_ORCHID,
                BlockRegistry.GOLDEN_ORCHID,
                BlockRegistry.STRANGE_ROOT,
                BlockRegistry.POTTED_CHERRYWOOD_SAPLING,
                BlockRegistry.POTTED_MYSTERYWOOD_SAPLING,
                BlockRegistry.POTTED_YELLOW_ORCHID
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                BlockRegistry.CHERRYWOOD_LEAVES,
                BlockRegistry.CHERRYWOOD_LEAF_CARPET,
                BlockRegistry.MYSTERYWOOD_LEAVES,
                BlockRegistry.NUGGETY_MYSTERYWOOD_LEAVES,
                BlockRegistry.ARCANE_GOLDEN_CHAIN
        );
//        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
//            registry.register(new Identifier(ForbiddenArcanus.MOD_ID, "particles/aureal_mote"));
//        }

        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.SOUL, SoulParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.AUREAL_MOTE, AurealMoteParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.MAGIC_EXPLOSION, ExplosionLargeParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.HUGE_MAGIC_EXPLOSION, new HugeMagicExplosionParticle.Factory());

        //BlockEntityRenderers
//        BlockEntityRendererRegistry.register(BlockEntityRegistry.BLACK_HOLE, BlackHoleRenderer::new);
//        BlockEntityRendererRegistry.register(BlockEntityRegistry.HEPHAESTUS_FORGE, HephaestusForgeRenderer::new);
        BlockEntityRendererRegistry.register(BlockEntityRegistry.NIPA, NipaRenderer::new);
//        BlockEntityRendererRegistry.register(BlockEntityRegistry.OBSIDIAN_SKULL, ObsidianSkullRenderer::new);
        BlockEntityRendererRegistry.register(BlockEntityRegistry.PEDESTAL, PedestalRenderer::new);
//        BlockEntityRendererRegistry.register(BlockEntityRegistry.UTREM_JAR, UtremJarRenderer::new);

        //EntityRenderers
        EntityRendererRegistry.register(EntityRegistry.ENERGY_BALL, EnergyBallRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.CRIMSON_LIGHTNING_BOLT, CrimsonLightningBoltRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.BOOM_ARROW, BoomArrowRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.DRACO_ARCANUS_ARROW, DracoArcanusArrowRenderer::new);

    }
}
