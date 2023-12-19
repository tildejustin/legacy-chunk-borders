package dev.tildejustin.chunkborders.mixin;

import dev.tildejustin.chunkborders.DebugRenderer;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;togglePerspectiveKey:Lnet/minecraft/client/option/KeyBinding;"))
    private void handleChunkBordersKey(CallbackInfo ci) {
        if (Keyboard.getEventKey() == Keyboard.KEY_G && Keyboard.isKeyDown(61)) {
            DebugRenderer.instance.toggleChunkBorders();
        }
    }
}
