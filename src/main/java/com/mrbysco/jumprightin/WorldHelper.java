package com.mrbysco.jumprightin;

import com.mrbysco.jumprightin.config.JumpConfig;
import com.mrbysco.jumprightin.config.LoadType;
import net.minecraft.client.main.GameConfig;
import net.neoforged.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Path;

public class WorldHelper {
	public static boolean loadConfiguredWorld() {
		String levelName = JumpConfig.CLIENT.worldName.get();
		String serverIP = JumpConfig.CLIENT.serverIP.get();
		if (levelName.isBlank() && serverIP.isBlank()) {
			JumpRightIn.LOGGER.info("No world or server configured for auto-join, skipping.");
			return false;
		}
		LoadType loadType = JumpConfig.CLIENT.loadType.get();
		if (loadType == LoadType.ONCE) {
			// Create a file in config folder to indicate that the world/server has been loaded once
			Path configDir = FMLPaths.CONFIGDIR.get();
			File markerFile = configDir.resolve("jumprightin_loaded.txt").toFile();
			if (markerFile.exists()) {
				if (levelName.isBlank()) {
					JumpRightIn.LOGGER.info("Server '{}' has already been loaded once, skipping auto-join.", serverIP);
				} else {
					JumpRightIn.LOGGER.info("World '{}' has already been loaded once, skipping auto-join.", levelName);
				}
				return false;
			} else {
				try {
					if (markerFile.createNewFile()) {
						JumpRightIn.LOGGER.info("Marker file created, allowing auto-join to {} '{}'.", levelName.isBlank() ? "server" : "world", levelName.isBlank() ? serverIP : levelName);
					} else {
						JumpRightIn.LOGGER.warn("Failed to create marker file, but it does not exist. Auto-join will proceed, but this may cause issues on subsequent launches.");
					}
				} catch (Exception e) {
					JumpRightIn.LOGGER.error("Error creating marker file for LoadType.ONCE: {}", e.getMessage());
					// Load the world but log an error about the file
				}
			}
		}
		return true;
	}

	public static GameConfig.QuickPlayVariant getQuickPlayVariant() {
		String worldName = JumpConfig.CLIENT.worldName.get();
		String serverIP = JumpConfig.CLIENT.serverIP.get();
		if (!worldName.isBlank()) {
			return new GameConfig.QuickPlaySinglePlayerData(worldName);
		} else if (!serverIP.isBlank()) {
			return new GameConfig.QuickPlayMultiplayerData(serverIP);
		} else {
			throw new IllegalArgumentException("Both world name and server IP cannot be blank.");
		}
	}

}
