package com.mrbysco.jumprightin;

import com.mojang.logging.LogUtils;
import com.mrbysco.jumprightin.config.JumpConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(JumpRightIn.MOD_ID)
public class JumpRightIn {
	public static final String MOD_ID = "jumprightin";
	public static final Logger LOGGER = LogUtils.getLogger();

	public JumpRightIn(ModContainer container, Dist dist) {
		if (dist.isClient()) {
			container.registerConfig(ModConfig.Type.CLIENT, JumpConfig.clientSpec);
		}
	}

}
