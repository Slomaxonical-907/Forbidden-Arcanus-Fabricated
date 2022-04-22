package com.slomaxonical.forbidden_arcanus.core.helper;

import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.slomaxonical.forbidden_arcanus.ForbiddenArcanus.MOD_ID;

public class DataHelper {
    public static Vec3d randPos(BlockPos pos, Random rand, double min, double max) {
        double x = MathHelper.nextDouble(rand, min, max) + pos.getX();
        double y = MathHelper.nextDouble(rand, min, max) + pos.getY();
        double z = MathHelper.nextDouble(rand, min, max) + pos.getZ();
        return new Vec3d(x, y, z);
    }

    public static Identifier prefix(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static <T, K extends Collection<T>> K reverseOrder(Supplier<K> reversed, Collection<T> items) {
        ArrayList<T> original = new ArrayList<>(items);
        K newCollection = reversed.get();
        for (int i = items.size()-1; i >= 0 ; i--)
        {
            newCollection.add(original.get(i));
        }
        return newCollection;
    }

    public static String toTitleCase(String givenString, String regex) {
        String[] stringArray = givenString.split(regex);
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : stringArray) {
            stringBuilder.append(Character.toUpperCase(string.charAt(0))).append(string.substring(1)).append(regex);
        }
        return stringBuilder.toString().trim().replaceAll(regex, " ").substring(0, stringBuilder.length() - 1);
    }

    public static int[] nextInts(Random rand, int count, int range) {
        int[] ints = new int[count];
        for (int i = 0; i < count; i++) {
            while (true) {
                int nextInt = rand.nextInt(range);
                if (Arrays.stream(ints).noneMatch(j -> j == nextInt)) {
                    ints[i] = nextInt;
                    break;
                }
            }
        }
        return ints;
    }

    public static <T> boolean hasDuplicate(T[] things) {
        Set<T> thingSet = new HashSet<>();
        return !Arrays.stream(things).allMatch(thingSet::add);
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> Collection<T> takeAll(Collection<? extends T> src, T... items) {
        List<T> ret = Arrays.asList(items);
        for (T item : items) {
            if (!src.contains(item)) {
                return Collections.emptyList();
            }
        }
        if (!src.removeAll(ret)) {
            return Collections.emptyList();
        }
        return ret;
    }

    public static <T> Collection<T> takeAll(Collection<T> src, Predicate<T> pred) {
        List<T> ret = new ArrayList<>();

        Iterator<T> iter = src.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (pred.test(item)) {
                iter.remove();
                ret.add(item);
            }
        }

        if (ret.isEmpty()) {
            return Collections.emptyList();
        }
        return ret;
    }

    public static <T> Collection<T> getAll(Collection<? extends T> src, T... items) {
        return List.copyOf(getAll(src, t -> Arrays.stream(items).anyMatch(tAgain -> tAgain.getClass().isInstance(t))));
    }

    public static <T> Collection<T> getAll(Collection<T> src, Predicate<T> pred) {
        return src.stream().filter(pred).collect(Collectors.toList());
    }

    public static Vec3d circlePosition(Vec3d pos, float distance, float current, float total) {
        double angle = current / total * (Math.PI * 2);
        double dx2 = (distance * Math.cos(angle));
        double dz2 = (distance * Math.sin(angle));

        Vec3d vector = new Vec3d(dx2, 0, dz2);
        double x = vector.x * distance;
        double z = vector.z * distance;
        return pos.add(new Vec3d(x, 0, z));
    }

    public static Vec3d rotatedCirclePosition(Vec3d pos, float distance, float current, float total, long gameTime, float time) {
        return rotatedCirclePosition(pos, distance, distance, current, total, gameTime, time);
    }

    public static Vec3d rotatedCirclePosition(Vec3d pos, float distanceX, float distanceZ, float current, float total, long gameTime, float time) {
        double angle = current / total * (Math.PI * 2);
        angle += ((gameTime % time) / time) * (Math.PI * 2);
        double dx2 = (distanceX * Math.cos(angle));
        double dz2 = (distanceZ * Math.sin(angle));

        Vec3d vector2f = new Vec3d(dx2, 0, dz2);
        double x = vector2f.x * distanceX;
        double z = vector2f.z * distanceZ;
        return pos.add(x, 0, z);
    }

    public static ArrayList<Vec3d> blockOutlinePositions(World world, BlockPos pos) {
        ArrayList<Vec3d> arrayList = new ArrayList<>();
        double d0 = 0.5625D;
        Random random = world.random;
        for (Direction direction : Direction.values()) {
            BlockPos blockpos = pos.offset(direction);
            if (!world.getBlockState(blockpos).isOpaqueFullCube(world, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + d0 * (double) direction.getOffsetX() : (double) random.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + d0 * (double) direction.getOffsetY() : (double) random.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + d0 * (double) direction.getOffsetZ() : (double) random.nextFloat();
                arrayList.add(new Vec3d((double) pos.getX() + d1, (double) pos.getY() + d2, (double) pos.getZ() + d3));
            }
        }
        return arrayList;
    }
}
