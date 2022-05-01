package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.item.filler.TargetItemGroupFiller;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class UtremJarItem extends Item {
    public UtremJarItem(Supplier<Block> utremJar, Settings settings) {
        super(settings);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        new TargetItemGroupFiller(() -> BlockRegistry.PIXIE_UTREM_JAR.asItem(), true).fill(new ItemStack(this), group, items);
        new TargetItemGroupFiller(() -> BlockRegistry.PIXIE_UTREM_JAR.asItem(), true).fill(this.withFluid(Fluids.WATER), group, items);
        new TargetItemGroupFiller(() -> BlockRegistry.PIXIE_UTREM_JAR.asItem(), true).fill(this.withFluid(Fluids.LAVA), group, items);
    }
    private ItemStack withFluid(Fluid fluid) {
        ItemStack stack = new ItemStack(this);

        NbtCompound tag = new NbtCompound();
        tag.putString("FluidName", Objects.requireNonNull(fluid.getRegistryEntry()).toString());
        tag.putInt("Amount", 1000);

        stack.getOrCreateSubNbt("BlockEntityTag").put("Fluid", tag);

        return stack;
    }

    public Fluid getFluid(ItemStack stack) {
        if (stack.hasNbt()) {
            NbtCompound tag = stack.getOrCreateSubNbt("BlockEntityTag");

            if (tag.contains("Fluid")) {
                NbtCompound compound = tag.getCompound("Fluid");

                return DefaultedRegistry.FLUID.get(new Identifier(compound.getString("FluidName")));
            }
        }
        return Fluids.EMPTY;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        Fluid fluid = this.getFluid(stack);

        if (fluid != Fluids.EMPTY) {
            tooltip.add(new TranslatableText(fluid.getRegistryEntry().toString()).formatted(Formatting.GRAY));
        }
    }
}
