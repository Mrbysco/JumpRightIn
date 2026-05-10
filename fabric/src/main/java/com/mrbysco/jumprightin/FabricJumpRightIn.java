package com.mrbysco.jumprightin;

import com.mrbysco.jumprightin.config.JumpConfig;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.neoforged.fml.config.ModConfig;

public class FabricJumpRightIn implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		NeoForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.CLIENT, JumpConfig.clientSpec);
	}
}
