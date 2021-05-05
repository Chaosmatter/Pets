package de.chaosmatter.pets.commands;

import de.chaosmatter.pets.Pet;
import de.chaosmatter.pets.Pets;
import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.UUID;

public class PetManager {
    private final Pets plugin;

    public PetManager(Pets plugin) {
        this.plugin = plugin;
    }

    @Getter
    private final HashMap<UUID, Pet> petMap = new HashMap<>();

    public Pet getPet(UUID uuid) {
        if (!this.petMap.containsKey(uuid)) {
            System.out.println("Error: Pet matching UUID " + uuid + " does not exist!");
            return null;
        }
        return this.petMap.get(uuid);
    }

    public Pet createPet(UUID uuid, EntityType entityType) {
        Pet pet = new Pet(uuid, entityType);
        this.petMap.put(uuid, pet);
        pet.spawn();
        return pet;
    }

    public void removePet(Pet pet) {
        pet.deSpawn();
        this.petMap.remove(pet.getOwner().getUniqueId(), pet);
    }

    public void renamePet(Pet pet, String newName) {
        pet.setDisplayName(newName);
    }

    public void changePetType(Pet pet, EntityType newEntityType) {
        pet.changeType(newEntityType);
    }

}
