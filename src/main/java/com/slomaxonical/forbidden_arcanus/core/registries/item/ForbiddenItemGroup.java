package com.slomaxonical.forbidden_arcanus.core.registries.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ForbiddenItemGroup extends OwoItemGroup {
    public ForbiddenItemGroup(Identifier id) {
        super(id);
    }
    @Override
    protected void setup() {
        this.addTab(Icon.of(ItemRegistry.ARCANE_CRYSTAL),"wot",TagKey.of(Registry.ITEM_KEY, new Identifier(ForbiddenArcanus.ID,"items_tab")));
        this.addTab(Icon.of(BlockRegistry.ARCANE_CHISELED_DARKSTONE),"wow",TagKey.of(Registry.ITEM_KEY, new Identifier(ForbiddenArcanus.ID,"blocks_tag")));
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(BlockRegistry.HEPHAESTUS_FORGE);
    }
}
