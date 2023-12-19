package dev.tildejustin.chunkborders;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class ChunkBorderDebugRenderer implements DebugRenderer.DebugRenderable {
    private final MinecraftClient client;

    public ChunkBorderDebugRenderer(MinecraftClient minecraftClient) {
        this.client = minecraftClient;
    }

    @Override
    public void render(float tickDelta, long limitTime) {
        PlayerEntity playerEntity = this.client.player;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        double d = playerEntity.prevTickX + (playerEntity.x - playerEntity.prevTickX) * (double) tickDelta;
        double e = playerEntity.prevTickY + (playerEntity.y - playerEntity.prevTickY) * (double) tickDelta;
        double f = playerEntity.prevTickZ + (playerEntity.z - playerEntity.prevTickZ) * (double) tickDelta;
        double g = 0.0 - e;
        double h = 256.0 - e;
        GlStateManager.disableTexture();
        GlStateManager.disableBlend();
        double i = (double) (playerEntity.chunkX << 4) - d;
        double j = (double) (playerEntity.chunkZ << 4) - f;
        GL11.glLineWidth(1.0F);
        bufferBuilder.begin(3, VertexFormats.POSITION_COLOR);

        for (int k = -16; k <= 32; k += 16) {
            for (int l = -16; l <= 32; l += 16) {
                bufferBuilder.vertex(i + (double) k, g, j + (double) l).color(1.0F, 0.0F, 0.0F, 0.0F).next();
                bufferBuilder.vertex(i + (double) k, g, j + (double) l).color(1.0F, 0.0F, 0.0F, 0.5F).next();
                bufferBuilder.vertex(i + (double) k, h, j + (double) l).color(1.0F, 0.0F, 0.0F, 0.5F).next();
                bufferBuilder.vertex(i + (double) k, h, j + (double) l).color(1.0F, 0.0F, 0.0F, 0.0F).next();
            }
        }

        for (int k = 2; k < 16; k += 2) {
            bufferBuilder.vertex(i + (double) k, g, j).color(1.0F, 1.0F, 0.0F, 0.0F).next();
            bufferBuilder.vertex(i + (double) k, g, j).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i + (double) k, h, j).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i + (double) k, h, j).color(1.0F, 1.0F, 0.0F, 0.0F).next();
            bufferBuilder.vertex(i + (double) k, g, j + 16.0).color(1.0F, 1.0F, 0.0F, 0.0F).next();
            bufferBuilder.vertex(i + (double) k, g, j + 16.0).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i + (double) k, h, j + 16.0).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i + (double) k, h, j + 16.0).color(1.0F, 1.0F, 0.0F, 0.0F).next();
        }

        for (int k = 2; k < 16; k += 2) {
            bufferBuilder.vertex(i, g, j + (double) k).color(1.0F, 1.0F, 0.0F, 0.0F).next();
            bufferBuilder.vertex(i, g, j + (double) k).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i, h, j + (double) k).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i, h, j + (double) k).color(1.0F, 1.0F, 0.0F, 0.0F).next();
            bufferBuilder.vertex(i + 16.0, g, j + (double) k).color(1.0F, 1.0F, 0.0F, 0.0F).next();
            bufferBuilder.vertex(i + 16.0, g, j + (double) k).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i + 16.0, h, j + (double) k).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i + 16.0, h, j + (double) k).color(1.0F, 1.0F, 0.0F, 0.0F).next();
        }

        for (int k = 0; k <= 256; k += 2) {
            double m = (double) k - e;
            bufferBuilder.vertex(i, m, j).color(1.0F, 1.0F, 0.0F, 0.0F).next();
            bufferBuilder.vertex(i, m, j).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i, m, j + 16.0).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i + 16.0, m, j + 16.0).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i + 16.0, m, j).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i, m, j).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            bufferBuilder.vertex(i, m, j).color(1.0F, 1.0F, 0.0F, 0.0F).next();
        }

        tessellator.draw();
        GL11.glLineWidth(2.0F);
        bufferBuilder.begin(3, VertexFormats.POSITION_COLOR);

        for (int k = 0; k <= 16; k += 16) {
            for (int l = 0; l <= 16; l += 16) {
                bufferBuilder.vertex(i + (double) k, g, j + (double) l).color(0.25F, 0.25F, 1.0F, 0.0F).next();
                bufferBuilder.vertex(i + (double) k, g, j + (double) l).color(0.25F, 0.25F, 1.0F, 1.0F).next();
                bufferBuilder.vertex(i + (double) k, h, j + (double) l).color(0.25F, 0.25F, 1.0F, 1.0F).next();
                bufferBuilder.vertex(i + (double) k, h, j + (double) l).color(0.25F, 0.25F, 1.0F, 0.0F).next();
            }
        }

        for (int k = 0; k <= 256; k += 16) {
            double m = (double) k - e;
            bufferBuilder.vertex(i, m, j).color(0.25F, 0.25F, 1.0F, 0.0F).next();
            bufferBuilder.vertex(i, m, j).color(0.25F, 0.25F, 1.0F, 1.0F).next();
            bufferBuilder.vertex(i, m, j + 16.0).color(0.25F, 0.25F, 1.0F, 1.0F).next();
            bufferBuilder.vertex(i + 16.0, m, j + 16.0).color(0.25F, 0.25F, 1.0F, 1.0F).next();
            bufferBuilder.vertex(i + 16.0, m, j).color(0.25F, 0.25F, 1.0F, 1.0F).next();
            bufferBuilder.vertex(i, m, j).color(0.25F, 0.25F, 1.0F, 1.0F).next();
            bufferBuilder.vertex(i, m, j).color(0.25F, 0.25F, 1.0F, 0.0F).next();
        }

        tessellator.draw();
        GL11.glLineWidth(1.0F);
        GlStateManager.enableBlend();
        GlStateManager.enableTexture();
    }
}
