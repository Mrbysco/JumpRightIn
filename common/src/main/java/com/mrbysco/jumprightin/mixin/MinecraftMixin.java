package com.mrbysco.jumprightin.mixin;

import com.mojang.realmsclient.client.RealmsClient;
import com.mrbysco.jumprightin.WorldHelper;
import com.mrbysco.jumprightin.config.JumpConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.quickplay.QuickPlay;
import net.minecraft.server.packs.resources.ReloadInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

	@Inject(
			at = @At("HEAD"),
			method = "setInitialScreen(Lcom/mojang/realmsclient/client/RealmsClient;Lnet/minecraft/server/packs/resources/ReloadInstance;Lnet/minecraft/client/main/GameConfig$QuickPlayData;)V",
			cancellable = true
	)
	public void jumprightin$setInitialScreen(RealmsClient realmsClient, ReloadInstance reloadInstance, GameConfig.QuickPlayData quickPlayData, CallbackInfo ci) {
		Minecraft mc = (Minecraft) (Object) this;
		if (WorldHelper.loadConfiguredWorld()) {
			QuickPlay.connect(mc, new GameConfig.QuickPlayData(null, JumpConfig.CLIENT.worldName.get(), JumpConfig.CLIENT.serverIP.get(), ""), reloadInstance, realmsClient);
			ci.cancel();
		}
	}
}
