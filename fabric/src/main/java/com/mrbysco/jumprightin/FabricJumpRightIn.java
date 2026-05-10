package com.mrbysco.jumprightin;

import com.mrbysco.jumprightin.config.JumpConfig;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraftforge.fml.config.ModConfig;

public class FabricJumpRightIn implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.CLIENT, JumpConfig.clientSpec);
	}
}
