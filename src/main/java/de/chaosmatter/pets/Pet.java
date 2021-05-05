package de.chaosmatter.pets;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftCreature;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Random;
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

    public void followPlayer(Creature creature, Player player, double Speed){
        Location location = player.getLocation();


        Random rnd = new Random();
        int zufall = rnd.nextInt(6);
        switch(zufall){
            case 0:
                location.add(1.5,0,1.5);
                break;
            case 1:
                location.add(0,0,1.5);
                break;
            case 2:
                location.add(1.5,0,0);
                break;
            case 3:
                location.subtract(1.5,0,1.5);
                break;
            case 4:
                location.subtract(0,0,1.5);
                break;
            case 5:
                location.subtract(1.5,0,0);
                break;
        }


        if(location.distanceSquared(creature.getLocation()) > 100){
            if(!player.isOnGround()){
                return;
            }
            creature.teleport(location);
        }else{
            ((CraftCreature)creature).getHandle().getNavigation().a(location.getX(),location.getY(),location.getZ(),Speed);
        }
    }
}
