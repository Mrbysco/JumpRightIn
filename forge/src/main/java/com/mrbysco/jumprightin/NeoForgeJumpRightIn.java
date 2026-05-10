package com.mrbysco.jumprightin;

import com.mrbysco.jumprightin.config.JumpConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class NeoForgeJumpRightIn {

	public NeoForgeJumpRightIn(ModContainer container, Dist dist) {
		if (dist.isClient()) {
			container.registerConfig(ModConfig.Type.CLIENT, JumpConfig.clientSpec);
		}
	}
}