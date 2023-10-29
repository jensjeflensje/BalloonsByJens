package dev.jensderuiter.balloonsbyjens.util;

import dev.jensderuiter.balloonsbyjens.BalloonsPlugin;
import dev.jensderuiter.balloonsbyjens.runner.BalloonRunner;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Utils {

    public static void checkBalloonRemovalOrAdd(Player player, ItemStack item) {
        ItemStack finalItem = item;
        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack item = finalItem;
                BalloonRunner runner = BalloonsPlugin.playerBalloons.get(player);
                if (runner != null && runner.head.isSimilar(item)) return;
                removeBalloon(runner);
                if (item == null) {
                    item = player.getInventory().getItemInMainHand();
                }
                if (item.getType() == Material.PLAYER_HEAD && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains("Balloon")) {
                    BalloonRunner balloonRunner = new BalloonRunner(player, item);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 0, false));
                    balloonRunner.runTaskTimer(BalloonsPlugin.getInstance(), 0, 1);
                    BalloonsPlugin.playerBalloons.put(player, balloonRunner);
                }
            }
        }.runTaskLater(BalloonsPlugin.getInstance(), 1);
    }

    public static void removeBalloon(BalloonRunner runner) {
        if (runner != null) {
            runner.moveLocation.getWorld().spawnParticle(Particle.CLOUD, runner.moveLocation, 5, 0, 0 ,0, 0.1);
            runner.destroy();
            runner.cancel();
            runner.player.removePotionEffect(PotionEffectType.SLOW_FALLING);
            BalloonsPlugin.playerBalloons.remove(runner.player);
        }
    }

}
