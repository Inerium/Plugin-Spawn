package Spawn.Launch;

public class Config {

    public static void setConfig() {
        if (!Launch.config.contains("ConfigSpawn.Teleport_Message.Enabled")) {
            Launch.config.set("ConfigSpawn.Teleport_Message.Enabled", Boolean.valueOf(true));
        }
        if (!Launch.config.contains("ConfigSpawn.Teleport_Message.Message")) {
            Launch.config.set("ConfigSpawn.Teleport_Message.Message", "&aTéléporter au spawn");
        }
        if (!Launch.config.contains("ConfigSpawn.Cooldown.Enabled")) {
            Launch.config.set("ConfigSpawn.Cooldown.Enabled", Boolean.valueOf(true));
        }
        if (!Launch.config.contains("ConfigSpawn.Cooldown.Cooldown")) {
            Launch.config.set("ConfigSpawn.Cooldown.Cooldown", Integer.valueOf(60));
        }
        if (!Launch.config.contains("ConfigSpawn.Teleport_Delay.Enabled")) {
            Launch.config.set("ConfigSpawn.Teleport_Delay.Enabled", Boolean.valueOf(true));
        }
        if (!Launch.config.contains("ConfigSpawn.Teleport_Delay.Delay")) {
            Launch.config.set("ConfigSpawn.Teleport_Delay.Delay", Integer.valueOf(5));
        }
        if (!Launch.config.contains("ConfigSpawn.NewPlayerSpawn")) {
            Launch.config.set("ConfigSpawn.NewPlayerSpawn", Boolean.valueOf(false));
        }
        if (!Launch.config.contains("ConfigSpawn.MultiWorldSpawns")) {
            Launch.config.set("ConfigSpawn.MultiWorldSpawns", Boolean.valueOf(false));
        }
    }

}



