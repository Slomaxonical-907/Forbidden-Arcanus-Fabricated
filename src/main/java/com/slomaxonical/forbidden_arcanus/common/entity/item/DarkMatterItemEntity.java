package com.slomaxonical.forbidden_arcanus.common.entity.item;

import com.slomaxonical.forbidden_arcanus.common.item.DarkMatterItem;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DarkMatterItemEntity extends ItemEntity {
    public DarkMatterItemEntity(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }
    public DarkMatterItemEntity(final World w, final double x, final double y, final double z, final ItemStack is) {
        super(w, x, y, z, is);
    }

    @Override
    public void tick() {
        // Prevent the entity from despawning?could be cool Thx AE2 for this
//        ItemEntityAccessor accessor = (ItemEntityAccessor) this;
//        if (!this.world.isRemote && accessor.getAge() >= 1000) {
//            accessor.setAge(0);
//        }

        super.tick();

        final ItemStack is = this.getStack();
        final Item dm = is.getItem();

        if (!(dm instanceof DarkMatterItem)) {
            return;
        }

        blackHoleCheck(this);
    }
    public void blackHoleCheck(@NotNull ItemEntity entity) {
        World world = entity.getEntityWorld();
        BlockPos pos = entity.getBlockPos();

        List<ItemEntity> itemEntities = world.getNonSpectatingEntities(ItemEntity.class, new Box(pos).expand(0.5));

        for (ItemEntity itemEntity : itemEntities) {
            if (itemEntity.getStack().isOf(ItemRegistry.CORRUPTI_DUST) && world.getBlockState(pos).isAir()) {
                entity.getStack().decrement(1);
                itemEntity.getStack().decrement(1);

                world.setBlockState(pos, BlockRegistry.BLACK_HOLE.getDefaultState());
            }
        }
    }
}
