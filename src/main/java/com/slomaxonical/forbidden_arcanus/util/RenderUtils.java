//package com.slomaxonical.forbidden_arcanus.util;
//
//
//
//import com.slomaxonical.forbidden_arcanus.core.helper.FAHelper;
//import dev.architectury.fluid.FluidStack;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.render.TexturedRenderLayers;
//import net.minecraft.client.render.VertexConsumer;
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.texture.Sprite;
//import net.minecraft.client.texture.SpriteAtlasTexture;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.item.ItemStack;
//import net.minecraft.particle.ItemStackParticleEffect;
//import net.minecraft.particle.ParticleTypes;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.math.*;
//import net.minecraft.world.World;
//
//import java.util.Random;
//
//public class RenderUtils {
////transfer API....
//    public static void renderFluid(IFluidTank fluidTank, FluidStack fluidStack, VertexConsumerProvider buffer, Matrix4f matrix, Matrix3f normal, Box boundingBox, int color, int combinedLight, int combinedOverlay) {
//        Fluid fluid = fluidStack.getFluid();
//
//        VertexConsumer builder = buffer.getBuffer(TexturedRenderLayers.getEntityTranslucentCull());
//
//        Identifier Identifier = fluid.getAttributes().getStillTexture();
//        Sprite texture = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).apply(Identifier);
//
//        int light1 = combinedLight & 0xFFFF;
//        int light2 = combinedLight >> 0x10 & 0xFFFF;
//
//        int a = color >> 24 & 0xFF;
//        int r = color >> 16 & 0xFF;
//        int g = color >> 8 & 0xFF;
//        int b = color & 0xFF;
//
//        Box bounds = getRenderBounds(fluidTank, boundingBox);
//        float x1 = (float) bounds.minX;
//        float x2 = (float) bounds.maxX;
//        float y1 = (float) bounds.minY;
//        float y2 = (float) bounds.maxY;
//        float z1 = (float) bounds.minZ;
//        float z2 = (float) bounds.maxZ;
//        double bx1 = bounds.minX * 16;
//        double bx2 = bounds.maxX * 16;
//        double by1 = bounds.minY * 16;
//        double by2 = bounds.maxY * 16;
//        double bz1 = bounds.minZ * 16;
//        double bz2 = bounds.maxZ * 16;
//
//        for (Direction direction : Direction.values()) {
//            if (direction == Direction.DOWN) {
//                float u1 = texture.getFrameU(bx1);
//                float u2 = texture.getFrameU(bx2);
//                float v1 = texture.getFrameV(bz1);
//                float v2 = texture.getFrameV(bz2);
//                builder.vertex(matrix, x1, y1, z2).color(r, g, b, a).texture(u1, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x1, y1, z1).color(r, g, b, a).texture(u1, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y1, z1).color(r, g, b, a).texture(u2, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y1, z2).color(r, g, b, a).texture(u2, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//            }
//
//            if (direction == Direction.UP) {
//                float u1 = texture.getFrameU(bx1);
//                float u2 = texture.getFrameU(bx2);
//                float v1 = texture.getFrameV(bz1);
//                float v2 = texture.getFrameV(bz2);
//                builder.vertex(matrix, x1, y2, z2).color(r, g, b, a).texture(u1, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y2, z2).color(r, g, b, a).texture(u2, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y2, z1).color(r, g, b, a).texture(u2, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x1, y2, z1).color(r, g, b, a).texture(u1, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//            }
//
//            if (direction == Direction.NORTH) {
//                float u1 = texture.getFrameU(bx1);
//                float u2 = texture.getFrameU(bx2);
//                float v1 = texture.getFrameV(by1);
//                float v2 = texture.getFrameV(by2);
//                builder.vertex(matrix, x1, y1, z1).color(r, g, b, a).texture(u1, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x1, y2, z1).color(r, g, b, a).texture(u1, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y2, z1).color(r, g, b, a).texture(u2, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y1, z1).color(r, g, b, a).texture(u2, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//            }
//
//            if (direction == Direction.SOUTH) {
//                float u1 = texture.getFrameU(bx1);
//                float u2 = texture.getFrameU(bx2);
//                float v1 = texture.getFrameV(by1);
//                float v2 = texture.getFrameV(by2);
//                builder.vertex(matrix, x2, y1, z2).color(r, g, b, a).texture(u2, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y2, z2).color(r, g, b, a).texture(u2, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x1, y2, z2).color(r, g, b, a).texture(u1, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x1, y1, z2).color(r, g, b, a).texture(u1, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//            }
//
//            if (direction == Direction.WEST) {
//                float u1 = texture.getFrameU(by1);
//                float u2 = texture.getFrameU(by2);
//                float v1 = texture.getFrameV(bz1);
//                float v2 = texture.getFrameV(bz2);
//                builder.vertex(matrix, x1, y1, z2).color(r, g, b, a).texture(u1, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x1, y2, z2).color(r, g, b, a).texture(u2, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x1, y2, z1).color(r, g, b, a).texture(u2, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x1, y1, z1).color(r, g, b, a).texture(u1, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//            }
//
//            if (direction == Direction.EAST) {
//                float u1 = texture.getFrameU(by1);
//                float u2 = texture.getFrameU(by2);
//                float v1 = texture.getFrameV(bz1);
//                float v2 = texture.getFrameV(bz2);
//                builder.vertex(matrix, x2, y1, z1).color(r, g, b, a).texture(u1, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y2, z1).color(r, g, b, a).texture(u2, v1).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y2, z2).color(r, g, b, a).texture(u2, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//                builder.vertex(matrix, x2, y1, z2).color(r, g, b, a).texture(u1, v2).overlay(combinedOverlay).light(light1, light2).normal(normal, 0.0F, 1.0F, 0.0F).next();
//            }
//        }
//    }
////transfer API....
//    private static Box getRenderBounds(IFluidTank tank, Box tankBounds) {
//        float percent = (float) tank.getFluidAmount() / (float) tank.getCapacity();
//
//        double tankHeight = tankBounds.maxY - tankBounds.minY;
//        double y1 = tankBounds.minY, y2 = (tankBounds.minY + (tankHeight * percent));
//        if (tank.getFluid().getFluid().getAttributes().isLighterThanAir()) {
//            double yOff = tankBounds.maxY - y2;  // lighter than air fluids move to the top of the tank
//            y1 += yOff; y2 += yOff;
//        }
//        return new Box(tankBounds.minX, y1, tankBounds.minZ, tankBounds.maxX, y2, tankBounds.maxZ);
//    }
//
//    public static void addItemParticles(World world, ItemStack stack, BlockPos pos, int count) {
//        Random random = world.getRandom();
//
//        for(int i = 0; i < count; i++) {
//            Vec3d offset = new Vec3d((random.nextFloat() - 0.5D) * 0.1D, random.nextFloat() * 0.1D + 0.1D, 0.0D);
//            Vec3d vector = new Vec3d((random.nextFloat() - 0.5D) * 0.3D, -random.nextFloat() * 0.6D - 0.3D, 0.6D).add(FAHelper.blockPosToVector(pos));
//
//            if (world instanceof ServerWorld serverWorld) {
//                serverWorld.spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, stack), vector.x, vector.y, vector.z, 1, offset.x, offset.y + 0.05D, offset.z, 0.0D);
//            } else {
//                world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, stack), vector.x, vector.y, vector.z, offset.x, offset.y + 0.05D, offset.z);
//            }
//        }
//    }
//}
