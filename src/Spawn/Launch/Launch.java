package Spawn.Launch;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import Spawn.Cmd.SetSpawnCmd;
import Spawn.Cmd.SpawnCmd;

public class Launch extends JavaPlugin {
 
    private String prefix = "[Spawn-Ineria]" ;
    private String phrase1 = "Le plugin est correctement charger !" ;
    private String phrase2 = "Le fichier de configuration est correctement sauvegarder !" ;
 
    public File configFile;
    public static FileConfiguration config;
    public HashMap<String, Long> cooldowns = new HashMap();
 
    @Override
    public void onEnable() {
        System.out.println(prefix + phrase1);
        this.configFile = new File(getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(this.configFile);
        try
        {
          config.save(this.configFile);
          System.out.println(this.prefix + "Configuration crée avec succèe");
        }
        catch (IOException e)
        {
          System.out.println(this.prefix + "Erreur pendant la création de la configuration" + e);
        }
        getCommand("setspawn").setExecutor(new SetSpawnCmd(this));
        // Commande pour définir le point de spawn
        getCommand("spawn").setExecutor(new SpawnCmd(this));
        //Commande pour se tp au point de spawn
        getLogger().info("Plugin démarré !");
      }
 
    @Override
    public void onDisable() {
        System.out.println(prefix + phrase2);
        getLogger().info("Plugin arrêté !");
    }
 
    public void Teleport(Location spawn, Player p)
      {
        this.cooldowns.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
        boolean msge = getConfig().getBoolean("ConfigSpawn.Teleport_Message.Enabled");
        String msg = getConfig().getString("ConfigSpawn.Teleport_Message.Message");
        if (msge) {
          p.sendMessage(msg.replaceAll("(&([a-f0-9]))", "§$2"));
        }
        p.teleport(spawn);
      }
   
      public void dTeleport(final Location spawn, final Player p)
      {
        int delays = getConfig().getInt("ConfigSpawn.Teleport_Delay.Delay");
        p.sendMessage(ChatColor.GRAY + "Téléportation dans " + delays + " Second(s)...");
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
        {
          public void run()
          {
            Launch.this.Teleport(spawn, p);
          }
        }, 20 * delays);
      }

}


