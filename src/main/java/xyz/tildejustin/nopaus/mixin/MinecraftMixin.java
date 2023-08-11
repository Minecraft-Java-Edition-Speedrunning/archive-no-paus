package xyz.tildejustin.nopaus.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameOptions;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.tildejustin.nopaus.NoPaus;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow
    public GameOptions options;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 3))
    private void checkPauseOnLostFocus(CallbackInfo ci) {
        if (Keyboard.getEventKey() == 25 && Keyboard.isKeyDown(61)) {
            NoPaus.pauseOnLostFocus = !NoPaus.pauseOnLostFocus;
            this.options.save();
        }
    }
}
