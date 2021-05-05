package de.chaosmatter.pets;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Pet {
    private UUID uuid;
    @Getter @Setter
    private Player owner;
    @Getter @Setter
    private EntityType entityType;
    @Getter @Setter
    private String displayName;
    @Getter
    private Entity entity;

    public Pet(UUID uuid, EntityType entityType) {
        this.uuid = uuid;
        this.owner = Bukkit.getPlayer(uuid);
        this.entityType = entityType;

    }

    public void spawn() {
        Entity entity = (Entity)owner.getWorld().spawnEntity(owner.getLocation(), entityType);
        this.entity = entity;
    }

    public void updateDisplayName(String displayName) {
        this.entity.setCustomName(displayName.replace("&", "§"));
        this.entity.setCustomNameVisible(true);
    }

    public void deSpawn() {
        this.entity.remove();
    }

    public void changeType(EntityType newEntityType) {
        this.entityType = newEntityType;
        deSpawn();
        spawn();
        if(this.displayName != null) {
            updateDisplayName(this.displayName);
        }
    }
}
