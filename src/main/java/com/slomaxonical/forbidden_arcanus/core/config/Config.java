package com.slomaxonical.forbidden_arcanus.core.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.valhelsia.valhelsia_core.core.config.ValhelsiaConfigSpec;

import java.io.File;

/**
 * Config
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.core.config.Config
 *
 * @author Valhelsia Team
 * @version 16.2.0
 */
//@Mod.EventBusSubscriber
public class Config {

	private static final ValhelsiaConfigSpec.Builder COMMON_BUILDER = new ValhelsiaConfigSpec.Builder();
	private static final ValhelsiaConfigSpec.Builder CLIENT_BUILDER = new ValhelsiaConfigSpec.Builder();

	public static final ValhelsiaConfigSpec COMMON_CONFIG;
	public static final ValhelsiaConfigSpec CLIENT_CONFIG;

	static {
		WorldGenConfig.init(COMMON_BUILDER);
		ItemConfig.init(COMMON_BUILDER);
		BlockConfig.init(COMMON_BUILDER);
		RenderingConfig.init(CLIENT_BUILDER);
		EnchantmentConfig.init(COMMON_BUILDER);
		AurealConfig.init(COMMON_BUILDER);

		COMMON_CONFIG = COMMON_BUILDER.build();
		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}

	public static void loadConfig(ValhelsiaConfigSpec config, String path) {
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).preserveInsertionOrder().sync().autosave().writingMode(WritingMode.REPLACE).build();
		file.load();
		config.setConfig(file);
	}

	public static void init() {
	}
}
