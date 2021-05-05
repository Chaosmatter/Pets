package de.chaosmatter.pets.commands;

import de.chaosmatter.pets.Pet;
import de.chaosmatter.pets.Pets;
import org.bukkit.Bukkit;
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
                if(args.length == 1 && args[0].equalsIgnoreCase("removeall")) {
                    //remove all pets from the server
                    this.plugin.getPetManager().removeAllPets();
                    player.sendMessage(this.plugin.getConfigManager().getRemovedAllPetsPlayer());
                    Bukkit.broadcastMessage(this.plugin.getConfigManager().getRemovedAllPetsBroadcast());
                    return false;
                }
                if(args.length == 0) {
                    if(this.plugin.getPetManager().getPetMap().containsKey(player.getUniqueId())) {
                        //todo pet management gui
                        return false;
                    }
                    //todo create pet gui
                    return false;
                }
                if(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("spawn")) {
                    if(this.plugin.getPetManager().getPetMap().containsKey(player.getUniqueId())) {
                        player.sendMessage(this.plugin.getConfigManager().getHavePetAlready());
                        return false;
                    }
                    EntityType entityType = EntityType.valueOf(args[1]);
                    this.plugin.getPetManager().createPet(player.getUniqueId(), entityType);
                    return false;
                }
                if(args[0].equalsIgnoreCase("remove")) {
                    if(this.plugin.getPetManager().getPetMap().containsKey(player.getUniqueId())) {
                        this.plugin.getPetManager().removePet(this.plugin.getPetManager().getPet(player.getUniqueId()));
                        return false;
                    }
                    player.sendMessage(this.plugin.getConfigManager().getHaveNoPet());
                    return false;
                }

                if(args[0].equalsIgnoreCase("changetype")) {
                    if(this.plugin.getPetManager().getPetMap().containsKey(player.getUniqueId())) {
                        EntityType entityType = EntityType.valueOf(args[1]);
                        this.plugin.getPetManager().changePetType(this.plugin.getPetManager().getPet(player.getUniqueId()), entityType);
                        return false;
                    }
                    player.sendMessage(this.plugin.getConfigManager().getHaveNoPet());
                    return false;
                }

                if(args[0].equalsIgnoreCase("rename")) {
                    if(this.plugin.getPetManager().getPetMap().containsKey(player.getUniqueId())) {
                        this.plugin.getPetManager().renamePet(this.plugin.getPetManager().getPet(player.getUniqueId()), args[1].replace("&", "§"));
                        return false;
                    }
                    player.sendMessage(this.plugin.getConfigManager().getHaveNoPet());
                    return false;
                }

            }
            sender.sendMessage("Must be a Player!");
        }
        return false;
    }
}
