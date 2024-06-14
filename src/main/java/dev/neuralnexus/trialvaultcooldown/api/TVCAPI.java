/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown.api;

/** API wrapper class */
public class TVCAPI {
    private final Data data;

    public TVCAPI(String initSomeData) {
        this.data = new Data(initSomeData);
    }

    /**
     * Get some data
     *
     * @return Some data
     */
    public String getSomeData() {
        return this.data.someData;
    }

    /** Data used throughout the plugin via the API. */
    static class Data {
        String someData;

        Data(String someData) {
            this.someData = someData;
        }
    }
}
