/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.trialvaultcooldown.TrialVaultCooldown;

import org.apache.logging.log4j.Logger;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(TrialVaultCooldown.PROJECT_ID)
public class Sponge8Plugin {
    @Inject
    public Sponge8Plugin(PluginContainer container, Logger logger) {
        TrialVaultCooldown.instance()
                .pluginStart(
                        container,
                        null,
                        logger,
                        new LoggerAdapter(TrialVaultCooldown.PROJECT_NAME, logger));
    }
}
