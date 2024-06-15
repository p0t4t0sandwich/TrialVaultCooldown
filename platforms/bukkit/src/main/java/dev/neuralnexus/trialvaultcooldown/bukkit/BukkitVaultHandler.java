package dev.neuralnexus.trialvaultcooldown.bukkit;

import org.bukkit.block.TrialSpawner;
import org.bukkit.block.Vault;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BukkitVaultHandler implements Listener {
    @EventHandler
    public static void handleVault(PlayerInteractEvent event) {
        if (event.getClickedBlock() instanceof Vault vault) {
            org.bukkit.block.data.type.Vault vaultData = (org.bukkit.block.data.type.Vault) vault.getBlockData();
            System.out.println("Vault clicked with the state " + vaultData.getTrialSpawnerState());
        } else if (event.getClickedBlock() instanceof TrialSpawner trialSpawner) {
            org.bukkit.block.data.type.TrialSpawner trialSpawnerData = (org.bukkit.block.data.type.TrialSpawner) trialSpawner.getBlockData();
            System.out.println("TrialSpawner clicked with the state " + trialSpawnerData.getTrialSpawnerState());
        }
    }
}
