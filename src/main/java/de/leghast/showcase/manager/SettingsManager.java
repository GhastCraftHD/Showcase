package de.leghast.showcase.manager;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.settings.AdjusterSettings;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SettingsManager {


    private final Map<UUID, AdjusterSettings> adjusterSettings;
    private final Map<UUID, Boolean> enabled;


    public SettingsManager(){
        adjusterSettings = new HashMap<>();
        enabled = new HashMap<>();
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

    public boolean isEnabled(UUID uuid){
        if(!enabled.containsKey(uuid)) setEnabled(uuid, ConfigManager.ENABLED_BY_DEFAULT);
        return enabled.get(uuid);
    }

    public void setEnabled(UUID uuid, boolean enabled){
        this.enabled.put(uuid, enabled);
    }

    public void removeEnabled(UUID uuid){
        enabled.remove(uuid);
    }

    public void toggleEnabled(UUID uuid){
        enabled.put(uuid, !isEnabled(uuid));
    }


}
