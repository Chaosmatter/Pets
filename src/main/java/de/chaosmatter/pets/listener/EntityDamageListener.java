package de.chaosmatter.pets.listener;

import de.chaosmatter.pets.Pet;
import de.chaosmatter.pets.Pets;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    private final Pets plugin;

    public EntityDamageListener(Pets plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        for (Pet pet : this.plugin.getPetManager().getPetMap().values()) {
            if(pet.getEntity() == entity) {
                event.setCancelled(true);
            }
        }
    }
}
