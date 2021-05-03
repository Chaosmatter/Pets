package de.chaosmatter.pets;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class Pets extends JavaPlugin {
    @Getter
    private static Pets instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }
}
