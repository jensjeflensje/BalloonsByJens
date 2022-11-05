package dev.jensderuiter.balloonsbyjens;

import dev.jensderuiter.balloonsbyjens.event.*;
import dev.jensderuiter.balloonsbyjens.runner.BalloonRunner;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class BalloonsPlugin extends JavaPlugin {

    public static HashMap<Player, BalloonRunner> playerBalloons = new HashMap<>();
    private static BalloonsPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new OnBalloonHold(), this);
        getServer().getPluginManager().registerEvents(new OnDrop(), this);
        getServer().getPluginManager().registerEvents(new OnLeave(), this);
        getServer().getPluginManager().registerEvents(new OnPickup(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryChange(), this);
    }

    @Override
    public void onDisable() {
        for (BalloonRunner runner : playerBalloons.values()) {
            runner.destroy();
            runner.cancel();
        }
    }

    public static BalloonsPlugin getInstance() {
        return instance;
    }
}
