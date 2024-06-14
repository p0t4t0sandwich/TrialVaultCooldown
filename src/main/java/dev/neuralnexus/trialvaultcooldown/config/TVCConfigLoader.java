/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown.config;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.config.ConfigUtils;
import dev.neuralnexus.trialvaultcooldown.TrialVaultCooldown;
import dev.neuralnexus.trialvaultcooldown.config.versions.TVCConfig_V1;

import io.leangen.geantyref.TypeToken;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/** A class for loading TrialVaultCooldown configuration. */
public class TVCConfigLoader {
    private static final Path configPath =
            Paths.get(
                    TaterAPIProvider.serverType().dataFolders().configFolder()
                            + File.separator
                            + TrialVaultCooldown.PROJECT_NAME
                            + File.separator
                            + TrialVaultCooldown.PROJECT_ID
                            + ".conf");
    private static final String defaultConfigPath =
            "source." + TrialVaultCooldown.PROJECT_ID + ".conf";
    private static final TypeToken<Integer> versionType = new TypeToken<Integer>() {};
    private static TVCConfig config;

    /** Load the configuration from the file. */
    public static void load() {
        ConfigUtils.copyDefaults(
                TrialVaultCooldown.class,
                configPath,
                defaultConfigPath,
                TrialVaultCooldown.logger());

        final HoconConfigurationLoader loader =
                HoconConfigurationLoader.builder().path(configPath).build();
        CommentedConfigurationNode root = ConfigUtils.getRoot(loader);
        if (root == null) {
            return;
        }

        ConfigurationNode versionNode = root.node("version");
        int version = versionNode.getInt(1);

        //        List<ModuleConfig> modules = ConfigUtils.get(root, moduleType, "modules",
        // TrialVaultCooldown.logger());

        switch (version) {
            case 1:
                config = new TVCConfig_V1(version);
                break;
            default:
                TrialVaultCooldown.logger().error("Unknown configuration version: " + version);
        }
    }

    /** Unload the configuration. */
    public static void unload() {
        config = null;
    }

    /** Save the configuration to the file. */
    public static void save() {
        if (config == null) {
            return;
        }
        final HoconConfigurationLoader loader =
                HoconConfigurationLoader.builder().path(configPath).build();
        CommentedConfigurationNode root = ConfigUtils.getRoot(loader);
        if (root == null) {
            return;
        }

        ConfigUtils.set(
                root, versionType, "version", config.version(), TrialVaultCooldown.logger());

        try {
            loader.save(root);
        } catch (ConfigurateException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    }

    /**
     * Get the loaded configuration.
     *
     * @return The loaded configuration.
     */
    public static TVCConfig config() {
        if (config == null) {
            load();
        }
        return config;
    }
}
