package tech.miraidev.EnderCrystal.listener;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.EntityType;

public class Damage implements Listener {
    @EventHandler
    public void damage(EntityDamageEvent event){
        Entity entity = event.getEntity();
        if(entity.getType().equals(EntityType.ENDER_CRYSTAL)) {
            event.setCancelled(true);
        }
    }
}
