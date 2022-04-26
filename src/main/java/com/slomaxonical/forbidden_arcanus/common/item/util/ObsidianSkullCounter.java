package com.slomaxonical.forbidden_arcanus.common.item.util;

import com.slomaxonical.forbidden_arcanus.common.item.ObsidianSkullItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.valhelsia.valhelsia_core.common.capability.counter.SimpleCounter;

public class ObsidianSkullCounter extends SimpleCounter {
    public ObsidianSkullCounter(Identifier name) {
        super(name);
    }

    @Override
    public void tick(NbtCompound tag) {
        String damageSource = tag.getString("DamageSource");
        boolean flag = ObsidianSkullItem.DAMAGE_SOURCES.stream().anyMatch(source -> source.getName().equals(damageSource));

        if (flag && this.getValue() < ObsidianSkullItem.OBSIDIAN_SKULL_PROTECTION_TIME) {
            this.increase();
        } else if (!flag && this.getValue() > 0) {
            this.decrease();
        }
    }
}
