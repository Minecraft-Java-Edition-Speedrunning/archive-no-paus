package xyz.tildejustin.nopaus.mixin;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.tildejustin.nopaus.NoPaus;

import java.io.BufferedReader;
import java.io.PrintWriter;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
	@Inject(at = @At(value = "INVOKE", target = "Ljava/io/PrintWriter;println(Ljava/lang/String;)V", ordinal = 29, shift = At.Shift.AFTER), method = "save", locals = LocalCapture.CAPTURE_FAILHARD)
	private void savePauseOnLostFocus(CallbackInfo ci, PrintWriter printWriter) {
		printWriter.println("pauseOnLostFocus:" + NoPaus.pauseOnLostFocus);
	}

	@Inject(at = @At(value = "INVOKE", target = "Ljava/lang/String;split(Ljava/lang/String;)[Ljava/lang/String;", shift = At.Shift.BY, by = 2), method = "load", locals = LocalCapture.CAPTURE_FAILHARD)
	private void loadPauseOnLostFocus(CallbackInfo ci, BufferedReader bufferedReader, String string, String[] stringArray) {
		if (stringArray[0].equals("pauseOnLostFocus")) {
			NoPaus.pauseOnLostFocus = stringArray[1].equals("true");
		}
	}
}
