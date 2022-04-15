package com.slomaxonical.malum.core.registries.item;

import com.slomaxonical.malum.common.item.misc.MalumFuelItem;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static net.minecraft.item.Items.GLASS_BOTTLE;

public class ItemRegistry implements ItemRegistryContainer {

    public static Item.Settings DEFAULT_SETTINGS() {
        return new Item.Settings().group(MalumCreativeTab.INSTANCE);
    }

    public static Item.Settings SPLINTER_SETTINGS() {
        return new Item.Settings().group(MalumSplinterTab.INSTANCE);
    }

    public static Item.Settings BUILDING_SETTINGS() {
        return new Item.Settings().group(MalumBuildingTab.INSTANCE);
    }

    public static Item.Settings NATURE_SETTINGS() {
        return new Item.Settings().group(MalumNatureTab.INSTANCE);
    }

    public static Item.Settings GEAR_SETTINGS() {
        return new Item.Settings().group(MalumCreativeTab.INSTANCE).maxCount(1);
    }

    public static Item.Settings HIDDEN_SETTINGS() {
        return new Item.Settings().maxCount(1);
    }

    //region runewood
    public static final Item HOLY_SAP = new Item(NATURE_SETTINGS().recipeRemainder(GLASS_BOTTLE));
    public static final Item HOLY_SAPBALL = new Item(NATURE_SETTINGS());

    //region soulwood
    public static final Item UNHOLY_SAP = new Item(NATURE_SETTINGS().recipeRemainder(GLASS_BOTTLE));
    public static final Item UNHOLY_SAPBALL = new Item(NATURE_SETTINGS());

    //endregion

    //region ores
    public static final Item COPPER_NUGGET = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static final Item COAL_FRAGMENT = new MalumFuelItem(new Item.Settings().group(ItemGroup.MISC), 200);
    public static final Item CHARCOAL_FRAGMENT = new MalumFuelItem(new Item.Settings().group(ItemGroup.MISC), 200);

    public static final Item ARCANE_CHARCOAL = new MalumFuelItem(new Item.Settings().group(ItemGroup.MISC), 3200);
    public static final Item BLAZING_QUARTZ = new MalumFuelItem(new Item.Settings().group(ItemGroup.MISC), 1600);
    public static final Item BRILLIANCE_CLUSTER = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item SOULSTONE_CLUSTER = new Item(DEFAULT_SETTINGS());
    public static final Item PROCESSED_SOULSTONE = new Item(DEFAULT_SETTINGS());
   //endregion


    //region materials

    public static final Item HEX_ASH = new Item(DEFAULT_SETTINGS());
    public static final Item SPIRIT_FABRIC = new Item(DEFAULT_SETTINGS());
    public static final Item SPECTRAL_LENS = new Item(DEFAULT_SETTINGS());
    public static final Item POPPET = new Item(DEFAULT_SETTINGS());

    public static final Item HALLOWED_GOLD_INGOT = new Item(DEFAULT_SETTINGS());
    public static final Item HALLOWED_GOLD_NUGGET =new Item(DEFAULT_SETTINGS());
    public static final Item HALLOWED_SPIRIT_RESONATOR = new Item(DEFAULT_SETTINGS());

    public static final Item SOUL_STAINED_STEEL_INGOT = new Item(DEFAULT_SETTINGS());
    public static final Item SOUL_STAINED_STEEL_NUGGET = new Item(DEFAULT_SETTINGS());
    public static final Item STAINED_SPIRIT_RESONATOR = new Item(DEFAULT_SETTINGS());


}
