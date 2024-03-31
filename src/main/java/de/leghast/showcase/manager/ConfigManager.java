package de.leghast.showcase.manager;

import de.leghast.showcase.Showcase;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ItemDisplay;

public class ConfigManager {

    private static FileConfiguration config;

    public static boolean CHECK_FOR_UPDATE;
    public static boolean ENABLED_BY_DEFAULT;
    public static ItemDisplay.ItemDisplayTransform DEFAULT_TRANSFORM;
    public static Material TOOL;

    public static void setupConfig(Showcase main){
        ConfigManager.config = main.getConfig();
        main.saveDefaultConfig();
        CHECK_FOR_UPDATE = getBool("check-for-update");
        ENABLED_BY_DEFAULT = getBool("enabled-by-default");
        DEFAULT_TRANSFORM = getTransform("default-transform");
        TOOL = getMaterial("tool");
    }

    private static String getString(String path){
        return config.getString(path);
    }

    private static boolean getBool(String path){
        return config.getBoolean(path);
    }

    private static int getInt(String path){
        return config.getInt(path);
    }

    private static double getDouble(String path){
        return config.getDouble(path);
    }

    private static Material getMaterial(String path){
        return Material.matchMaterial(getString(path));
    }

    private static ItemDisplay.ItemDisplayTransform getTransform(String path){
        return ItemDisplay.ItemDisplayTransform.valueOf(getString(path));
    }

}
