package com.mrbysco.jumprightin.platform.services;

import java.nio.file.Path;

public interface IPlatformHelper {

	/*
	 * Get the platform-specific path to the config folder.
	 */
	Path getConfigPath();

}
