package de.leghast.showcase.manager;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.instance.settings.AdjusterSettings;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class SettingsManager {

    private Showcase main;

    private HashMap<UUID, AdjusterSettings> adjusterSettings;

    public SettingsManager(Showcase main){
        this.main = main;
        adjusterSettings = new HashMap<>();
    }

    public boolean hasAdjusterSettings(UUID uuid){
        return adjusterSettings.containsKey(uuid);
    }

    public AdjusterSettings getAdjusterSettings(UUID uuid){
        return adjusterSettings.get(uuid);
    }

    public void addAdjusterSettings(UUID uuid){
        adjusterSettings.put(uuid, new AdjusterSettings(Bukkit.getPlayer(uuid)));
    }

    public void removeAdjusterSettings(UUID uuid){
        adjusterSettings.remove(uuid);
    }



}
