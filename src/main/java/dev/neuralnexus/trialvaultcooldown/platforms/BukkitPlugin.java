/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown.platforms;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.trialvaultcooldown.TrialVaultCooldown;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitPlugin extends JavaPlugin {
    public BukkitPlugin() {
        TrialVaultCooldown.instance()
                .pluginStart(
                        this,
                        getServer(),
                        getLogger(),
                        new LoggerAdapter(TrialVaultCooldown.PROJECT_NAME, getLogger()));
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}
}
