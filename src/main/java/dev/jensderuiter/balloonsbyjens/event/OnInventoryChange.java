package dev.jensderuiter.balloonsbyjens.event;

import dev.jensderuiter.balloonsbyjens.util.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnInventoryChange implements Listener {

    @EventHandler
    public void onInventoryChange(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() != null
                && event.getCurrentItem().getType() == Material.PLAYER_HEAD
                || event.getCursor() != null && event.getCursor().getType() == Material.PLAYER_HEAD) {
            Utils.checkBalloonRemovalOrAdd(player, null);
        }
    }
}
