package de.leghast.showcase;

import de.leghast.showcase.listener.*;
import de.leghast.showcase.manager.ClipboardManager;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.manager.EntityManager;
import de.leghast.showcase.manager.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class Showcase extends JavaPlugin {

    private EntityManager entityManager;
    private ClipboardManager clipboardManager;
    private SettingsManager settingsManager;

    @Override
    public void onEnable() {
        registerListener();
        initialiseManagers();
        initialise();
    }

    @Override
    public void onDisable(){
        for(World world : Bukkit.getWorlds()){
            for(Chunk chunk : world.getLoadedChunks()){
                entityManager.removeInteractionEntitiesFromChunk(chunk);
            }
        }
    }

    private void initialiseManagers(){
        ConfigManager.setupConfig(this);
        entityManager = new EntityManager(this);
        clipboardManager = new ClipboardManager(this);
        settingsManager = new SettingsManager(this);
    }

    private void initialise(){
        for(World world : Bukkit.getWorlds()){
            for(Chunk chunk : world.getLoadedChunks()){
                entityManager.spawnInteractionEntitiesInChunk(chunk);
            }
        }
    }

    private void registerListener(){
        Bukkit.getPluginManager().registerEvents(new ChunkListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public ClipboardManager getClipboardManager(){
        return clipboardManager;
    }

    public SettingsManager getSettingsManager(){
        return settingsManager;
    }

}
