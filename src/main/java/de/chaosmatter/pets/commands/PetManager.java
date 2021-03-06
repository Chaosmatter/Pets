package de.chaosmatter.pets.commands;

import de.chaosmatter.pets.Pet;
import de.chaosmatter.pets.Pets;
import lombok.Getter;
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

    public void createPet(UUID uuid, EntityType entityType) {
        Pet pet = new Pet(uuid, entityType);
        this.petMap.put(uuid, pet);
        pet.spawn();
        pet.getOwner().sendMessage(Pets.getInstance().getConfigManager().getCreatedPet());
    }

    public void removePet(Pet pet) {
        pet.deSpawn();
        this.petMap.remove(pet.getOwner().getUniqueId(), pet);
        pet.getOwner().sendMessage(Pets.getInstance().getConfigManager().getRemovedPet());
    }

    public void renamePet(Pet pet, String newName) {
        pet.setDisplayName(newName);
        pet.getOwner().sendMessage(Pets.getInstance().getConfigManager().getUpdatedPet());
    }

    public void changePetType(Pet pet, EntityType newEntityType) {
        pet.changeType(newEntityType);
        pet.getOwner().sendMessage(Pets.getInstance().getConfigManager().getUpdatedPet());
    }

    public void removeAllPets() {
        for(Pet pet : this.petMap.values()) {
            pet.deSpawn();
        }
        petMap.clear();
    }

}
