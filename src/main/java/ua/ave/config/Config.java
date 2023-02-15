package ua.ave.config;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import ua.ave.data.Constants;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import static ua.ave.data.Constants.config;
import static ua.ave.data.Constants.plugin;

public class Config {

    public int lycanCooldown = 10;
    public int shadowFiendRequiemCooldown = 50;
    public int shadowFiendCoilCooldown = 10;
    public int abaddonCooldown = 60;
    public int tidehunterCooldown = 50;
    public int tidehunterStickCooldown = 5;

    public static Config reloadConfig() {
        Gson gson = new Gson();
        File configFile = Paths.get(plugin.getDataFolder().toString(), "config.json").toFile();
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
        try {
            if (!configFile.exists()) {
                FileWriter writer = new FileWriter(configFile);
                writer.write(gson.toJson(config));
                writer.close();
            }

            Config config = gson.fromJson(new FileReader(configFile), Config.class);
            Bukkit.getServer().broadcastMessage("Config reloaded");
            return config;
        } catch (IOException e) {
            Bukkit.getServer().broadcastMessage("Reloading error");
            e.printStackTrace();
            return Constants.config;
        }
    }

}
