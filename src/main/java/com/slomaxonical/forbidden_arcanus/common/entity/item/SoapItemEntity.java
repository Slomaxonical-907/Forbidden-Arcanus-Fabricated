package com.slomaxonical.forbidden_arcanus.common.entity.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.item.PurifyingSoapItem;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.capability.counter.SimpleCounter;

import java.util.Random;

public class SoapItemEntity extends ItemEntity {
    private static final SimpleCounter COUNTER = new SimpleCounter(new Identifier(ForbiddenArcanus.MOD_ID, "wet_timer"));
    int waitTime = 20 + (int)(new Random().nextFloat()*100);

    public SoapItemEntity(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }
    public SoapItemEntity(final World w, final double x, final double y, final double z, final ItemStack is) {
        super(w, x, y, z, is);
    }

    @Override
    public void tick() {
        super.tick();

        final Item ps = this.getStack().getItem();

        if (!(ps instanceof PurifyingSoapItem)) return;

        if (this.isWet()) {
            if (COUNTER.isActive()) {
                COUNTER.increase();
            } else {
                COUNTER.setActive(true);
            }
            if (COUNTER.getValue() > waitTime) {
                this.setStack(new ItemStack(ItemRegistry.WET_PURIFYING_SOAP));
                COUNTER.resetTimer();
            }
        }
    }

}
