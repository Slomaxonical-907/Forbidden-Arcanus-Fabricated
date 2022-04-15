package com.slomaxonical.malum.core.registries.block;


import com.slomaxonical.malum.common.block.ether.EtherBlock;
import com.slomaxonical.malum.common.block.ether.EtherBrazierBlock;
import com.slomaxonical.malum.common.block.ether.EtherTorchBlock;
import com.slomaxonical.malum.common.block.ether.WallEtherTorchBlock;
import com.slomaxonical.malum.common.block.fusion_plate.FusionPlateComponentBlock;
import com.slomaxonical.malum.common.block.fusion_plate.FusionPlateCoreBlock;
import com.slomaxonical.malum.common.block.item_storage.*;
import com.slomaxonical.malum.common.block.misc.*;
import com.slomaxonical.malum.common.block.misc.extended.*;
import com.slomaxonical.malum.common.block.obelisk.BrillianceObeliskCoreBlock;
import com.slomaxonical.malum.common.block.obelisk.ObeliskComponentBlock;
import com.slomaxonical.malum.common.block.obelisk.RunewoodObeliskCoreBlock;
import com.slomaxonical.malum.common.block.spirit_altar.SpiritAltarBlock;
import com.slomaxonical.malum.common.block.spirit_crucible.SpiritCrucibleComponentBlock;
import com.slomaxonical.malum.common.block.spirit_crucible.SpiritCrucibleCoreBlock;
import com.slomaxonical.malum.common.block.totem.TotemBaseBlock;
import com.slomaxonical.malum.common.block.totem.TotemPoleBlock;
import com.slomaxonical.malum.core.registries.SoundRegistry;
import com.slomaxonical.malum.core.registries.item.ItemRegistry;
import com.slomaxonical.malum.core.systems.block.SimpleBlockSettings;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

import java.awt.*;

import static net.minecraft.block.PressurePlateBlock.ActivationRule.EVERYTHING;
import static net.minecraft.block.PressurePlateBlock.ActivationRule.MOBS;

public class BlockRegistry implements BlockRegistryContainer {
    public static SimpleBlockSettings TAINTED_ROCK_PROPERTIES() {
        return new SimpleBlockSettings(Material.STONE, MapColor.STONE_GRAY).requiresTool().sounds(SoundRegistry.TAINTED_ROCK).strength(1.25F, 9.0F);
    }
    public static SimpleBlockSettings TWISTED_ROCK_PROPERTIES() {
        return new SimpleBlockSettings(Material.STONE, MapColor.STONE_GRAY).needsPickaxe().requiresTool().sounds(SoundRegistry.TWISTED_ROCK).strength(1.25F, 9.0F);
    }
    public static SimpleBlockSettings SOULSTONE_PROPERTIES() {
        return new SimpleBlockSettings(Material.STONE, MapColor.DARK_RED).needsPickaxe().requiresTool().strength(5.0F, 3.0F).sounds(SoundRegistry.SOULSTONE);
    }

    public static SimpleBlockSettings DEEPSLATE_SOULSTONE_PROPERTIES() {
        return new SimpleBlockSettings(Material.STONE, MapColor.DARK_RED).needsPickaxe().requiresTool().strength(7.0F, 6.0F).sounds(SoundRegistry.DEEPSLATE_SOULSTONE);
    }

    public static SimpleBlockSettings BLAZE_QUARTZ_ORE_PROPERTIES() {
        return new SimpleBlockSettings(Material.STONE, MapColor.DARK_RED).needsPickaxe().requiresTool().strength(3.0F, 3.0F).sounds(SoundRegistry.BLAZING_QUARTZ_ORE);
    }

    public static SimpleBlockSettings BLAZE_QUARTZ_PROPERTIES() {
        return new SimpleBlockSettings(Material.STONE, MapColor.RED).needsPickaxe().requiresTool().strength(5.0F, 6.0F).sounds(SoundRegistry.BLAZING_QUARTZ_BLOCK);
    }

    public static SimpleBlockSettings ARCANE_CHARCOAL_PROPERTIES() {
        return new SimpleBlockSettings(Material.STONE, MapColor.BLACK).needsPickaxe().requiresTool().strength(5.0F, 6.0F).sounds(SoundRegistry.ARCANE_CHARCOAL_BLOCK);
    }

    public static SimpleBlockSettings RUNEWOOD_PROPERTIES() {
        return new SimpleBlockSettings(Material.WOOD, MapColor.YELLOW).needsAxe().sounds(BlockSoundGroup.WOOD).strength(1.75F, 4.0F);
    }

    public static SimpleBlockSettings RUNEWOOD_PLANTS_PROPERTIES() {
        return new SimpleBlockSettings(Material.PLANT, MapColor.YELLOW).noCollision().nonOpaque().sounds(BlockSoundGroup.GRASS).breakInstantly();
    }

    public static SimpleBlockSettings SOULWOOD_PROPERTIES() {
        return new SimpleBlockSettings(Material.WOOD, MapColor.PURPLE).needsAxe().sounds(BlockSoundGroup.WOOD).strength(1.75F, 4.0F);
    }

    public static SimpleBlockSettings SOULWOOD_PLANTS_PROPERTIES() {
        return new SimpleBlockSettings(Material.PLANT, MapColor.PURPLE).noCollision().nonOpaque().sounds(BlockSoundGroup.GRASS).breakInstantly();
    }
    public static SimpleBlockSettings LEAVES_PROPERTIES() {
        return new SimpleBlockSettings(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)).mapColor(MapColor.YELLOW);
    }

    public static SimpleBlockSettings ETHER_BLOCK_PROPERTIES() {
        return new SimpleBlockSettings(Material.WOOL, MapColor.YELLOW).sounds(SoundRegistry.ETHER).nonOpaque().breakInstantly().luminance((l) -> 14);
    }

    public static SimpleBlockSettings HALLOWED_GOLD_PROPERTIES() {
        return new SimpleBlockSettings(Material.METAL, MapColor.YELLOW).requiresTool().needsPickaxe().sounds(SoundRegistry.HALLOWED_GOLD).nonOpaque().strength(2F, 16.0F);
    }

    public static SimpleBlockSettings SOUL_STAINED_STEEL_BLOCK_PROPERTIES() {
        return new SimpleBlockSettings(Material.METAL, MapColor.PURPLE).requiresTool().needsPickaxe().sounds(SoundRegistry.SOUL_STAINED_STEEL).strength(5f, 64.0f);
    }

    public static SimpleBlockSettings SPIRIT_JAR_PROPERTIES() {
        return new SimpleBlockSettings(Material.GLASS, MapColor.BLUE).strength(0.5f, 64f).sounds(SoundRegistry.HALLOWED_GOLD).nonOpaque();
    }

    public static SimpleBlockSettings SOUL_VIAL_PROPERTIES() {
        return new SimpleBlockSettings(Material.GLASS, MapColor.BLUE).strength(0.75f, 64f).sounds(SoundRegistry.SOUL_STAINED_STEEL).nonOpaque();
    }

    public static SimpleBlockSettings STONE_PROPERTIES() {
        return new SimpleBlockSettings(Material.STONE, MapColor.STONE_GRAY).needsPickaxe().requiresTool().strength(3f, 3f);
    }

    public static SimpleBlockSettings DEEPSLATE_PROPERTIES() {
        return new SimpleBlockSettings(Material.STONE, MapColor.DEEPSLATE_GRAY).needsPickaxe().requiresTool().strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE);
    }

    public static final Block SPIRIT_ALTAR = new SpiritAltarBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().nonOpaque());
    public static final Block SPIRIT_JAR = new SpiritJarBlock(SPIRIT_JAR_PROPERTIES().ignoreBlockStateDatagen().nonOpaque());
    public static final Block SOUL_VIAL = new SoulVialBlock(SOUL_VIAL_PROPERTIES().ignoreBlockStateDatagen().nonOpaque());

    public static final Block RUNEWOOD_OBELISK = new RunewoodObeliskCoreBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().nonOpaque());
    public static final Block RUNEWOOD_OBELISK_COMPONENT = new ObeliskComponentBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().dropsLike(RUNEWOOD_OBELISK).nonOpaque(), ItemRegistry.RUNEWOOD_OBELISK));

    public static final Block BRILLIANT_OBELISK = new BrillianceObeliskCoreBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().nonOpaque());
    public static final Block BRILLIANT_OBELISK_COMPONENT = new ObeliskComponentBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().dropsLike(BRILLIANT_OBELISK).nonOpaque(), ItemRegistry.BRILLIANT_OBELISK));

    public static final Block SPIRIT_CRUCIBLE = new SpiritCrucibleCoreBlock(TAINTED_ROCK_PROPERTIES().ignoreBlockStateDatagen().nonOpaque());
    public static final Block SPIRIT_CRUCIBLE_COMPONENT = new SpiritCrucibleComponentBlock(TAINTED_ROCK_PROPERTIES().ignoreBlockStateDatagen().dropsLike(SPIRIT_CRUCIBLE).nonOpaque());

    public static final Block SOULWOOD_PLINTH = new PlinthCoreBlock(SOULWOOD_PROPERTIES().ignoreBlockStateDatagen().nonOpaque());
    public static final Block SOULWOOD_PLINTH_COMPONENT = new PlinthComponentBlock(SOULWOOD_PROPERTIES().ignoreBlockStateDatagen().dropsLike(SOULWOOD_PLINTH).nonOpaque(), ItemRegistry.SOULWOOD_PLINTH));

    public static final Block SOULWOOD_FUSION_PLATE = new FusionPlateCoreBlock(SOULWOOD_PROPERTIES().ignoreBlockStateDatagen().nonOpaque());
    public static final Block SOULWOOD_FUSION_PLATE_COMPONENT = new FusionPlateComponentBlock(SOULWOOD_PROPERTIES().ignoreBlockStateDatagen().dropsLike(SOULWOOD_FUSION_PLATE).nonOpaque(), ItemRegistry.SOULWOOD_FUSION_PLATE));

    public static final Block RUNEWOOD_TOTEM_BASE = new TotemBaseBlock(RUNEWOOD_PROPERTIES().nonOpaque(), false);
    public static final Block RUNEWOOD_TOTEM_POLE = new TotemPoleBlock(RUNEWOOD_PROPERTIES().nonOpaque(), BlockRegistry.RUNEWOOD_LOG, false);

    public static final Block SOULWOOD_TOTEM_BASE = new TotemBaseBlock(RUNEWOOD_PROPERTIES().nonOpaque(), true);
    public static final Block SOULWOOD_TOTEM_POLE = new TotemPoleBlock(RUNEWOOD_PROPERTIES().nonOpaque(), BlockRegistry.SOULWOOD_LOG, true);
    //endregion

    //region tainted rock
    public static final Block TAINTED_ROCK = new Block(TAINTED_ROCK_PROPERTIES());
    public static final Block SMOOTH_TAINTED_ROCK = new Block(TAINTED_ROCK_PROPERTIES());
    public static final Block POLISHED_TAINTED_ROCK = new Block(TAINTED_ROCK_PROPERTIES());

    public static final Block TAINTED_ROCK_BRICKS = new Block(TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TAINTED_ROCK_BRICKS = new Block(TAINTED_ROCK_PROPERTIES());
    public static final Block SMALL_TAINTED_ROCK_BRICKS = new Block(TAINTED_ROCK_PROPERTIES());

    public static final Block TAINTED_ROCK_TILES = new Block(TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TAINTED_ROCK_TILES = new Block(TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_SMALL_TAINTED_ROCK_BRICKS = new Block(TAINTED_ROCK_PROPERTIES());

    public static final Block TAINTED_ROCK_PILLAR = new PillarBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block TAINTED_ROCK_PILLAR_CAP = new MalumDirectionalBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block TAINTED_ROCK_COLUMN = new PillarBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block TAINTED_ROCK_COLUMN_CAP = new MalumDirectionalBlock(TAINTED_ROCK_PROPERTIES());

    public static final Block CUT_TAINTED_ROCK = new Block(TAINTED_ROCK_PROPERTIES());
    public static final Block CHISELED_TAINTED_ROCK = new Block(TAINTED_ROCK_PROPERTIES());

    public static final Block TAINTED_ROCK_SLAB = new SlabBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block SMOOTH_TAINTED_ROCK_SLAB = new SlabBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block POLISHED_TAINTED_ROCK_SLAB = new SlabBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block TAINTED_ROCK_BRICKS_SLAB = new SlabBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TAINTED_ROCK_BRICKS_SLAB = new SlabBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block SMALL_TAINTED_ROCK_BRICKS_SLAB = new SlabBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block TAINTED_ROCK_TILES_SLAB = new SlabBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TAINTED_ROCK_TILES_SLAB = new SlabBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB = new SlabBlock(TAINTED_ROCK_PROPERTIES());

    public static final Block TAINTED_ROCK_STAIRS = new MalumStairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES());
    public static final Block SMOOTH_TAINTED_ROCK_STAIRS = new MalumStairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES());
    public static final Block POLISHED_TAINTED_ROCK_STAIRS = new MalumStairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES());
    public static final Block TAINTED_ROCK_BRICKS_STAIRS = new MalumStairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TAINTED_ROCK_BRICKS_STAIRS = new MalumStairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES());
    public static final Block SMALL_TAINTED_ROCK_BRICKS_STAIRS = new MalumStairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES());
    public static final Block TAINTED_ROCK_TILES_STAIRS = new MalumStairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TAINTED_ROCK_TILES_STAIRS = new MalumStairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS = new MalumStairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES());

    public static final Block TAINTED_ROCK_PRESSURE_PLATE = new MalumPressurePlateBlock(MOBS, TAINTED_ROCK_PROPERTIES());

    public static final Block TAINTED_ROCK_WALL = new WallBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block TAINTED_ROCK_BRICKS_WALL = new WallBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block SMALL_TAINTED_ROCK_BRICKS_WALL = new WallBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TAINTED_ROCK_BRICKS_WALL = new WallBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block TAINTED_ROCK_TILES_WALL = new WallBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL = new WallBlock(TAINTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TAINTED_ROCK_TILES_WALL = new WallBlock(TAINTED_ROCK_PROPERTIES());

    public static final Block TAINTED_ROCK_ITEM_STAND = new ItemStandBlock(TAINTED_ROCK_PROPERTIES().nonOpaque());
    public static final Block TAINTED_ROCK_ITEM_PEDESTAL = new ItemPedestalBlock(TAINTED_ROCK_PROPERTIES().nonOpaque());
    //endregion

    //region twisted rock
    public static final Block TWISTED_ROCK = new Block(TWISTED_ROCK_PROPERTIES());
    public static final Block SMOOTH_TWISTED_ROCK = new Block(TWISTED_ROCK_PROPERTIES());
    public static final Block POLISHED_TWISTED_ROCK = new Block(TWISTED_ROCK_PROPERTIES());

    public static final Block TWISTED_ROCK_BRICKS = new Block(TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TWISTED_ROCK_BRICKS = new Block(TWISTED_ROCK_PROPERTIES());
    public static final Block SMALL_TWISTED_ROCK_BRICKS = new Block(TWISTED_ROCK_PROPERTIES());

    public static final Block TWISTED_ROCK_TILES = new Block(TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TWISTED_ROCK_TILES = new Block(TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_SMALL_TWISTED_ROCK_BRICKS = new Block(TWISTED_ROCK_PROPERTIES());

    public static final Block TWISTED_ROCK_PILLAR = new PillarBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block TWISTED_ROCK_PILLAR_CAP = new MalumDirectionalBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block TWISTED_ROCK_COLUMN = new PillarBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block TWISTED_ROCK_COLUMN_CAP = new MalumDirectionalBlock(TWISTED_ROCK_PROPERTIES());

    public static final Block CUT_TWISTED_ROCK = new Block(TWISTED_ROCK_PROPERTIES());
    public static final Block CHISELED_TWISTED_ROCK = new Block(TWISTED_ROCK_PROPERTIES());

    public static final Block TWISTED_ROCK_SLAB = new SlabBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block SMOOTH_TWISTED_ROCK_SLAB = new SlabBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block POLISHED_TWISTED_ROCK_SLAB = new SlabBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block TWISTED_ROCK_BRICKS_SLAB = new SlabBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TWISTED_ROCK_BRICKS_SLAB = new SlabBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block SMALL_TWISTED_ROCK_BRICKS_SLAB = new SlabBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block TWISTED_ROCK_TILES_SLAB = new SlabBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TWISTED_ROCK_TILES_SLAB = new SlabBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB = new SlabBlock(TWISTED_ROCK_PROPERTIES());

    public static final Block TWISTED_ROCK_STAIRS = new MalumStairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES());
    public static final Block SMOOTH_TWISTED_ROCK_STAIRS = new MalumStairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES());
    public static final Block POLISHED_TWISTED_ROCK_STAIRS = new MalumStairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES());
    public static final Block TWISTED_ROCK_BRICKS_STAIRS = new MalumStairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TWISTED_ROCK_BRICKS_STAIRS = new MalumStairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES());
    public static final Block SMALL_TWISTED_ROCK_BRICKS_STAIRS = new MalumStairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES());
    public static final Block TWISTED_ROCK_TILES_STAIRS = new MalumStairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TWISTED_ROCK_TILES_STAIRS = new MalumStairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS = new MalumStairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES());

    public static final Block TWISTED_ROCK_PRESSURE_PLATE = new MalumPressurePlateBlock(MOBS, TWISTED_ROCK_PROPERTIES());

    public static final Block TWISTED_ROCK_WALL = new WallBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block TWISTED_ROCK_BRICKS_WALL = new WallBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block SMALL_TWISTED_ROCK_BRICKS_WALL = new WallBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TWISTED_ROCK_BRICKS_WALL = new WallBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block TWISTED_ROCK_TILES_WALL = new WallBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL = new WallBlock(TWISTED_ROCK_PROPERTIES());
    public static final Block CRACKED_TWISTED_ROCK_TILES_WALL = new WallBlock(TWISTED_ROCK_PROPERTIES());

    public static final Block TWISTED_ROCK_ITEM_STAND = new ItemStandBlock(TWISTED_ROCK_PROPERTIES().nonOpaque());
    public static final Block TWISTED_ROCK_ITEM_PEDESTAL = new ItemPedestalBlock(TWISTED_ROCK_PROPERTIES().nonOpaque());
    //endregion

    //region runewood
    public static final Block RUNEWOOD_SAPLING = new MalumSaplingBlock(RUNEWOOD_PLANTS_PROPERTIES().ticksRandomly(), FeatureRegistry.RUNEWOOD_TREE));
    public static final Block RUNEWOOD_LEAVES = new MalumLeavesBlock(LEAVES_PROPERTIES().ignoreLootDatagen(), new Color(175, 65, 48), new Color(251, 193, 76));

    public static final Block STRIPPED_RUNEWOOD_LOG = new PillarBlock(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_LOG = new RunewoodLogBlock(RUNEWOOD_PROPERTIES(), STRIPPED_RUNEWOOD_LOG, false);
    public static final Block STRIPPED_RUNEWOOD = new PillarBlock(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD = new MalumLogBlock(RUNEWOOD_PROPERTIES(), STRIPPED_RUNEWOOD));

    public static final Block REVEALED_RUNEWOOD_LOG = new SapFilledLogBlock(RUNEWOOD_PROPERTIES(), STRIPPED_RUNEWOOD_LOG, ItemRegistry.HOLY_SAP, SpiritTypeRegistry.INFERNAL_SPIRIT_COLOR));
    public static final Block EXPOSED_RUNEWOOD_LOG = new MalumLogBlock(RUNEWOOD_PROPERTIES(), REVEALED_RUNEWOOD_LOG));

    public static final Block RUNEWOOD_PLANKS = new Block(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_PLANKS_SLAB = new SlabBlock(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_PLANKS_STAIRS = new MalumStairsBlock(RUNEWOOD_PLANKS.getDefaultState(), RUNEWOOD_PROPERTIES());

    public static final Block VERTICAL_RUNEWOOD_PLANKS = new Block(RUNEWOOD_PROPERTIES());
    public static final Block VERTICAL_RUNEWOOD_PLANKS_SLAB = new SlabBlock(RUNEWOOD_PROPERTIES());
    public static final Block VERTICAL_RUNEWOOD_PLANKS_STAIRS = new MalumStairsBlock(VERTICAL_RUNEWOOD_PLANKS.getDefaultState(), RUNEWOOD_PROPERTIES());

    public static final Block RUNEWOOD_PANEL = new Block(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_PANEL_SLAB = new SlabBlock(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_PANEL_STAIRS = new MalumStairsBlock(RUNEWOOD_PANEL.getDefaultState(), RUNEWOOD_PROPERTIES());

    public static final Block RUNEWOOD_TILES = new Block(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_TILES_SLAB = new SlabBlock(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_TILES_STAIRS = new MalumStairsBlock(RUNEWOOD_TILES.getDefaultState(), RUNEWOOD_PROPERTIES());

    public static final Block CUT_RUNEWOOD_PLANKS = new Block(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_BEAM = new PillarBlock(RUNEWOOD_PROPERTIES());

    public static final Block RUNEWOOD_DOOR = new MalumDoorBlock(RUNEWOOD_PROPERTIES().nonOpaque());
    public static final Block RUNEWOOD_TRAPDOOR = new MalumTrapDoorBlock(RUNEWOOD_PROPERTIES().nonOpaque());
    public static final Block SOLID_RUNEWOOD_TRAPDOOR = new MalumTrapDoorBlock(RUNEWOOD_PROPERTIES().nonOpaque());

    public static final Block RUNEWOOD_PLANKS_BUTTON = new MalumWoodenButtonBlock(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_PLANKS_PRESSURE_PLATE = new MalumPressurePlateBlock(EVERYTHING, RUNEWOOD_PROPERTIES());

    public static final Block RUNEWOOD_PLANKS_FENCE = new FenceBlock(RUNEWOOD_PROPERTIES());
    public static final Block RUNEWOOD_PLANKS_FENCE_GATE = new FenceGateBlock(RUNEWOOD_PROPERTIES());

    public static final Block RUNEWOOD_ITEM_STAND = new ItemStandBlock(RUNEWOOD_PROPERTIES().nonOpaque());
    public static final Block RUNEWOOD_ITEM_PEDESTAL = new WoodItemPedestalBlock(RUNEWOOD_PROPERTIES().nonOpaque());

    public static final Block RUNEWOOD_SIGN = new MalumSignBlock(RUNEWOOD_PROPERTIES().nonOpaque().noCollision(), WoodTypeRegistry.RUNEWOOD);
    public static final Block RUNEWOOD_WALL_SIGN = new MalumWallSignBlock(RUNEWOOD_PROPERTIES().nonOpaque().noCollision(), WoodTypeRegistry.RUNEWOOD);
    //endregion

    //region soulwood
    public static final Block SOULWOOD_SAPLING = new MalumSaplingBlock(SOULWOOD_PLANTS_PROPERTIES().ticksRandomly(), FeatureRegistry.SOULWOOD_TREE));
    public static final Block SOULWOOD_LEAVES = new MalumLeavesBlock(LEAVES_PROPERTIES().ignoreLootDatagen(), new Color(152, 6, 45), new Color(224, 30, 214));

    public static final Block STRIPPED_SOULWOOD_LOG = new PillarBlock(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_LOG = new RunewoodLogBlock(SOULWOOD_PROPERTIES(), STRIPPED_SOULWOOD_LOG, true));
    public static final Block STRIPPED_SOULWOOD = new PillarBlock(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD = new MalumLogBlock(SOULWOOD_PROPERTIES(), STRIPPED_SOULWOOD));

    public static final Block REVEALED_SOULWOOD_LOG = new SapFilledLogBlock(SOULWOOD_PROPERTIES(), STRIPPED_SOULWOOD_LOG, ItemRegistry.UNHOLY_SAP, new Color(214, 46, 83));
    public static final Block EXPOSED_SOULWOOD_LOG = new MalumLogBlock(SOULWOOD_PROPERTIES(), REVEALED_SOULWOOD_LOG));

    public static final Block SOULWOOD_PLANKS = new Block(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_PLANKS_SLAB = new SlabBlock(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_PLANKS_STAIRS = new MalumStairsBlock(SOULWOOD_PLANKS.getDefaultState(), SOULWOOD_PROPERTIES());

    public static final Block VERTICAL_SOULWOOD_PLANKS = new Block(SOULWOOD_PROPERTIES());
    public static final Block VERTICAL_SOULWOOD_PLANKS_SLAB = new SlabBlock(SOULWOOD_PROPERTIES());
    public static final Block VERTICAL_SOULWOOD_PLANKS_STAIRS = new MalumStairsBlock(VERTICAL_SOULWOOD_PLANKS.getDefaultState(), SOULWOOD_PROPERTIES());

    public static final Block SOULWOOD_PANEL = new Block(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_PANEL_SLAB = new SlabBlock(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_PANEL_STAIRS = new MalumStairsBlock(SOULWOOD_PANEL.getDefaultState(), SOULWOOD_PROPERTIES());

    public static final Block SOULWOOD_TILES = new Block(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_TILES_SLAB = new SlabBlock(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_TILES_STAIRS = new MalumStairsBlock(SOULWOOD_TILES.getDefaultState(), SOULWOOD_PROPERTIES());

    public static final Block CUT_SOULWOOD_PLANKS = new Block(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_BEAM = new PillarBlock(SOULWOOD_PROPERTIES());

    public static final Block SOULWOOD_DOOR = new MalumDoorBlock(SOULWOOD_PROPERTIES().nonOpaque());
    public static final Block SOULWOOD_TRAPDOOR = new MalumTrapDoorBlock(SOULWOOD_PROPERTIES().nonOpaque());
    public static final Block SOLID_SOULWOOD_TRAPDOOR = new MalumTrapDoorBlock(SOULWOOD_PROPERTIES().nonOpaque());

    public static final Block SOULWOOD_PLANKS_BUTTON = new MalumWoodenButtonBlock(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_PLANKS_PRESSURE_PLATE = new MalumPressurePlateBlock(EVERYTHING, SOULWOOD_PROPERTIES());

    public static final Block SOULWOOD_PLANKS_FENCE = new FenceBlock(SOULWOOD_PROPERTIES());
    public static final Block SOULWOOD_PLANKS_FENCE_GATE = new FenceGateBlock(SOULWOOD_PROPERTIES());

    public static final Block SOULWOOD_ITEM_STAND = new ItemStandBlock(SOULWOOD_PROPERTIES().nonOpaque());
    public static final Block SOULWOOD_ITEM_PEDESTAL = new WoodItemPedestalBlock(SOULWOOD_PROPERTIES().nonOpaque());

    public static final Block SOULWOOD_SIGN = new MalumSignBlock(SOULWOOD_PROPERTIES().nonOpaque().noCollision(), WoodTypeRegistry.SOULWOOD));
    public static final Block SOULWOOD_WALL_SIGN = new MalumWallSignBlock(SOULWOOD_PROPERTIES().nonOpaque().noCollision(), WoodTypeRegistry.SOULWOOD));
    //endregion

    //region ether
    public static final Block ETHER_TORCH = new EtherTorchBlock(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().lightLevel((b) -> 14));
    public static final Block WALL_ETHER_TORCH = new WallEtherTorchBlock(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().lightLevel((b) -> 14).dropsLike(ETHER_TORCH));
    public static final Block ETHER = new EtherBlock(ETHER_BLOCK_PROPERTIES());
    public static final Block TAINTED_ETHER_BRAZIER = new EtherBrazierBlock(TAINTED_ROCK_PROPERTIES().lightLevel((b) -> 14).nonOpaque());
    public static final Block TWISTED_ETHER_BRAZIER = new EtherBrazierBlock(TWISTED_ROCK_PROPERTIES().lightLevel((b) -> 14).nonOpaque());

    public static final Block IRIDESCENT_ETHER_TORCH = new EtherTorchBlock(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().lightLevel((b) -> 14));
    public static final Block IRIDESCENT_WALL_ETHER_TORCH = new WallEtherTorchBlock(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().lightLevel((b) -> 14).dropsLike(IRIDESCENT_ETHER_TORCH));
    public static final Block IRIDESCENT_ETHER = new EtherBlock(ETHER_BLOCK_PROPERTIES());
    public static final Block TAINTED_IRIDESCENT_ETHER_BRAZIER = new EtherBrazierBlock(TAINTED_ROCK_PROPERTIES().lightLevel((b) -> 14).nonOpaque());
    public static final Block TWISTED_IRIDESCENT_ETHER_BRAZIER = new EtherBrazierBlock(TWISTED_ROCK_PROPERTIES().lightLevel((b) -> 14).nonOpaque());
    //endregion

    public static final Block BLOCK_OF_ARCANE_CHARCOAL = new Block(ARCANE_CHARCOAL_PROPERTIES());

    public static final Block BLAZING_QUARTZ_ORE = new OreBlock(BLAZE_QUARTZ_ORE_PROPERTIES().ignoreBlockStateDatagen().ignoreLootDatagen(), UniformInt.of(3, 4));
    public static final Block BLOCK_OF_BLAZING_QUARTZ = new Block(BLAZE_QUARTZ_PROPERTIES());

    public static final Block BRILLIANT_STONE = new OreBlock(STONE_PROPERTIES().ignoreBlockStateDatagen().ignoreLootDatagen(), UniformInt.of(7, 9));
    public static final Block BRILLIANT_DEEPSLATE = new OreBlock(DEEPSLATE_PROPERTIES().ignoreBlockStateDatagen().ignoreLootDatagen(), UniformInt.of(8, 13));
    public static final Block BLOCK_OF_BRILLIANCE = new Block(STONE_PROPERTIES());

    public static final Block SOULSTONE_ORE = new Block(SOULSTONE_PROPERTIES().ignoreLootDatagen());
    public static final Block DEEPSLATE_SOULSTONE_ORE = new Block(DEEPSLATE_SOULSTONE_PROPERTIES().ignoreLootDatagen().strength(6f, 4f));
    public static final Block BLOCK_OF_SOULSTONE = new Block(SOULSTONE_PROPERTIES());

    public static final Block BLOCK_OF_HALLOWED_GOLD = new Block(HALLOWED_GOLD_PROPERTIES());
    public static final Block BLOCK_OF_SOUL_STAINED_STEEL = new Block(SOUL_STAINED_STEEL_BLOCK_PROPERTIES());



}
