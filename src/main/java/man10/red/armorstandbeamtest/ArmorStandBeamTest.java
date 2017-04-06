package man10.red.armorstandbeamtest;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import javax.swing.*;
import java.util.ArrayList;
import java.util.UUID;

public final class ArmorStandBeamTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.LEFT_CLICK_AIR){
            new BukkitRunnable(){
                double t = 0;
                Vector v = p.getLocation().getDirection().normalize();
                Location loc = p.getLocation();
                @Override
                public void run() {
                    t = t + 0.5;
                    double x = v.getX() * t;
                    double y = v.getY() * t + 1.5;
                    double z = v.getZ() * t;
                    loc.add(x,y,z);
                    ArmorStand armorStand = Bukkit.getServer().getWorld(p.getWorld().getName()).spawn(loc,ArmorStand.class);
                    armorStand.setAI(false);
                    armorStand.setGravity(false);
                    armorStand.setHelmet(new ItemStack(Material.DIAMOND_BLOCK, (short) 14));
                    armorStand.setVisible(false);
                    //p.playEffect(loc, Effect.HAPPY_VILLAGER,1);
                    loc.subtract(x,y,z);
                    if(t > 30){
                        cancel();
                    }
                }
            }.runTaskTimer(this,0,1);
        }
    }



}
