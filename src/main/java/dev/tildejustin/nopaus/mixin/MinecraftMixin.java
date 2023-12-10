package dev.tildejustin.nopaus.mixin;

import dev.tildejustin.nopaus.NoPaus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameOptions;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow
    public GameOptions options;

    @Inject(method = "tick",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;reload()V")),
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0)
    )
    private void checkPauseOnLostFocus(CallbackInfo ci) {
        if (Keyboard.getEventKey() == 25 && Keyboard.isKeyDown(61)) {
            NoPaus.pauseOnLostFocus = !NoPaus.pauseOnLostFocus;
            this.options.save();
        }
    }
}
