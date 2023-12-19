package dev.tildejustin.chunkborders.mixin;

import dev.tildejustin.chunkborders.DebugRenderer;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(
            method = "renderWorld(IFJ)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;renderingPanorama:Z", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=destroyProgress")), require = 0
    )
    private void runDebugRenderer(int anaglyphFilter, float tickDelta, long limitTime, CallbackInfo ci) {
        if (DebugRenderer.instance.isEnabled()) {
            DebugRenderer.instance.render(tickDelta, limitTime);
        }
    }
}
