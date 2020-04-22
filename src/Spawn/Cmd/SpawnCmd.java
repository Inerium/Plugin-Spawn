package Spawn.Cmd;

import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Spawn.Launch.Launch;

public class SpawnCmd
  implements CommandExecutor
{
  Launch main;

  public SpawnCmd(Launch plugin)
  {
    this.main = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (sender.getName() == "CONSOLE")
    {
      System.out.println("Vous ne pouvez pas executer cette commande depuis la console !");
      return true;
    }
    Player p = (Player)sender;
    String noPerm = ChatColor.DARK_RED + "Vous n'avez pas la permission !";
    if (p.hasPermission("TutoRG.SpawnTp"))
    {
      boolean delay = this.main.getConfig().getBoolean("ConfigSpawn.plugin.Teleport_Delay.Enabled");
      boolean cooldown = this.main.getConfig().getBoolean("ConfigSpawn.Cooldown.Enabled");
      boolean multiworld = this.main.getConfig().getBoolean("ConfigSpawn.MultiWorldSpawns");
      if (cooldown)
      {
        int cooldownTime = this.main.getConfig().getInt("ConfigSpawn.Cooldown.Cooldown");
        if ((!p.hasPermission("TutoRG.SpawnTp.cooldown.bypass")) &&
          (this.main.cooldowns.containsKey(sender.getName())))
        {
          long secondsLeft = ((Long)this.main.cooldowns.get(sender.getName())).longValue() / 1000L + cooldownTime - System.currentTimeMillis() / 1000L;
          if (secondsLeft > 0L)
          {
            sender.sendMessage("Vous ne pouvez pas utiliser cette commande pendant " + secondsLeft + " second(s)!");
            return true;
          }
        }
        this.main.cooldowns.put(sender.getName(), Long.valueOf(System.currentTimeMillis()));
      }
      if ((delay) && (!p.hasPermission("TutoRG.SpawnTp.delay.bypass")))
      {
        if (multiworld)
        {
          if (this.main.getConfig().contains("ConfigSpawn.Spawns." + p.getWorld().getName())) {
            this.main.dTeleport(getSpawnLocation(p.getWorld()), p);
          } else {
            p.sendMessage(ChatColor.RED + "Aucun point de spawn dans ce monde !");
          }
        }
        else if (this.main.getConfig().contains("ConfigSpawn.Spawns"))
        {
          Set spawns = this.main.getConfig().getConfigurationSection("ConfigSpawn.Spawns").getKeys(false);
          String spawnsstr = spawns.toString().replace("[", "").replace("]", "");
          String[] str = spawnsstr.split(", ");
       
          this.main.dTeleport(getSpawnLocation(this.main.getServer().getWorld(str[0])), p);
          p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Ineria Spawn" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Vous avez était téléporter au spawn !");
        }
        else
        {
          p.sendMessage(ChatColor.RED + "Aucun point de spawn !");
        }
      }
      else if (multiworld)
      {
        if (this.main.getConfig().contains("ConfigSpawn.Spawns." + p.getWorld().getName())) {
          this.main.Teleport(getSpawnLocation(p.getWorld()), p);
        } else {
          p.sendMessage(ChatColor.RED + "Aucun point de spawn dans ce monde !");
        }
      }
      else if (this.main.getConfig().contains("ConfigSpawn.Spawns"))
      {
        Set spawns = this.main.getConfig().getConfigurationSection("ConfigSpawn.Spawns").getKeys(false);
        String spawnsstr = spawns.toString().replace("[", "").replace("]", "");
        String[] str = spawnsstr.split(", ");
     
        this.main.Teleport(getSpawnLocation(this.main.getServer().getWorld(str[0])), p);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Spawn Ineria" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Vous avez était téléporter au spawn !");
      }
      else
      {
        p.sendMessage(ChatColor.RED + "Aucun point de spawn !");
      }
    }
    else
    {
      p.sendMessage(noPerm);
    }
    return true;
  }

  public Location getSpawnLocation(World w)
  {
    boolean multiworld = this.main.getConfig().getBoolean("ConfigSpawn.MultiWorldSpawns");
 
    String worldName = "";
    if (multiworld)
    {
      worldName = w.getName();
    }
    else
    {
      Set spawns = this.main.getConfig().getConfigurationSection("ConfigSpawn.Spawns").getKeys(false);
      String spawnsstr = spawns.toString().replace("[", "").replace("]", "");
      String[] str = spawnsstr.split(", ");
   
      worldName = str[0];
    }
    String loc = this.main.getConfig().getString("ConfigSpawn.Spawns." + worldName + ".location");
    Float pitch = Float.valueOf(Float.parseFloat(this.main.getConfig().getString("ConfigSpawn.Spawns." + worldName + ".pitch")));
    Float yaw = Float.valueOf(Float.parseFloat(this.main.getConfig().getString("ConfigSpawn.Spawns." + worldName + ".yaw")));
    String[] xyz = loc.split(", ");
 
    Location SpawnLoc = new Location(w, Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
    SpawnLoc.setPitch(pitch.floatValue());
    SpawnLoc.setYaw(yaw.floatValue());
    SpawnLoc.add(0.5D, 0.0D, 0.5D);
    return SpawnLoc;
  }
}