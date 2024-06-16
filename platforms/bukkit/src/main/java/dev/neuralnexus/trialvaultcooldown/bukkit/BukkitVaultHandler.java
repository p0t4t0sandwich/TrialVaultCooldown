package dev.neuralnexus.trialvaultcooldown.bukkit;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultServerData;
import org.bukkit.block.Vault;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class BukkitVaultHandler implements Listener {
    @EventHandler
    public static void handleVault(PlayerInteractEvent event) {
        if (event.getClickedBlock() instanceof Vault vault) {
            org.bukkit.block.data.type.Vault vaultData = (org.bukkit.block.data.type.Vault) vault.getBlockData();
            System.out.println("Vault clicked with the state " + vaultData.getTrialSpawnerState());
            CraftBlock craftBlock = (CraftBlock) vault;
            BlockEntity blockEntity = craftBlock.getHandle().getBlockEntity(craftBlock.getPosition());
            VaultBlockEntity vaultBlockEntity = (VaultBlockEntity) blockEntity;
            if (vaultBlockEntity == null) return;
            VaultServerData vaultServerData = vaultBlockEntity.getServerData();
            if (vaultServerData == null) return;

            // Now some sketchy reflection to change the method
            Object proxyInstance = Proxy.newProxyInstance(
                    VaultServerData.class.getClassLoader(),
                    new Class[]{VaultServerData.class},
                    (proxy, method, args) -> {
                        if (method.getName().equals("hasRewardedPlayer")) {
                            return false;
                        }
                        return method.invoke(vaultServerData, args);
                    }
            );
            try {
                Field[] fields = vaultServerData.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.get(vaultServerData).getClass().equals(vaultServerData.getClass())) {
                        field.set(vaultServerData, proxyInstance);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
//        } else if (event.getClickedBlock() instanceof TrialSpawner trialSpawner) {
//            org.bukkit.block.data.type.TrialSpawner trialSpawnerData = (org.bukkit.block.data.type.TrialSpawner) trialSpawner.getBlockData();
//            System.out.println("TrialSpawner clicked with the state " + trialSpawnerData.getTrialSpawnerState());
//        }
    }
}
