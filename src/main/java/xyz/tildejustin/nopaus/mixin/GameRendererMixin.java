package xyz.tildejustin.nopaus.mixin;

import net.minecraft.client.render.GameRenderer;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tildejustin.nopaus.NoPaus;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Redirect(
            method = "method_1331",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/Display;isActive()Z"
            )
    )
    private boolean redirectIsActive() {
        return Display.isActive() || !NoPaus.pauseOnLostFocus;
    }
}
