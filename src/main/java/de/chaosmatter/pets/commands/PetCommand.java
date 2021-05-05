package de.chaosmatter.pets.commands;

import de.chaosmatter.pets.Pets;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class PetCommand implements CommandExecutor {
    private final Pets plugin;

    public PetCommand(Pets plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("pet") || command.getName().equalsIgnoreCase("pets")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                //todo
                return false;
            }
            sender.sendMessage("Must be a Player!");
        }
        return false;
    }
}
