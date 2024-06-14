/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown.platforms;

import com.mojang.logging.LogUtils;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.trialvaultcooldown.TrialVaultCooldown;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

/** NeoForge entry point. */
@Mod(TrialVaultCooldown.PROJECT_ID)
public class NeoForgePlugin {
    public NeoForgePlugin() {
        TrialVaultCooldown.instance()
                .pluginStart(
                        this,
                        ServerLifecycleHooks.getCurrentServer(),
                        LogUtils.getLogger(),
                        new LoggerAdapter(TrialVaultCooldown.PROJECT_NAME, LogUtils.getLogger()));
    }
}
