package dev.tildejustin.nopaus.mixin;

import dev.tildejustin.nopaus.NoPaus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Unique
    boolean active;

    @Redirect(method = "method_1331", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;isActive()Z"))
    private boolean redirectIsActive() {
        return (active = Display.isActive()) || !NoPaus.pauseOnLostFocus;
    }

    @Redirect(method = "method_1331", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;focused:Z"))
    private boolean redirectFocused(Minecraft instance) {
        return instance.focused && this.active;
    }
}
