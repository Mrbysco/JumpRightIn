package com.mrbysco.jumprightin.platform;

import com.mrbysco.jumprightin.platform.services.IPlatformHelper;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ForgePlatformHelper implements IPlatformHelper {

	@Override
	public Path getConfigPath() {
		return FMLPaths.CONFIGDIR.get();
	}
}
