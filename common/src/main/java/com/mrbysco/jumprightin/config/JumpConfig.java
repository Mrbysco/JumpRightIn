package com.mrbysco.jumprightin.config;

import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class JumpConfig {
	public static class Client {
		public final ModConfigSpec.ConfigValue<String> worldName;
		public final ModConfigSpec.ConfigValue<String> serverIP;
		public final ModConfigSpec.EnumValue<LoadType> loadType;

		Client(ModConfigSpec.Builder builder) {
			//General settings
			builder.comment("Jump settings")
					.push("jump");

			worldName = builder
					.comment("The name of the world to load upon starting the game")
					.define("worldName", "");

			serverIP = builder
					.comment("The ip of the server to load upon starting the game")
					.define("serverIP", "");

			loadType = builder
					.comment("How the world should be loaded upon starting the game. ALWAYS will load the world every time, while ONCE will only load it the first launch.")
					.defineEnum("loadType", LoadType.ALWAYS);

			builder.pop();
		}
	}


	public static final ModConfigSpec clientSpec;
	public static final Client CLIENT;

	static {
		final Pair<Client, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Client::new);
		clientSpec = specPair.getRight();
		CLIENT = specPair.getLeft();
	}

}
