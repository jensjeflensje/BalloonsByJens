package dev.jensderuiter.balloonsbyjens.runner;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.Random;

public class BalloonRunner extends BukkitRunnable {

    public Player player;
    public ItemStack head;
    private ArmorStand armorStand;
    private Chicken chicken;
    private Location playerLocation;
    public Location moveLocation;

    private int ticks = 0;
    private float targetYaw = 0;

    public BalloonRunner(Player player, ItemStack head) {
        this.player = player;
        this.head = head;
    }

    @Override
    public void run() {
        if (this.armorStand == null) {
            initBalloon();
        }

        Location location = player.getLocation();
        location.setYaw(this.playerLocation.getYaw());

        if (this.ticks == 20) {
            Random random = new Random();
            targetYaw = random.nextInt(10) - 5;
            this.ticks = 0;
        }

        if (targetYaw > location.getYaw()) {
            location.setYaw(location.getYaw() + .2f);
        } else if (targetYaw < location.getYaw()) {
            location.setYaw(location.getYaw() - .2f);
        }

        moveLocation = this.armorStand.getLocation().subtract(0, 2, 0).clone();
        Vector vector = location.toVector().subtract(moveLocation.toVector());
        vector.multiply(0.3);
        moveLocation = moveLocation.add(vector);

        double value1 = vector.getZ() * 50 * -1;
        double value2 = vector.getX() * 50 * -1;
        this.armorStand.setHeadPose(new EulerAngle(Math.toRadians(value1), Math.toRadians(location.getYaw()), Math.toRadians(value2)));

        teleport(moveLocation);

        this.playerLocation = player.getLocation();
        this.playerLocation.setYaw(location.getYaw());
        this.ticks++;
    }

    public void destroy() {
        this.armorStand.remove();
        this.chicken.remove();
    }

    private void teleport(Location location) {
        this.armorStand.teleport(location.add(0, 2, 0));
        this.chicken.teleport(location.add(0, 1.2, 0));
    }

    private void initBalloon() {
        this.playerLocation = player.getLocation();
        this.playerLocation.setYaw(0);
        this.armorStand = playerLocation.getWorld().spawn(playerLocation, ArmorStand.class);
        this.armorStand.setBasePlate(false);
        this.armorStand.setVisible(false);
        this.armorStand.setInvulnerable(true);
        this.armorStand.setCanPickupItems(false);
        this.armorStand.setGravity(false);
        this.armorStand.setSmall(false);
        ItemMeta meta = this.head.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        this.head.setItemMeta(meta);
        this.armorStand.setMarker(true);
        this.armorStand.getEquipment().setHelmet(this.head);

        this.chicken = playerLocation.getWorld().spawn(playerLocation, Chicken.class);
        this.chicken.setInvulnerable(true);
        this.chicken.setInvisible(true);
        this.chicken.setSilent(true);
        this.chicken.setBaby();

        this.chicken.setLeashHolder(this.player);
    }
}
