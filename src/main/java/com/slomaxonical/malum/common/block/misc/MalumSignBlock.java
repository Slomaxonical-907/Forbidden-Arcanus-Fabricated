package com.slomaxonical.malum.common.block.misc;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.SignType;
import net.minecraft.util.math.BlockPos;

public class MalumSignBlock extends SignBlock implements BlockEntityProvider {
    public static final IntProperty ROTATION = Properties.ROTATION;

    public MalumSignBlock(Settings settings, SignType type) {
        super(settings,type);
    }

//    @Override
//    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//        return new MalumSignTileEntity(pos,state);
//    }

}
