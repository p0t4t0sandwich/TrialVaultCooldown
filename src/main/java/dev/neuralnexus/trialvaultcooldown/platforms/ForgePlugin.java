/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown.platforms;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.trialvaultcooldown.TrialVaultCooldown;

import net.minecraftforge.fml.common.Mod;

/** Forge entry point. */
@Mod(
        value = TrialVaultCooldown.PROJECT_ID,
        modid = TrialVaultCooldown.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
public class ForgePlugin {
    public ForgePlugin() {
        TrialVaultCooldown.instance()
                .pluginStart(this, null, null, new LoggerAdapter(TrialVaultCooldown.PROJECT_NAME));
    }
}
