package dev.tildejustin.nopaus.mixin;

import dev.tildejustin.nopaus.NoPaus;
import net.minecraft.client.render.GameRenderer;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Redirect(method = "method_1331", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;isActive()Z"))
    private boolean redirectIsActive() {
        return Display.isActive() || !NoPaus.pauseOnLostFocus;
    }
}
