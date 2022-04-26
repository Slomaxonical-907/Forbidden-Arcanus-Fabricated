package com.slomaxonical.forbidden_arcanus.core.registries.block;


import com.slomaxonical.forbidden_arcanus.common.block.*;
import com.slomaxonical.forbidden_arcanus.common.block.extended.*;
import com.slomaxonical.forbidden_arcanus.common.block.util.RodBlock;
import com.slomaxonical.forbidden_arcanus.common.worldGen.CherrywoodTreeGrower;
import com.slomaxonical.forbidden_arcanus.common.worldGen.MysterywoodTreeGrower;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.BlockView;

public class BlockRegistry implements BlockRegistryContainer {
    public static final Block DARKSTONE =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block ARCANE_CHISELED_DARKSTONE =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block DARKSTONE_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block DARKSTONE_STAIRS =  new FAStairsBlock(DARKSTONE.getDefaultState(), FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block DARKSTONE_WALL =  new WallBlock(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block ARCANE_GILDED_DARKSTONE =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F).sounds(BlockSoundGroup.GILDED_BLACKSTONE));
    public static final Block POLISHED_DARKSTONE =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block POLISHED_DARKSTONE_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.STONE_SLAB).strength(4.5F, 8.0F));
    public static final Block POLISHED_DARKSTONE_STAIRS =  new FAStairsBlock(POLISHED_DARKSTONE.getDefaultState(), FabricBlockSettings.copy(Blocks.STONE_STAIRS).strength(4.5F, 8.0F));
    public static final Block POLISHED_DARKSTONE_WALL =  new WallBlock(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block POLISHED_DARKSTONE_PRESSURE_PLATE =  new FAPressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copy(Blocks.STONE_PRESSURE_PLATE).strength(0.5F));
    public static final Block POLISHED_DARKSTONE_BUTTON =  new FAStoneButtonBlock(FabricBlockSettings.copy(Blocks.STONE_BUTTON).strength(0.5F));
    public static final Block CHISELED_POLISHED_DARKSTONE =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block RUNIC_CHISELED_POLISHED_DARKSTONE =  new RunicChiseledPolishedDarkstone(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block ARCANE_CHISELED_POLISHED_DARKSTONE =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block POLISHED_DARKSTONE_BRICKS =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block POLISHED_DARKSTONE_BRICK_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.STONE_SLAB).strength(4.5F, 8.0F));
    public static final Block POLISHED_DARKSTONE_BRICK_STAIRS =  new FAStairsBlock(POLISHED_DARKSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copy(Blocks.STONE_STAIRS).strength(4.5F, 8.0F));
    public static final Block POLISHED_DARKSTONE_BRICK_WALL =  new WallBlock(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block CRACKED_POLISHED_DARKSTONE_BRICKS =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block TILED_POLISHED_DARKSTONE_BRICKS =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block ARCANE_POLISHED_DARKSTONE =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block ARCANE_POLISHED_DARKSTONE_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.STONE_SLAB).strength(4.5F, 8.0F));
    public static final Block ARCANE_POLISHED_DARKSTONE_STAIRS =  new FAStairsBlock(ARCANE_POLISHED_DARKSTONE.getDefaultState(), FabricBlockSettings.copy(Blocks.STONE_STAIRS).strength(4.5F, 8.0F));
    public static final Block ARCANE_POLISHED_DARKSTONE_WALL =  new WallBlock(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block CHISELED_ARCANE_POLISHED_DARKSTONE =  new Block(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block ARCANE_POLISHED_DARKSTONE_PILLAR =  new PillarBlock(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block ARCANE_POLISHED_DARKSTONE_ROD =  new RodBlock(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F));
    public static final Block DARKSTONE_PEDESTAL =  new PedestalBlock(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F).nonOpaque());
    public static final Block ARCANE_DARKSTONE_PEDESTAL =  new PedestalBlock(FabricBlockSettings.copy(Blocks.STONE).strength(4.5F, 8.0F).nonOpaque());

    public static final Block STELLA_ARCANUM =  new StellaArcanumBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN).strength(38.0F, 1200.0F));
    public static final Block XPETRIFIED_ORE =  new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F));
    public static final Block ARCANE_CRYSTAL_ORE =  new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F), UniformIntProvider.create(2, 5));
    public static final Block DEEPSLATE_ARCANE_CRYSTAL_ORE =  new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(2, 5));
    public static final Block RUNIC_STONE =  new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F), UniformIntProvider.create(4, 8));
    public static final Block RUNIC_DEEPSLATE =  new OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).requiresTool().strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(4, 8));
    public static final Block RUNIC_DARKSTONE =  new OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).requiresTool().strength(6.0F, 4.0F), UniformIntProvider.create(4, 8));
    public static final Block DARK_NETHER_STAR_BLOCK =  new Block(FabricBlockSettings.of(Materials.DARK_NETHER_STAR).requiresTool().strength(10.0F, 1200.0F));
    public static final Block PROCESSED_OBSIDIAN_BLOCK =  new Block(FabricBlockSettings.copy(Blocks.OBSIDIAN));
    public static final Block ARCANE_GOLD_BLOCK =  new Block(FabricBlockSettings.copy(Blocks.GOLD_BLOCK));
    public static final Block STELLARITE_BLOCK =  new Block(FabricBlockSettings.copy(Blocks.OBSIDIAN));
    public static final Block ARCANE_CRYSTAL_BLOCK =  new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_WHITE).requiresTool().strength(1.0F, 3.0F).nonOpaque());
    public static final Block RUNE_BLOCK =  new Block(FabricBlockSettings.of(Materials.RUNE).requiresTool().strength(5.0F, 6.0F));
    public static final Block DARK_RUNE_BLOCK =  new Block(FabricBlockSettings.of(Materials.RUNE, MapColor.GRAY).requiresTool().strength(5.0F, 6.0F));
    public static final Block ARCANE_GOLDEN_GLASS =  new GlassBlock(FabricBlockSettings.copy(Blocks.GLASS));
    public static final Block RUNIC_GLASS =  new GlassBlock(FabricBlockSettings.copy(Blocks.GLASS));
    public static final Block DARK_RUNIC_GLASS =  new GlassBlock(FabricBlockSettings.copy(Blocks.GLASS));
    public static final Block ARCANE_GOLDEN_GLASS_PANE = new FAPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE));

    public static final Block RUNIC_GLASS_PANE =  new FAPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE));
    public static final Block DARK_RUNIC_GLASS_PANE =  new FAPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE));

    public static final Block SOULLESS_SAND =  new SoullessSandBlock(FabricBlockSettings.copy(Blocks.SOUL_SAND).velocityMultiplier(1.0F));
    public static final Block SOULLESS_SANDSTONE =  new Block(FabricBlockSettings.copy(Blocks.SANDSTONE));
    public static final Block CUT_SOULLESS_SANDSTONE =  new Block(FabricBlockSettings.copy(Blocks.SANDSTONE));
    public static final Block POLISHED_SOULLESS_SANDSTONE =  new Block(FabricBlockSettings.copy(Blocks.SANDSTONE));
    public static final Block SOULLESS_SANDSTONE_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_SLAB));
    public static final Block CUT_SOULLESS_SANDSTONE_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_SLAB));
    public static final Block POLISHED_SOULLESS_SANDSTONE_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_SLAB));
    public static final Block SOULLESS_SANDSTONE_STAIRS =  new FAStairsBlock(SOULLESS_SANDSTONE.getDefaultState(), FabricBlockSettings.copy(Blocks.SANDSTONE_STAIRS));
    public static final Block POLISHED_SOULLESS_SANDSTONE_STAIRS =  new FAStairsBlock(POLISHED_SOULLESS_SANDSTONE.getDefaultState(), FabricBlockSettings.copy(Blocks.SANDSTONE_STAIRS));
    public static final Block SOULLESS_SANDSTONE_WALL =  new WallBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_WALL));

    public static final Block FUNGYSS =  new FungyssBlock(FabricBlockSettings.copy(Blocks.WARPED_FUNGUS).sounds(BlockSoundGroup.GRASS));
    public static final Block CHERRYWOOD_SAPLING =  new FASaplingBlock(new CherrywoodTreeGrower(), FabricBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block MYSTERYWOOD_SAPLING =  new FASaplingBlock(new MysterywoodTreeGrower(), FabricBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block GROWING_EDELWOOD =  new GrowingEdelwoodBlock(FabricBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block FUNGYSS_BLOCK =  new MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BLUE).strength(0.2F).sounds(BlockSoundGroup.WOOD));
    public static final LeavesBlock CHERRYWOOD_LEAVES =  new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block CHERRYWOOD_LEAF_CARPET =  new LeafCarpetBlock(()->CHERRYWOOD_LEAVES, FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block MYSTERYWOOD_LEAVES =  new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block NUY_MYSTERYWOOD_LEAVES =  new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));

    public static final Block FUNGYSS_STEM =  new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F).sounds(BlockSoundGroup.STEM));
    public static final Block CHERRYWOOD_LOG =  new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    public static final Block THIN_CHERRYWOOD_LOG =  new ThinLogBlock(FabricBlockSettings.copy(Blocks.STONE));
    public static final Block MYSTERYWOOD_LOG =  new MysterywoodLogBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    public static final Block EDELWOOD_LOG =  new EdelwoodLogBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.BROWN).ticksRandomly());
    public static final Block CARVED_EDELWOOD_LOG =  new CarvedEdelwoodLogBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.BROWN).ticksRandomly());
    public static final Block EDELWOOD_BRANCH =  new EdelwoodBranchBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.BROWN));
    public static final Block STRIPPED_CHERRYWOOD_LOG =  new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    public static final Block STRIPPED_MYSTERYWOOD_LOG =  new MysterywoodLogBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    public static final Block FUNGYSS_HYPHAE =  new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F).sounds(BlockSoundGroup.STEM));
    public static final Block CHERRYWOOD =  new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    public static final Block MYSTERYWOOD =  new MysterywoodLogBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    public static final Block STRIPPED_CHERRYWOOD =  new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    public static final Block STRIPPED_MYSTERYWOOD =  new MysterywoodLogBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    public static final Block FUNGYSS_PLANKS =  new Block(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block CHERRYWOOD_PLANKS =  new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    public static final Block CARVED_CHERRYWOOD_PLANKS =  new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    public static final Block MYSTERYWOOD_PLANKS =  new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    public static final Block EDELWOOD_PLANKS =  new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    public static final Block ARCANE_EDELWOOD_PLANKS =  new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    public static final Block FUNGYSS_SLAB =  new SlabBlock(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block CHERRYWOOD_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB));
    public static final Block MYSTERYWOOD_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB));
    public static final Block EDELWOOD_SLAB =  new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB));
    public static final Block FUNGYSS_STAIRS =  new FAStairsBlock(FUNGYSS_PLANKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block CHERRYWOOD_STAIRS =  new FAStairsBlock(CHERRYWOOD_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS));
    public static final Block MYSTERYWOOD_STAIRS =  new FAStairsBlock(MYSTERYWOOD_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS));
    public static final Block EDELWOOD_STAIRS =  new FAStairsBlock(EDELWOOD_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS));

    public static final Block ARCANE_GOLD_DOOR =  new FADoorBlock(FabricBlockSettings.of(Material.METAL, MapColor.GOLD).requiresTool().strength(3.0F).sounds(BlockSoundGroup.METAL).nonOpaque());
    public static final Block FUNGYSS_DOOR =  new FADoorBlock(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque());
    public static final Block CHERRYWOOD_DOOR =  new FADoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque());
    public static final Block MYSTERYWOOD_DOOR =  new FADoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque());
    public static final Block EDELWOOD_DOOR =  new FADoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque());
    public static final Block ARCANE_GOLD_TRAPDOOR =  new FATrapDoorBlock(FabricBlockSettings.of(Material.METAL, MapColor.GOLD).requiresTool().strength(3.0F).sounds(BlockSoundGroup.METAL).nonOpaque().allowsSpawning(BlockRegistry::never));
    public static final Block FUNGYSS_TRAPDOOR =  new FATrapDoorBlock(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning(BlockRegistry::never));
    public static final Block CHERRYWOOD_TRAPDOOR =  new FATrapDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning(BlockRegistry::never));
    public static final Block MYSTERYWOOD_TRAPDOOR =  new FATrapDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning(BlockRegistry::never));
    public static final Block EDELWOOD_TRAPDOOR =  new FATrapDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning(BlockRegistry::never));
    public static final Block FUNGYSS_FENCE =  new FenceBlock(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block CHERRYWOOD_FENCE =  new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE));
    public static final Block MYSTERYWOOD_FENCE =  new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE));
    public static final Block EDELWOOD_FENCE =  new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE));
    public static final Block FUNGYSS_FENCE_GATE =  new FenceGateBlock(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block CHERRYWOOD_FENCE_GATE =  new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE));
    public static final Block MYSTERYWOOD_FENCE_GATE =  new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE));
    public static final Block EDELWOOD_FENCE_GATE =  new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE));
//    public static final Pair<Block, Block> FUNGYSS_SIGN  = HELPER.createSignBlock("fungyss", MapColor.WHITE_GRAY, ModWoodTypes.FUNGYSS);
//    public static final Pair<Block, Block> CHERRYWOOD_SIGN  = HELPER.createSignBlock("cherrywood", MapColor.PINK, ModWoodTypes.CHERRYWOOD);
//    public static final Pair<Block, Block> MYSTERYWOOD_SIGN  = HELPER.createSignBlock("mysterywood", MapColor.BROWN, ModWoodTypes.MYSTERYWOOD);
//    public static final Pair<Block, Block> EDELWOOD_SIGN  = HELPER.createSignBlock("edelwood", MapColor.BROWN, ModWoodTypes.EDELWOOD);
    public static final Block EDELWOOD_LADDER =  new FALadderBlock(FabricBlockSettings.copy(Blocks.LADDER));
    public static final Block FUNGYSS_BUTTON =  new FAWoodenButtonBlock(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block CHERRYWOOD_BUTTON =  new FAWoodenButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON));
    public static final Block MYSTERYWOOD_BUTTON =  new FAWoodenButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON));
    public static final Block EDELWOOD_BUTTON =  new FAWoodenButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON));
    public static final Block ARCANE_GOLD_PRESSURE_PLATE =  new FAPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE));
    public static final Block FUNGYSS_PRESSURE_PLATE =  new FAPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block CHERRYWOOD_PRESSURE_PLATE =  new FAPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE));
    public static final Block MYSTERYWOOD_PRESSURE_PLATE =  new FAPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE));
    public static final Block EDELWOOD_PRESSURE_PLATE =  new FAPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE));

    public static final Block HEPHAESTUS_FORGE =  new HephaestusForgeBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN).strength(38.0F, 1200.0F).nonOpaque());

    public static final Block ARCANE_DRAGON_EGG =  new ArcaneDragonEggBlock(FabricBlockSettings.copy(Blocks.DRAGON_EGG).luminance(value -> 5));

    public static final Block ARCANE_CRYSTAL_OBELISK =  new ArcaneCrystalObeliskBlock(FabricBlockSettings.of(Material.STONE).strength(1.0F, 10.0F));
    @NoBlockItem
    public static final Block OBSIDIAN_SKULL =  new ObsidianSkullBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL));
    @NoBlockItem
    public static final Block OBSIDIAN_WALL_SKULL =  new ObsidianWallSkullBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).dropsLike(OBSIDIAN_SKULL));
    @NoBlockItem
    public static final Block ETERNAL_OBSIDIAN_SKULL =  new ObsidianSkullBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL));
    @NoBlockItem
    public static final Block ETERNAL_OBSIDIAN_WALL_SKULL =  new ObsidianWallSkullBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).dropsLike(ETERNAL_OBSIDIAN_SKULL));
    @NoBlockItem
    public static final Block UTREM_JAR =  new UtremJarBlock(FabricBlockSettings.copy(Blocks.GLASS));
    public static final Block PIXIE_UTREM_JAR =  new PixieUtremJarBlock(()->ItemRegistry.PIXIE, FabricBlockSettings.copy(Blocks.GLASS));
    public static final Block CORRUPTED_PIXIE_UTREM_JAR =  new PixieUtremJarBlock(()->ItemRegistry.CORRUPTED_PIXIE, FabricBlockSettings.copy(Blocks.GLASS));
    public static final Block NIPA =  new NipaBlock(FabricBlockSettings.copy(Blocks.LARGE_FERN));
    public static final Block PETRIFIED_ROOT =  new NoFluidOverlayBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque());
    @NoBlockItem
    public static final Block BLACK_HOLE =  new BlackHoleBlock(FabricBlockSettings.copy(Blocks.STONE).strength(2.0F, 8.0F).nonOpaque());
    public static final Block ARCANE_GOLDEN_CHAIN =  new ChainBlock(FabricBlockSettings.copy(Blocks.CHAIN));
    public static final Block YELLOW_ORCHID =  new FlowerBlock(StatusEffects.GLOWING, 10, FabricBlockSettings.copy(Blocks.BLUE_ORCHID));
    @NoBlockItem
    public static final Block GOLDEN_ORCHID =  new GoldenOrchidBlock(FabricBlockSettings.copy(Blocks.BLUE_ORCHID).ticksRandomly());
    public static final Block MAGICAL_FARMLAND =  new MagicalFarmlandBlock(FabricBlockSettings.copy(Blocks.FARMLAND).ticksRandomly());
    @NoBlockItem
    public static final Block STRANGE_ROOT =  new StrangeRootBlock(FabricBlockSettings.copy(Blocks.WHEAT));

//I dont think pots are okay like dis
    @NoBlockItem
    public static final Block POTTED_CHERRYWOOD_SAPLING =  new FlowerPotBlock(CHERRYWOOD_SAPLING, FabricBlockSettings.copy(Blocks.POTTED_OAK_SAPLING));
    @NoBlockItem
    public static final Block POTTED_MYSTERYWOOD_SAPLING =  new FlowerPotBlock(MYSTERYWOOD_SAPLING, FabricBlockSettings.copy(Blocks.POTTED_OAK_SAPLING));
    @NoBlockItem
    public static final Block POTTED_YELLOW_ORCHID =  new FlowerPotBlock(YELLOW_ORCHID, FabricBlockSettings.copy(Blocks.POTTED_OAK_SAPLING));

    private static boolean never(BlockState state, BlockView level, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    public static class Materials {
        public static final Material DARK_NETHER_STAR = (new Material.Builder(MapColor.PURPLE)).build();
        public static final Material RUNE = (new Material.Builder(MapColor.MAGENTA)).build();
    }

    public static void init(){}

}
