package com.slomaxonical.malum.common.block.misc;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.Direction;

import java.awt.*;

public class MalumLeavesBlock extends LeavesBlock {
    public static final IntProperty COLOR = IntProperty.of("color",0,4);
    public final Color maxColor;
    public final Color minColor;
    public MalumLeavesBlock(Settings settings,Color maxColor,Color minColor)
    {
        super(settings);
        this.maxColor = maxColor;
        this.minColor = minColor;
        this.setDefaultState(this.stateManager.getDefaultState().with(COLOR, 0));
    }
}
