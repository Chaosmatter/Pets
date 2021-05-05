package de.chaosmatter.pets;

import de.chaosmatter.pets.commands.PetCommand;
import de.chaosmatter.pets.commands.PetManager;
import de.chaosmatter.pets.config.ConfigManager;
import de.chaosmatter.pets.listener.EntityDamageListener;
import de.chaosmatter.pets.listener.PlayerQuitListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Pets extends JavaPlugin {
    @Getter
    private static Pets instance;
    private PetManager petManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        this.petManager = new PetManager(this);
        this.configManager = new ConfigManager(this);

        getCommand("pet").setExecutor(new PetCommand(this));
        getCommand("pets").setExecutor(new PetCommand(this));

        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
    }
    @Override
    public void onDisable() {
    }

}
