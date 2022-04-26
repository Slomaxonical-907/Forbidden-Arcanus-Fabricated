package com.slomaxonical.forbidden_arcanus.common.entity.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.item.WetPurifyingSoapItem;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.capability.counter.SimpleCounter;

public class WetSoapItemEntity extends ItemEntity {
    private static final SimpleCounter COUNTER = new SimpleCounter(new Identifier(ForbiddenArcanus.MOD_ID, "dry_timer"));

    public WetSoapItemEntity(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }
    public WetSoapItemEntity(final World w, final double x, final double y, final double z, final ItemStack is) {
        super(w, x, y, z, is);
    }
    @Override
    public void tick() {
        super.tick();

        final Item wps = this.getStack().getItem();
        if (!(wps instanceof WetPurifyingSoapItem)) return;

        if (WetPurifyingSoapItem.inUltraWarm(this)) {
            this.setStack(new ItemStack(ItemRegistry.PURIFYING_SOAP));
        } else {
            if (this.isWet()) {

                if (this.isWet()) {
                    if (COUNTER.isActive()) {
                        COUNTER.resetTimer();
                    } else {
                        COUNTER.setActive(true);
                    }
                } else {
                    COUNTER.increase();
                    if (COUNTER.getValue() >= 3600) { //this currently gets reset on restart possibly use CardinalComponents
                        this.setStack(new ItemStack(ItemRegistry.PURIFYING_SOAP));
                    }
                }
            }
        }
    }

}
