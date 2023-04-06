package xyz.tildejustin.nopaus.mixin;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.tildejustin.nopaus.NoPaus;

import java.io.PrintWriter;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    @Inject(at = @At(value = "INVOKE", target = "Ljava/io/PrintWriter;println(Ljava/lang/String;)V", ordinal = 29, shift = At.Shift.AFTER), method = "save", locals = LocalCapture.CAPTURE_FAILHARD)
    private void savePauseOnLostFocus(CallbackInfo ci, PrintWriter printWriter) {
        printWriter.println("pauseOnLostFocus:" + NoPaus.pauseOnLostFocus);
    }

    @ModifyVariable(method = "load", at = @At(value = "STORE", ordinal = 0))
    private String[] loadPauseOnLostFocus(String[] stringArray) {
        if (stringArray[0].equals("pauseOnLostFocus")) {
            NoPaus.pauseOnLostFocus = stringArray[1].equals("true");
        }
        return stringArray;
    }
}
