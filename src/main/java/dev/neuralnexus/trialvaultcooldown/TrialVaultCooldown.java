/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.bstats.MetricsAdapter;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.logger.AbstractLogger;
import dev.neuralnexus.taterlib.plugin.Plugin;
import dev.neuralnexus.trialvaultcooldown.api.TVCAPI;
import dev.neuralnexus.trialvaultcooldown.api.TVCAPIProvider;
import dev.neuralnexus.trialvaultcooldown.commands.TVCCommand;
import dev.neuralnexus.trialvaultcooldown.config.TVCConfigLoader;

import java.util.HashMap;

/** Main class for the plugin. */
public class TrialVaultCooldown implements Plugin {
    public static final String PROJECT_NAME = "TrialVaultCooldown";
    public static final String PROJECT_ID = "trialvaultcooldown";
    public static final String PROJECT_VERSION = "0.1.0-R0.1-SNAPSHOT";
    public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
    public static final String PROJECT_DESCRIPTION =
            "A plugin/mod that allows players to redeem vaults multiple times with a configurable cooldown. ";
    public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TrialVaultCooldown";

    private static final TrialVaultCooldown instance = new TrialVaultCooldown();
    private static boolean STARTED = false;
    private static boolean RELOADED = false;
    private Object plugin;
    private Object pluginServer;
    private Object pluginLogger;
    private AbstractLogger logger;

    @Override
    public String name() {
        return TrialVaultCooldown.PROJECT_NAME;
    }

    @Override
    public String id() {
        return TrialVaultCooldown.PROJECT_ID;
    }

    @Override
    public void pluginStart(
            Object plugin, Object pluginServer, Object pluginLogger, AbstractLogger logger) {
        logger.info(
                TrialVaultCooldown.PROJECT_NAME
                        + " is running on "
                        + TaterAPIProvider.serverType()
                        + " "
                        + TaterAPIProvider.minecraftVersion()
                        + "!");
        PluginEvents.DISABLED.register(event -> pluginStop());

        if (pluginServer != null) {
            setPluginServer(pluginServer);
        }
        if (pluginLogger != null) {
            setPluginLogger(pluginLogger);
        }
        setPlugin(plugin);
        setLogger(logger);

        // Set up bStats
        HashMap<ServerType, Integer> statsMap = new HashMap<>();
        statsMap.put(ServerType.BUKKIT, 22253);
        statsMap.put(ServerType.SPONGE, 22254);
        MetricsAdapter.setupMetrics(plugin, pluginServer, pluginLogger, statsMap);

        // Config
        TVCConfigLoader.load();

        if (STARTED) {
            instance.logger.info(PROJECT_NAME + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register listeners
            CommandEvents.REGISTER_COMMAND.register(
                    event ->
                            event.registerCommand(
                                    TrialVaultCooldown.plugin(),
                                    new TVCCommand(),
                                    "alias1",
                                    "alias2"));
        }

        TVCAPIProvider.register(new TVCAPI("someData"));

        instance.logger.info(PROJECT_NAME + " has been started!");
    }

    @Override
    public void pluginStop() {
        if (!STARTED) {
            instance.logger.info(PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;

        // Remove references to objects
        TVCConfigLoader.unload();

        // Unregister API
        TVCAPIProvider.unregister();

        instance.logger.info(PROJECT_NAME + " has been stopped!");
    }

    /**
     * Getter for the singleton instance of the class.
     *
     * @return The singleton instance
     */
    public static TrialVaultCooldown instance() {
        return instance;
    }

    /**
     * Get the plugin
     *
     * @return The plugin
     */
    public static Object plugin() {
        return instance.plugin;
    }

    /**
     * Set the plugin
     *
     * @param plugin The plugin
     */
    private static void setPlugin(Object plugin) {
        instance.plugin = plugin;
    }

    /**
     * Set the plugin server
     *
     * @param pluginServer The plugin server
     */
    private static void setPluginServer(Object pluginServer) {
        instance.pluginServer = pluginServer;
    }

    /**
     * Set the plugin logger
     *
     * @param pluginLogger The plugin logger
     */
    private static void setPluginLogger(Object pluginLogger) {
        instance.pluginLogger = pluginLogger;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static AbstractLogger logger() {
        return instance.logger;
    }

    /**
     * Set the logger
     *
     * @param logger The logger
     */
    private static void setLogger(AbstractLogger logger) {
        instance.logger = logger;
    }

    /** Reload */
    public void reload() {
        if (!STARTED) {
            instance.logger.info(PROJECT_NAME + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        pluginStop();

        // Start
        pluginStart(instance.plugin, instance.pluginServer, instance.pluginLogger, instance.logger);

        instance.logger.info(PROJECT_NAME + " has been reloaded!");
    }
}
