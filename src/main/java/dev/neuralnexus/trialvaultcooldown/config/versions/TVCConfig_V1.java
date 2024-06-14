/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown.config.versions;

import dev.neuralnexus.trialvaultcooldown.config.TVCConfig;

/** A class for Switchboard configuration. */
public class TVCConfig_V1 implements TVCConfig {
    private final int version;

    public TVCConfig_V1(int version) {
        this.version = version;
    }

    @Override
    public int version() {
        return version;
    }
}
