package dev.jensderuiter.balloonsbyjens.event;

import dev.jensderuiter.balloonsbyjens.BalloonsPlugin;
import dev.jensderuiter.balloonsbyjens.runner.BalloonRunner;
import dev.jensderuiter.balloonsbyjens.util.Utils;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class OnLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        BalloonRunner runner = BalloonsPlugin.playerBalloons.get(event.getPlayer());
        Utils.removeBalloon(runner);
    }

}
