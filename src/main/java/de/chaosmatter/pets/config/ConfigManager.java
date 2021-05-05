package de.chaosmatter.pets.config;

import de.chaosmatter.pets.Pets;
import lombok.Getter;

import java.util.Objects;

public class ConfigManager {
    private final Pets plugin;

    public ConfigManager(Pets plugin) {
        this.plugin = plugin;
        createConfig();
    }

    public void createConfig() {
        this.plugin.getConfig().addDefault("Prefix", "&a&lPets §8»");
        this.plugin.getConfig().addDefault("NoPermission", "%prefix% &cDafür hast du keine Berechtigung!");
        this.plugin.getConfig().addDefault("CreatedPet", "%prefix% &eDein Haustier wurde erstellt!");
        this.plugin.getConfig().addDefault("RemovedPet", "%prefix% &cDein Haustier wurde entfernt!");
        this.plugin.getConfig().addDefault("RemovedAllPetsPlayer", "%prefix% &aDu hast erfolgreich alle Haustiere entfernt!");
        this.plugin.getConfig().addDefault("RemovedAllPetsBroadcast", "%prefix% &4Es wurden alle Haustiere entfernt!");
        this.plugin.getConfig().addDefault("UpdatedPetProperties", "%prefix% &eDein Haustier wurde aktualisiert.");
        this.plugin.getConfig().addDefault("HaveNoPet", "%prefix% &cDu hast noch kein Haustier!");

        this.plugin.getConfig().options().copyDefaults(true);
        this.plugin.saveConfig();

        this.prefix = Objects.requireNonNull(this.plugin.getConfig().getString("Prefix")).replace("&", "§");
        this.noPermission = Objects.requireNonNull(this.plugin.getConfig().getString("NoPermission")).replace("&", "§").replace("%prefix%", this.prefix);
        this.createdPet = Objects.requireNonNull(this.plugin.getConfig().getString("CreatedPet")).replace("&", "§").replace("%prefix%", this.prefix);
        this.removedPet = Objects.requireNonNull(this.plugin.getConfig().getString("RemovedPet")).replace("&", "§").replace("%prefix", this.prefix);
        this.removedAllPetsPlayer = Objects.requireNonNull(Objects.requireNonNull(this.plugin.getConfig().getString("RemovedAllPetsPlayer")).replace("&", "§").replace("%prefix%", this.prefix));
        this.removedAllPetsBroadcast = Objects.requireNonNull(Objects.requireNonNull(this.plugin.getConfig().getString("RemovedAllPetsBroadcast")).replace("&", "§").replace("%prefix%", this.prefix));
        this.updatedPet = Objects.requireNonNull(Objects.requireNonNull(this.plugin.getConfig().getString("UpdatedPetProperties")).replace("&", "§").replace("%prefix%", this.prefix));
        this.haveNoPet = Objects.requireNonNull(this.plugin.getConfig().getString("HaveNoPet")).replace("&", "§").replace("%prefix%", this.prefix);
    }

    @Getter
    private String prefix;
    @Getter
    private String noPermission;
    @Getter
    private String createdPet;
    @Getter
    private String removedPet;
    @Getter
    private String removedAllPetsPlayer;
    @Getter
    private String removedAllPetsBroadcast;
    @Getter
    private String updatedPet;
    @Getter
    private String haveNoPet;
}
