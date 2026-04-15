package com.mrbysco.jumprightin.mixin;

import com.google.common.collect.Lists;
import com.llamalad7.mixinextras.sugar.Local;
import com.mrbysco.jumprightin.WorldHelper;
import com.mrbysco.jumprightin.config.JumpConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.quickplay.QuickPlay;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Function;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

	@Shadow
	public abstract void setScreen(@Nullable Screen guiScreen);

	@Inject(
			method = "buildInitialScreens(Lnet/minecraft/client/Minecraft$GameLoadCookie;)Ljava/lang/Runnable;",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/Minecraft;addInitialScreens(Ljava/util/List;)Z",
					shift = At.Shift.AFTER,
					ordinal = 0),
			cancellable = true
	)
	public void jumprightin$setInitialScreen(Minecraft.GameLoadCookie gameLoadCookie, CallbackInfoReturnable<Runnable> cir,
	                                         @Local List<Function<Runnable, Screen>> list) {
		Minecraft mc = (Minecraft) (Object) this;
		if (WorldHelper.loadConfiguredWorld()) {
			Runnable runnable = () -> {
				GameConfig.QuickPlayVariant gameconfig$quickplayvariant = WorldHelper.getQuickPlayVariant();
				QuickPlay.connect(mc, gameconfig$quickplayvariant, gameLoadCookie.realmsClient());
			};

			for (Function<Runnable, Screen> function : Lists.reverse(list)) {
				Screen screen = function.apply(runnable);
				runnable = () -> this.setScreen(screen);
			}

			runnable = net.neoforged.neoforge.client.loading.ClientModLoader.completeModLoading(runnable);

			cir.setReturnValue(runnable);
		}
	}
}
