package dev.jensderuiter.balloonsbyjens.event;

import dev.jensderuiter.balloonsbyjens.util.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class OnBalloonHold implements Listener {

    @EventHandler
    public void onHold(PlayerItemHeldEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if (item == null) {
            Utils.checkBalloonRemovalOrAdd(event.getPlayer(), item);
            return;
        }
        if (item.getType() == Material.PLAYER_HEAD) {
            Utils.checkBalloonRemovalOrAdd(event.getPlayer(), item);
        } else {
            Utils.checkBalloonRemovalOrAdd(event.getPlayer(), item);
        }
    }

}
