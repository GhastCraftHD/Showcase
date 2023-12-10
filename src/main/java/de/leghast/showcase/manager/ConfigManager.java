package de.leghast.showcase.manager;

import de.leghast.showcase.Showcase;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ItemDisplay;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setupConfig(Showcase main){
        ConfigManager.config = main.getConfig();
        main.saveDefaultConfig();
    }

    public static ItemDisplay.ItemDisplayTransform getDefaultTransform(){
        return ItemDisplay.ItemDisplayTransform.valueOf(config.getString("default-transform"));
    }

    public static Material getToolMaterial(){
        return Material.matchMaterial(config.getString("tool"));
    }

}
