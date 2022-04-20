package com.slomaxonical.forbidden_arcanus.core.helper;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.*;

public class FAUtils {
    public static Team createTeam(Scoreboard scoreboard, String name, Formatting color) {
        if (scoreboard.getTeamNames().contains(name)) {
            return scoreboard.getPlayerTeam(name);
        } else {
            Team team = scoreboard.addTeam(name);
            team.setDisplayName(new LiteralText(name));
            team.setColor(color);
            return team;
        }
    }

    public static void removeTeam(Scoreboard scoreboard, Team team) {
        if (scoreboard.getTeamNames().contains(team.getName())) {
            scoreboard.removeTeam(team);
        }
    }
    //EnchantmentHelper
    public static ItemStack transferEnchantments(ItemStack oldStack, ItemStack newStack) {
        if (!EnchantmentHelper.get(oldStack).isEmpty()) {
            Map<Enchantment,Integer> var10000 = EnchantmentHelper.get(oldStack);
            Objects.requireNonNull(newStack);
            var10000.forEach(newStack::addEnchantment);
        }

        return newStack;
    }
    //VoxelHelper
    public static EnumMap<Direction.Axis, VoxelShape> rotateAxis(VoxelShape shape) {
        return new EnumMap(ImmutableMap.of(Direction.Axis.Y, shape, Direction.Axis.X, rotateAxis(shape, Direction.Axis.X), Direction.Axis.Z, rotateAxis(shape, Direction.Axis.Z)));
    }
    public static VoxelShape rotateAxis(VoxelShape shape, Direction.Axis axis) {
        if (axis == Direction.Axis.Y) {
            return shape;
        } else {
            Set<VoxelShape> rotatedShapes = new HashSet();
            shape.forEachBox((x1, y1, z1, x2, y2, z2) -> {
                y1 *= 16.0D;
                y2 *= 16.0D;
                x1 *= 16.0D;
                x2 *= 16.0D;
                z1 *= 16.0D;
                z2 *= 16.0D;
                if (axis == Direction.Axis.X) {
                    rotatedShapes.add(Block.createCuboidShape(y1, x1, z1, y2, x2, z2));
                } else {
                    rotatedShapes.add(Block.createCuboidShape(x1, z1, y1, x2, z2, y2));
                }

            });
            return (VoxelShape)rotatedShapes.stream().reduce((v1, v2) -> {
                return VoxelShapes.combine(v1, v2, BooleanBiFunction.OR);
            }).get();
        }
    }
    public static VoxelShape rotateShape(VoxelShape shape, Direction direction) {
        List<VoxelShape> rotatedPieces = new ArrayList();
        Vec3d vec3 = new Vec3d(-0.5D, -0.5D, -0.5D);
        shape.getBoundingBoxes().forEach((box) -> {
            box = box.offset(vec3.x, vec3.y, vec3.z);
            Box var10000;
            switch(direction) {
                case WEST:
                    var10000 = new Box(box.minY, -box.minZ, -box.minX, box.maxY, -box.maxZ, -box.maxX);
                    break;
                case NORTH:
                    var10000 = new Box(box.minX, -box.minZ, box.minY, box.maxX, -box.maxZ, box.maxY);
                    break;
                case SOUTH:
                    var10000 = new Box(-box.minX, -box.minZ, -box.minY, -box.maxX, -box.maxZ, -box.maxY);
                    break;
                case EAST:
                    var10000 = new Box(-box.minY, -box.minZ, box.minX, -box.maxY, -box.maxZ, box.maxX);
                    break;
                case DOWN:
                    var10000 = box;
                    break;
                case UP:
                    var10000 = new Box(box.minX, -box.minY, -box.minZ, box.maxX, -box.maxY, -box.maxZ);
                    break;
                default:
                    throw new IncompatibleClassChangeError();
            }

            Box rotated = var10000;
            rotatedPieces.add(VoxelShapes.cuboid(rotated.offset(-vec3.x, -vec3.z, -vec3.z)));
        });
        return (VoxelShape)rotatedPieces.stream().reduce(VoxelShapes.empty(), VoxelShapes::union);
    }
}
