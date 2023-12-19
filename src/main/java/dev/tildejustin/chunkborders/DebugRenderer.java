package dev.tildejustin.chunkborders;

import net.fabricmc.api.*;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class DebugRenderer {
    public final DebugRenderer.DebugRenderable chunkBorders;
    private boolean renderChunkBorders;
    public static DebugRenderer instance;

    static {
        DebugRenderer.instance = new DebugRenderer(MinecraftClient.getInstance());
    }

    private DebugRenderer(MinecraftClient minecraftClient) {
        this.chunkBorders = new ChunkBorderDebugRenderer(minecraftClient);
    }

    public boolean isEnabled() {
        return this.renderChunkBorders;
    }

    public void toggleChunkBorders() {
        this.renderChunkBorders = !this.renderChunkBorders;
    }

    public void render(float tickDelta, long limitTime) {
        if (this.renderChunkBorders && !MinecraftClient.getInstance().player.getReducedDebugInfo()) {
            this.chunkBorders.render(tickDelta, limitTime);
        }
    }

    public interface DebugRenderable {
        void render(float tickDelta, long limitTime);
    }
}
