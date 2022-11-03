package dev.jensderuiter.balloonsbyjens.event;

import dev.jensderuiter.balloonsbyjens.util.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class OnDrop implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (item.getType() == Material.PLAYER_HEAD) {
            Utils.checkBalloonRemovalOrAdd(event.getPlayer(), null);
        }
    }

}
