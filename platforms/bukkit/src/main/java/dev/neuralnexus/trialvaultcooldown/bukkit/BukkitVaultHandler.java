package dev.neuralnexus.trialvaultcooldown.bukkit;

import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultServerData;

import org.bukkit.Material;
import org.bukkit.block.Vault;
import org.bukkit.craftbukkit.block.CraftVault;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BukkitVaultHandler implements Listener {
    @EventHandler
    public static void handleVault(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType().equals(Material.VAULT)) {
            Vault vault = (Vault) event.getClickedBlock().getState();
            org.bukkit.block.data.type.Vault vaultData = (org.bukkit.block.data.type.Vault) vault.getBlockData();
            System.out.println("Vault clicked with the state " + vaultData.getTrialSpawnerState());
            CraftVault craftVault = (CraftVault) vault;
            if (!craftVault.getHandle().hasBlockEntity()) return;

            VaultBlockEntity blockEntity = craftVault.getTileEntity();
            if (blockEntity == null) return;

            VaultServerData serverData = blockEntity.getServerData();
            if (serverData == null) return;

//            replaceHasRewardedPlayer(serverData);
        }
//        } else if (event.getClickedBlock() instanceof TrialSpawner trialSpawner) {
//            org.bukkit.block.data.type.TrialSpawner trialSpawnerData = (org.bukkit.block.data.type.TrialSpawner) trialSpawner.getBlockData();
//            System.out.println("TrialSpawner clicked with the state " + trialSpawnerData.getTrialSpawnerState());
//        }
    }
}
