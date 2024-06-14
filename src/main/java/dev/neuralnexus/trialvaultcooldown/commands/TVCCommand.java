/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TrialVaultCooldown/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.trialvaultcooldown.commands;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.trialvaultcooldown.api.TVCAPIProvider;

/** Example Command. */
public class TVCCommand implements Command {
    private String name = "tvc";

    @Override
    public String name() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String description() {
        return "Example command";
    }

    @Override
    public String usage() {
        return "/tvc";
    }

    @Override
    public String permission() {
        return "tvc.command";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(permission())) {
            sender.sendMessage(
                    Utils.substituteSectionSign(
                            "&cYou do not have permission to execute this command!"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(
                    Utils.substituteSectionSign(
                            "&cThis command can only be executed by a player!"));
            return true;
        }

        sender.sendMessage(Utils.substituteSectionSign(TVCAPIProvider.get().getSomeData()));
        return true;
    }
}
