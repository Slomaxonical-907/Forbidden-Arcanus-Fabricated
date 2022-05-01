package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.helper.FATags;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import dev.architectury.registry.registries.Registries;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class QuantumCatcherItem extends Item {
    public QuantumCatcherItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return super.useOnBlock(context);
    }
//    public ActionResult onEntityInteract(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        World world = player.getEntityWorld();

        if (target instanceof PlayerEntity || target.getType().isIn(FATags.EntityTypes.QUANTUM_CATCHER_BLACKLISTED)) {
            return ActionResult.PASS;
        }

        if (this.getEntity(stack, world) == null && target.isAlive()) {
            if (stack.getCount() != 1) {
                ItemStackUtils.shrinkStack(player, stack);

                ItemStack newStack = new ItemStack(ItemRegistry.QUANTUM_CATCHER);
                this.setEntity(target, newStack);

                if (!player.giveItemStack(newStack)) {
                    player.dropItem(newStack, false);
                }
            } else {
                this.setEntity(target, stack);
            }

            target.discard();

            return ActionResult.success(world.isClient());
        }
            return super.useOnEntity(stack, player, target, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (world != null && getEntity(stack, world) != null)  {
            Entity entity = this.getEntity(stack, world);

            if (entity != null) {
                MutableText textComponent = new TranslatableText("tooltip.forbidden_arcanus.entity").append(": ").append(new LiteralText(Objects.requireNonNull(entity.getType().getName()).toString()));

                if (this.getEntityName(stack) != null)  {
                    textComponent.append(" (").append(Objects.requireNonNull(this.getEntityName(stack))).append(")");
                }

                textComponent.formatted(Formatting.GRAY);

                tooltip.add(textComponent);
            }
        }
    }
    private void setEntity(Entity entity, ItemStack stack) {
        entity.stopRiding();
        entity.removeAllPassengers();

        NbtCompound entityTag = new NbtCompound();

        if (entity.getType().getName() == null) return;

        entityTag.putString("entity", entity.getType().getName().toString());
        if (entity.hasCustomName()) {
            entityTag.putString("name", Objects.requireNonNull(entity.getCustomName()).getString());
        }
        entity.saveNbt(entityTag);

        NbtCompound itemNBT = stack.getOrCreateNbt();
        itemNBT.put("entity", entityTag);
    }

    private Entity getEntity(ItemStack stack, World world) {
        NbtCompound itemTag = stack.getNbt();

        if (itemTag == null) return null;

        NbtCompound entityTag = itemTag.getCompound("entity");
        //is there an alternative for forge registries
        EntityType<?> entityType = DefaultedRegistry.ENTITY_TYPE.get(new Identifier(entityTag.getString("entity")));

        if (entityType == null) return null;

        Entity entity = entityType.create(world);

        if (world instanceof ServerWorld && entity != null) {
            entity.readNbt(entityTag);
        }

        return entity;
    }

    private Text getEntityName(ItemStack stack) {
        NbtCompound itemTag = stack.getNbt();

        if (itemTag == null) return null;

        if (itemTag.contains("entity")) {
            NbtCompound entityTag = itemTag.getCompound("entity");

            if (entityTag.contains("name")) {
                return new LiteralText(entityTag.getString("name"));
            }
        }
        return null;
    }

    private void clearEntity(ItemStack stack) {
        stack.setNbt(null);
    }
}
