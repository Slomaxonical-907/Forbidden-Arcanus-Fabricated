package com.slomaxonical.forbidden_arcanus.core.registries.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.item.*;
import com.slomaxonical.forbidden_arcanus.common.item.util.FATools;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;

public class ItemRegistry implements ItemRegistryContainer {

    public static final Item ARCANE_CRYSTAL =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_CRYSTAL_DUST =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLD_INGOT =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLD_NUGGET =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item XPETRIFIED_ORB =  new XpetrifiedOrbItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(16));
    public static final Item ETERNAL_STELLA =  new EternalStellaItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item ORB_OF_TEMPORARY_FLIGHT =  new OrbOfTemporaryFlightItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).rarity(Rarity.UNCOMMON).maxCount(1));
    public static final Item MUNDABITUR_DUST =  new MundabiturDustItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item CORRUPTI_DUST =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DARK_MATTER =  new DarkMatterItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item OBSIDIAN_WITH_IRON =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item OBSIDIAN_INGOT =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item SOUL =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DARK_SOUL =  new DarkSoulItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item PIXIE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item CORRUPTED_PIXIE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item RUNE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DARK_RUNE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item CLOTH =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item CHERRY_PEACH =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).food(ForbiddenFoods.CHERRY_PEACH));
    public static final Item ENDER_PEARL_FRAGMENT =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRAGON_SCALE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item SILVER_DRAGON_SCALE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item GOLDEN_DRAGON_SCALE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item AQUATIC_DRAGON_SCALE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ROTTEN_LEATHER =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item SPECTRAL_EYE_AMULET =  new SpectralEyeAmuletItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).rarity(Rarity.RARE).maxCount(1));
    public static final Item BAT_WING =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).food(ForbiddenFoods.BAT_WING));
    public static final Item BAT_SOUP =  new StewItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1).food(ForbiddenFoods.BAT_SOUP));
    public static final Item TENTACLE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).food(ForbiddenFoods.TENTACLE));
    public static final Item COOKED_TENTACLE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).food(ForbiddenFoods.COOKED_TENTACLE));
    public static final Item STRANGE_ROOT =  new StrangeRootItem(BlockRegistry.STRANGE_ROOT, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).food(ForbiddenFoods.STRANGE_ROOT));
    public static final Item GOLDEN_FEATHER =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item EDELWOOD_STICK =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item WAX =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item SPAWNER_SCRAP =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item QUANTUM_CATCHER =  new QuantumCatcherItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));

    public static final Item SANITY_METER =  new SanityMeterItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item LENS_OF_VERITATIS =  new LensOfVeritatisItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item PURIFYING_SOAP =  new PurifyingSoapItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item WET_PURIFYING_SOAP =  new WetPurifyingSoapItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item OBSIDIAN_SKULL =  new ObsidianSkullItem(()->BlockRegistry.OBSIDIAN_SKULL, ()->BlockRegistry.OBSIDIAN_WALL_SKULL, false, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1).rarity(Rarity.UNCOMMON).fireproof());
    public static final Item OBSIDIAN_SKULL_SHIELD =  new ObsidianSkullShieldItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxDamage(1008).rarity(Rarity.UNCOMMON).fireproof());
    public static final Item ETERNAL_OBSIDIAN_SKULL =  new ObsidianSkullItem(()->BlockRegistry.ETERNAL_OBSIDIAN_SKULL, ()->BlockRegistry.ETERNAL_OBSIDIAN_WALL_SKULL, true, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1).rarity(Rarity.RARE).fireproof());
    public static final Item UTREM_JAR =  new UtremJarItem(()->BlockRegistry.UTREM_JAR, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item AUREAL_BOTTLE =  new AurealBottleItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(16));
    public static final Item ARCANE_CRYSTAL_DUST_SPECK =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_BONE_MEAL =  new ArcaneBoneMealItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ZOMBIE_ARM =  new ZombieArmItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item SHINY_ZOMBIE_ARM =  new ZombieArmItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item TEST_TUBE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(16));
    public static final Item BLOOD_TEST_TUBE =  new BloodTestTubeItem(new FabricItemSettings().maxCount(1));
    public static final Item BLACKSMITH_GAVEL_HEAD =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(16));
    public static final Item WOODEN_BLACKSMITH_GAVEL =  new BlacksmithGavelItem(ToolMaterials.WOOD, 7, -3.25F, 1, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item STONE_BLACKSMITH_GAVEL =  new BlacksmithGavelItem(ToolMaterials.STONE, 7, -3.25F, 3, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item GOLDEN_BLACKSMITH_GAVEL =  new BlacksmithGavelItem(ToolMaterials.GOLD, 7, -3.25F, 2, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item IRON_BLACKSMITH_GAVEL =  new BlacksmithGavelItem(ToolMaterials.IRON, 7, -3.25F, 10, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item DIAMOND_BLACKSMITH_GAVEL =  new BlacksmithGavelItem(ToolMaterials.DIAMOND, 7, -3.25F, 30, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item NETHERITE_BLACKSMITH_GAVEL =  new BlacksmithGavelItem(ToolMaterials.NETHERITE, 7, -3.25F, 60, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item ARCANE_GOLDEN_BLACKSMITH_GAVEL =  new BlacksmithGavelItem(ForbiddenToolMaterials.ARCANE_GOLDEN, 7, -3.25F, 15, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item REINFORCED_ARCANE_GOLDEN_BLACKSMITH_GAVEL =  new BlacksmithGavelItem(ForbiddenToolMaterials.REINFORCED_ARCANE_GOLDEN, 7, -3.25F, 80, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item STELLARITE_PIECE =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DARK_NETHER_STAR =  new SimpleFoiledItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).rarity(Rarity.RARE));
    public static final Item SMELTER_PRISM =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static Item EDELWOOD_BUCKET = new EdelwoodBucketItem(Fluids.EMPTY, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(16));
    public static final Item EDELWOOD_WATER_BUCKET =  new EdelwoodBucketItem(Fluids.WATER, 4, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_LAVA_BUCKET =  new EdelwoodBucketItem(Fluids.LAVA, 3, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_MILK_BUCKET =  new EdelwoodMilkBucketItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_POWDER_SNOW_BUCKET =  new SolidEdelwoodBucketItem(Blocks.POWDER_SNOW, SoundEvents.BLOCK_POWDER_SNOW_BREAK, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_PUFFERFISH_BUCKET =  new EdelwoodMobBucketItem(EntityType.PUFFERFISH,  Fluids.WATER,  SoundEvents.ITEM_BUCKET_EMPTY_FISH, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_SALMON_BUCKET =  new EdelwoodMobBucketItem(EntityType.SALMON, Fluids.WATER,SoundEvents.ITEM_BUCKET_EMPTY_FISH, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_COD_BUCKET =  new EdelwoodMobBucketItem(EntityType.COD,  Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_TROPICAL_FISH_BUCKET =  new EdelwoodMobBucketItem(EntityType.TROPICAL_FISH, Fluids.WATER,  SoundEvents.ITEM_BUCKET_EMPTY_FISH, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_BAT_BUCKET =  new EdelwoodMobBucketItem(EntityType.BAT,  Fluids.EMPTY, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_SQUID_BUCKET =  new EdelwoodMobBucketItem(EntityType.SQUID,  Fluids.WATER,  SoundEvents.ITEM_BUCKET_EMPTY_FISH, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_GLOW_SQUID_BUCKET =  new EdelwoodMobBucketItem(EntityType.GLOW_SQUID,  Fluids.WATER,  SoundEvents.ITEM_BUCKET_EMPTY_FISH, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_MAGMA_CUBE_BUCKET =  new EdelwoodMobBucketItem(EntityType.MAGMA_CUBE,  Fluids.EMPTY, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_SLIME_BUCKET =  new EdelwoodMobBucketItem(EntityType.SLIME,  Fluids.EMPTY, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_CHICKEN_BUCKET =  new EdelwoodMobBucketItem(EntityType.CHICKEN,  Fluids.EMPTY, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_AXOLOTL_BUCKET =  new EdelwoodMobBucketItem(EntityType.AXOLOTL,  Fluids.WATER,  SoundEvents.ITEM_BUCKET_EMPTY_AXOLOTL, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item EDELWOOD_MUSHROOM_STEW_BUCKET =  new EdelwoodSoupBucketItem(Items.MUSHROOM_STEW, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1).food(FoodComponents.MUSHROOM_STEW));
    public static final Item EDELWOOD_SUSPICIOUS_STEW_BUCKET =  new EdelwoodSuspiciousStewBucketItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1).food(FoodComponents.SUSPICIOUS_STEW));
    public static final Item EDELWOOD_BEETROOT_SOUP_BUCKET =  new EdelwoodSoupBucketItem( Items.BEETROOT_SOUP, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1).food(FoodComponents.BEETROOT_SOUP));
    public static final Item EDELWOOD_BAT_SOUP_BUCKET =  new EdelwoodSoupBucketItem(BAT_SOUP, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1).food(ForbiddenFoods.BAT_SOUP));
    public static final Item BOOM_ARROW =  new ModArrowItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_ARROW =  new ModArrowItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item EDELWOOD_OIL =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).recipeRemainder(Items.GLASS).maxCount(16));
    public static final Item GOLDEN_ORCHID_SEEDS = new AliasedBlockItem(BlockRegistry.GOLDEN_ORCHID, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));

    public static final Item SOUL_EXTRACTOR =  new SoulExtractorItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxDamage(128));
    public static final Item SLIMEC_PICKAXE =  new SlimecPickaxeItem(ForbiddenToolMaterials.SLIMEC, 1, -2.5F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_STAFF =  new Item(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item DRACO_ARCANUS_SWORD =  new SwordItem(ForbiddenToolMaterials.DRACO_ARCANUS, 4, -2.2F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_SHOVEL =  new ShovelItem(ForbiddenToolMaterials.DRACO_ARCANUS, 2.5F, -2.8F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_PICKAXE =  new FATools.Axe(ForbiddenToolMaterials.DRACO_ARCANUS, 2, -2.6F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_AXE =  new FATools.Axe(ForbiddenToolMaterials.DRACO_ARCANUS, 6, -2.8F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_HOE =  new FATools.Hoe(ForbiddenToolMaterials.DRACO_ARCANUS, -4, 1, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_SCEPTER =  new DracoArcanusScepterItem(new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item ARCANE_GOLDEN_SWORD =  new SwordItem(ForbiddenToolMaterials.ARCANE_GOLDEN, 3, -2.4F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLDEN_SHOVEL =  new ShovelItem(ForbiddenToolMaterials.ARCANE_GOLDEN, 1.5F, -3.0F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLDEN_PICKAXE =  new FATools.Pickaxe(ForbiddenToolMaterials.ARCANE_GOLDEN, 1, -2.8F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLDEN_AXE =  new FATools.Axe(ForbiddenToolMaterials.ARCANE_GOLDEN, 5, -3.0F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLDEN_HOE =  new FATools.Hoe(ForbiddenToolMaterials.ARCANE_GOLDEN, -3, 0, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item REINFORCED_ARCANE_GOLDEN_SWORD =  new SwordItem(ForbiddenToolMaterials.REINFORCED_ARCANE_GOLDEN, 3, -2.4F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item REINFORCED_ARCANE_GOLDEN_SHOVEL =  new ShovelItem(ForbiddenToolMaterials.REINFORCED_ARCANE_GOLDEN, 1.5F, -3.0F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item REINFORCED_ARCANE_GOLDEN_PICKAXE =  new FATools.Pickaxe(ForbiddenToolMaterials.REINFORCED_ARCANE_GOLDEN, 1, -2.8F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item REINFORCED_ARCANE_GOLDEN_AXE =  new FATools.Axe(ForbiddenToolMaterials.REINFORCED_ARCANE_GOLDEN, 5, -3.0F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item REINFORCED_ARCANE_GOLDEN_HOE =  new FATools.Hoe(ForbiddenToolMaterials.REINFORCED_ARCANE_GOLDEN, -3, 0, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item MYSTICAL_DAGGER =  new MysticalDaggerItem(ForbiddenToolMaterials.MYSTICAL_DAGGER, 2.5F, -0.3F, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP).maxCount(1));
    public static final Item DRACO_ARCANUS_HELMET =  new ArmorItem(ModArmorMaterials.DRACO_ARCANUS, EquipmentSlot.HEAD, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_CHESTPLATE =  new ArmorItem(ModArmorMaterials.DRACO_ARCANUS, EquipmentSlot.CHEST, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_LEGGINGS =  new ArmorItem(ModArmorMaterials.DRACO_ARCANUS, EquipmentSlot.LEGS, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item DRACO_ARCANUS_BOOTS =  new ArmorItem(ModArmorMaterials.DRACO_ARCANUS, EquipmentSlot.FEET, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item TYR_HELMET =  new ArmorItem(ModArmorMaterials.TYR, EquipmentSlot.HEAD, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item TYR_CHESTPLATE =  new ArmorItem(ModArmorMaterials.TYR, EquipmentSlot.CHEST, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item TYR_LEGGINGS =  new ArmorItem(ModArmorMaterials.TYR, EquipmentSlot.LEGS, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item TYR_BOOTS =  new ArmorItem(ModArmorMaterials.TYR, EquipmentSlot.FEET, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item MORTEM_HELMET =  new MortemArmorItem(ModArmorMaterials.MORTEM, EquipmentSlot.HEAD, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item MORTEM_CHESTPLATE =  new MortemArmorItem(ModArmorMaterials.MORTEM, EquipmentSlot.CHEST, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item MORTEM_LEGGINGS =  new MortemArmorItem(ModArmorMaterials.MORTEM, EquipmentSlot.LEGS, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item MORTEM_BOOTS =  new MortemArmorItem(ModArmorMaterials.MORTEM, EquipmentSlot.FEET, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLD_HELMET =  new ArmorItem(ModArmorMaterials.ARCANE_GOLD, EquipmentSlot.HEAD, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLD_CHESTPLATE =  new ArmorItem(ModArmorMaterials.ARCANE_GOLD, EquipmentSlot.CHEST, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLD_LEGGINGS =  new ArmorItem(ModArmorMaterials.ARCANE_GOLD, EquipmentSlot.LEGS, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));
    public static final Item ARCANE_GOLD_BOOTS =  new ArmorItem(ModArmorMaterials.ARCANE_GOLD, EquipmentSlot.FEET, new FabricItemSettings().group(ForbiddenArcanus.TABBED_FORBIDDEN_GROUP));

//    public static class Stacks {
//        public static final ItemStack LENS_OF_VERITATIS = new ItemStack(ItemRegistry.LENS_OF_VERITATIS);
//        public static final ItemStack ORB_OF_TEMPORARY_FLIGHT = new ItemStack(ItemRegistry.ORB_OF_TEMPORARY_FLIGHT);
//        public static final ItemStack SANITY_METER = new ItemStack(ItemRegistry.SANITY_METER);
//        public static final ItemStack ARCANE_CRYSTAL_DUST_SPECK = new ItemStack(ItemRegistry.ARCANE_CRYSTAL_DUST_SPECK);
//        public static final ItemStack OBSIDIAN_SKULL = new ItemStack(ItemRegistry.OBSIDIAN_SKULL);
//        public static final ItemStack ETERNAL_OBSIDIAN_SKULL = new ItemStack(ItemRegistry.ETERNAL_OBSIDIAN_SKULL);
//        public static final ItemStack OBSIDIAN_SKULL_SHIELD = new ItemStack(ItemRegistry.OBSIDIAN_SKULL_SHIELD);
//    }

}
