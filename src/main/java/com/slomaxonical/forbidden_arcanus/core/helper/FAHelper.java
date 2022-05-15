package com.slomaxonical.forbidden_arcanus.core.helper;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;

public class FAHelper {
    public static Vec3d blockPosToVector(BlockPos pos) {
        return blockPosToVector(pos, 0);
    }

    public static Vec3d blockPosToVector(BlockPos pos, double offset) {
        return new Vec3d(pos.getX() + offset, pos.getY() + offset, pos.getZ() + offset);
    }
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
    public static void addItemParticles(World level, ItemStack stack, BlockPos pos, int count) {
        Random random = level.getRandom();

        for(int i = 0; i < count; i++) {
            Vec3d offset = new Vec3d((random.nextFloat() - 0.5D) * 0.1D, random.nextFloat() * 0.1D + 0.1D, 0.0D);
            Vec3d vector = new Vec3d((random.nextFloat() - 0.5D) * 0.3D, -random.nextFloat() * 0.6D - 0.3D, 0.6D).add(blockPosToVector(pos));

            if (level instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, stack), vector.x, vector.y, vector.z, 1, offset.x, offset.y + 0.05D, offset.z, 0.0D);
            } else {
                level.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, stack), vector.x, vector.y, vector.z, offset.x, offset.y + 0.05D, offset.z);
            }
        }
    }
//    //Item stack Helper
//    public static ItemStack transferEnchantments(ItemStack oldStack, ItemStack newStack) {
//        if (!EnchantmentHelper.get(oldStack).isEmpty()) {
//            Map<Enchantment,Integer> var10000 = EnchantmentHelper.get(oldStack);
//            Objects.requireNonNull(newStack);
//            var10000.forEach(newStack::addEnchantment);
//        }
//
//        return newStack;
//    }
//    public static void decrementStack(@Nullable PlayerEntity player, ItemStack stack) {
//        decrementStack(player, stack, 1);
//    }
//
//    public static void decrementStack(@Nullable PlayerEntity player, ItemStack stack, int count) {
//        if (player == null || !player.isCreative()) {
//            stack.decrement(count);
//        }
//
//    }
//
//    //VoxelShape Helper
//    public static VoxelShape rotateShape(VoxelShape shape, Direction direction) {
//        List<VoxelShape> rotatedPieces = new ArrayList();
//        Vec3d vec3 = new Vec3d(-0.5D, -0.5D, -0.5D);
//        shape.getBoundingBoxes().forEach((aabb) -> {
//            aabb = aabb.offset(vec3.x, vec3.y, vec3.z);
//            Box var10000;
//            switch(direction) {
//                case WEST:
//                    var10000 = new Box(aabb.minY, -aabb.minZ, -aabb.minX, aabb.maxY, -aabb.maxZ, -aabb.maxX);
//                    break;
//                case NORTH:
//                    var10000 = new Box(aabb.minX, -aabb.minZ, aabb.minY, aabb.maxX, -aabb.maxZ, aabb.maxY);
//                    break;
//                case SOUTH:
//                    var10000 = new Box(-aabb.minX, -aabb.minZ, -aabb.minY, -aabb.maxX, -aabb.maxZ, -aabb.maxY);
//                    break;
//                case EAST:
//                    var10000 = new Box(-aabb.minY, -aabb.minZ, aabb.minX, -aabb.maxY, -aabb.maxZ, aabb.maxX);
//                    break;
//                case DOWN:
//                    var10000 = aabb;
//                    break;
//                case UP:
//                    var10000 = new Box(aabb.minX, -aabb.minY, -aabb.minZ, aabb.maxX, -aabb.maxY, -aabb.maxZ);
//                    break;
//                default:
//                    throw new IncompatibleClassChangeError();
//            }
//
//            Box rotated = var10000;
//            rotatedPieces.add(VoxelShapes.cuboid(rotated.offset(-vec3.x, -vec3.z, -vec3.z)));
//        });
//        return (VoxelShape)rotatedPieces.stream().reduce(VoxelShapes.empty(), VoxelShapes::union);
//    }
//    public static EnumMap<Direction.Axis, VoxelShape> rotateAxis(VoxelShape shape) {
//        return new EnumMap(ImmutableMap.of(Direction.Axis.Y, shape, Direction.Axis.X, rotateAxis(shape, Direction.Axis.X), Direction.Axis.Z, rotateAxis(shape, Direction.Axis.Z)));
//    }
//
//    public static VoxelShape rotateAxis(VoxelShape shape, Direction.Axis axis) {
//        if (axis == Direction.Axis.Y) {
//            return shape;
//        } else {
//            Set<VoxelShape> rotatedShapes = new HashSet();
//            shape.forEachBox((x1, y1, z1, x2, y2, z2) -> {
//                y1 *= 16.0D;
//                y2 *= 16.0D;
//                x1 *= 16.0D;
//                x2 *= 16.0D;
//                z1 *= 16.0D;
//                z2 *= 16.0D;
//                if (axis == Direction.Axis.X) {
//                    rotatedShapes.add(Block.createCuboidShape(y1, x1, z1, y2, x2, z2));
//                } else {
//                    rotatedShapes.add(Block.createCuboidShape(x1, z1, y1, x2, z2, y2));
//                }
//
//            });
//            return (VoxelShape)rotatedShapes.stream().reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
//        }
//    }
//
//    public static VoxelShape combineAll(VoxelShape... shapes) {
//        VoxelShape result = VoxelShapes.empty();
//        VoxelShape[] var2 = shapes;
//        int var3 = shapes.length;
//
//        for(int var4 = 0; var4 < var3; ++var4) {
//            VoxelShape shape = var2[var4];
//            result = VoxelShapes.combineAndSimplify(result, shape, BooleanBiFunction.OR);
//        }
//
//        return result.simplify();
//    }
//
}
