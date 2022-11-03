package dev.jensderuiter.balloonsbyjens.util;

import dev.jensderuiter.balloonsbyjens.BalloonsPlugin;
import dev.jensderuiter.balloonsbyjens.runner.BalloonRunner;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Utils {

    public static void checkBalloonRemovalOrAdd(Player player, ItemStack item) {
        ItemStack finalItem = item;
        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack item = finalItem;
                BalloonRunner runner = BalloonsPlugin.playerBalloons.get(player);
                if (runner != null) {
                    if (runner.head.isSimilar(item)) return;
                    runner.moveLocation.getWorld().spawnParticle(Particle.CLOUD, runner.moveLocation, 5, 0, 0 ,0, 0.1);
                    runner.destroy();
                    runner.cancel();
                    BalloonsPlugin.playerBalloons.remove(player);
                }
                if (item == null) {
                    item = player.getInventory().getItemInMainHand();
                }
                if (item.getType() == Material.PLAYER_HEAD && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains("Balloon")) {
                    BalloonRunner balloonRunner = new BalloonRunner(player, item);
                    balloonRunner.runTaskTimer(BalloonsPlugin.getInstance(), 0, 1);
                    BalloonsPlugin.playerBalloons.put(player, balloonRunner);
                }
            }
        }.runTaskLater(BalloonsPlugin.getInstance(), 1);
    }

}
