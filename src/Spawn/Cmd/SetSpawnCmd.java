package Spawn.Cmd;

import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Spawn.Launch.Launch;

public class SetSpawnCmd implements CommandExecutor {

    Launch main;

    public SetSpawnCmd(Launch plugin) {
        this.main = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.getName() == "CONSOLE")
        {
            System.out.println("Vous ne pouvez pas executer cette commande depuis la console !");
            return true;
        }
        Player p = (Player)sender;
        String noPerm = ChatColor.DARK_RED + "Vous n'avez pas la permission !";
        if (p.hasPermission("Spawn.SetSpawn")) {
            if (this.main.getConfig().getBoolean("ConfigSpawn.MultiWorldSpawns")) {
                String world = p.getWorld().getName();
                String location = p.getLocation().getBlockX() + ", " + p.getLocation().getBlockY() + ", " + p.getLocation().getBlockZ();
                Float pitch = Float.valueOf(p.getLocation().getPitch());
                Float yaw = Float.valueOf(p.getLocation().getYaw());
                if (this.main.getConfig().contains("ConfigSpawn.Spawns." + world)) {
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Ineria Spawn" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Point de spawn déplacer dans le monde '" + ChatColor.GOLD + world + ChatColor.GREEN + "'");
                } else {
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Ineria Spawn" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Point de spawn définie dans le monde '" + ChatColor.GOLD + world + ChatColor.GREEN + "'");
                }
                this.main.getConfig().set("ConfigSpawn.Spawns." + world + ".location", location);
                this.main.getConfig().set("ConfigSpawn.Spawns." + world + ".pitch", pitch);
                this.main.getConfig().set("ConfigSpawn.Spawns." + world + ".yaw", yaw);
                this.main.saveConfig();
            }
            else if (this.main.getConfig().contains("ConfigSpawn.Spawns"))
            {
                Set spawns = this.main.getConfig().getConfigurationSection("ConfigSpawn.Spawns").getKeys(false);
                String spawnsstr = spawns.toString().replace("[", "").replace("]", "");
                String[] str = spawnsstr.split(", ");
                String world = str[0];

                String location = p.getLocation().getBlockX() + ", " + p.getLocation().getBlockY() + ", " + p.getLocation().getBlockZ();
                Float pitch = Float.valueOf(p.getLocation().getPitch());
                Float yaw = Float.valueOf(p.getLocation().getYaw());
                if (this.main.getConfig().contains("ConfigSpawn.Spawns." + world)) {
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Spawn Ineria" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Point de spawn déplacer dans le monde '" + ChatColor.GOLD + world + ChatColor.GREEN + "'");
                } else {
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Spawn Ineria" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Point de spawn définie dans le monde '" + ChatColor.GOLD + world + ChatColor.GREEN + "'");
                }
                this.main.getConfig().set("ConfigSpawn.Spawns." + world + ".location", location);
                this.main.getConfig().set("ConfigSpawn.Spawns." + world + ".pitch", pitch);
                this.main.getConfig().set("ConfigSpawn.Spawns." + world + ".yaw", yaw);
                this.main.saveConfig();
            }
            else
            {
                String world = p.getWorld().getName();
                String location = p.getLocation().getBlockX() + ", " + p.getLocation().getBlockY() + ", " + p.getLocation().getBlockZ();
                Float pitch = Float.valueOf(p.getLocation().getPitch());
                Float yaw = Float.valueOf(p.getLocation().getYaw());
                if (this.main.getConfig().contains("ConfigSpawn.Spawns." + world)) {
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "H.Party Spawn" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Point de spawn déplacer dans le monde '" + ChatColor.GOLD + world + ChatColor.GOLD + "'");
                } else {
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "H.Party Spawn" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Point de spawn définie dans le monde '" + ChatColor.GOLD + world + ChatColor.GOLD + "'");
                }
                this.main.getConfig().set("ConfigSpawn.Spawns." + world + ".location", location);
                this.main.getConfig().set("ConfigSpawn.Spawns." + world + ".pitch", pitch);
                this.main.getConfig().set("ConfigSpawn.Spawns." + world + ".yaw", yaw);
                this.main.saveConfig();
            }
        }
        else {
            p.sendMessage(noPerm);
        }
        return true;
    }
}
