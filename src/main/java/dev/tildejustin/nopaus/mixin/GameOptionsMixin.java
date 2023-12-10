package dev.tildejustin.nopaus.mixin;

import dev.tildejustin.nopaus.NoPaus;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.io.PrintWriter;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    @Inject(method = "save",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;allKeys:[Lnet/minecraft/client/option/KeyBinding;"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void savePauseOnLostFocus(CallbackInfo ci, PrintWriter printWriter) {
        printWriter.println("pauseOnLostFocus:" + NoPaus.pauseOnLostFocus);
    }

    @ModifyVariable(method = "load", at = @At(value = "STORE", ordinal = 0))
    private String[] loadPauseOnLostFocus(String[] stringArray) {
        if ("pauseOnLostFocus".equals(stringArray[0])) NoPaus.pauseOnLostFocus = "true".equals(stringArray[1]);
        return stringArray;
    }
}
