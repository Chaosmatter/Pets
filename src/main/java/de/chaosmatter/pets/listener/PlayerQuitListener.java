package de.chaosmatter.pets.listener;

import de.chaosmatter.pets.Pets;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final Pets plugin;

    public PlayerQuitListener(Pets plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(this.plugin.getPetManager().getPetMap().containsKey(player.getUniqueId())) {
            this.plugin.getPetManager().getPet(player.getUniqueId()).deSpawn();
            this.plugin.getPetManager().getPetMap().remove(player.getUniqueId(), this.plugin.getPetManager().getPet(player.getUniqueId()));
        }
    }
}
