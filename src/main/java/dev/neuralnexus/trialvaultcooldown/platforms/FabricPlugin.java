/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown.platforms;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.trialvaultcooldown.TrialVaultCooldown;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricPlugin implements ModInitializer {
    public FabricPlugin() {
        TrialVaultCooldown.instance()
                .pluginStart(this, null, null, new LoggerAdapter(TrialVaultCooldown.PROJECT_NAME));
    }

    @Override
    public void onInitialize() {}
}
