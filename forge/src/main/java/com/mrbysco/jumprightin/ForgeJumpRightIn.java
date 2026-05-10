package com.mrbysco.jumprightin;

import com.mrbysco.jumprightin.config.JumpConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class ForgeJumpRightIn {

	public ForgeJumpRightIn() {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, JumpConfig.clientSpec);
		});
	}
}