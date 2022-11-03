package dev.jensderuiter.balloonsbyjens.event;

import dev.jensderuiter.balloonsbyjens.util.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class OnPickup implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        ItemStack item = event.getItem().getItemStack();
        if (item.getType() == Material.PLAYER_HEAD) {
            if (item.isSimilar(event.getPlayer().getInventory().getItemInMainHand())) {
                Utils.checkBalloonRemovalOrAdd(event.getPlayer(), item);
            }
        }
    }

}
