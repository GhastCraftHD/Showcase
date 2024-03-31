package de.leghast.showcase;

import de.leghast.showcase.command.ShowcaseCommand;
import de.leghast.showcase.listener.*;
import de.leghast.showcase.manager.ClipboardManager;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.manager.EntityManager;
import de.leghast.showcase.manager.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public final class Showcase extends JavaPlugin {

    public static String PERMISSION = "showcase.use";

    private EntityManager entityManager;
    private ClipboardManager clipboardManager;
    private SettingsManager settingsManager;

    private boolean updateAvailable = false;
    private String latestVersion = this.getPluginMeta().getVersion();

    @Override
    public void onLoad(){
        ConfigManager.setupConfig(this);
    }

    @Override
    public void onEnable() {
        registerListener();
        initialiseManagers();
        registerCommands();
        checkForUpdate();
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
        entityManager = new EntityManager(this);
        clipboardManager = new ClipboardManager();
        settingsManager = new SettingsManager();
    }

    private void registerListener(){
        Bukkit.getPluginManager().registerEvents(new ChunkListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(this), this);
    }

    private void registerCommands(){
        getCommand("showcase").setExecutor(new ShowcaseCommand(this));
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

    private void checkForUpdate() {
        if (!ConfigManager.CHECK_FOR_UPDATE) return;

        String apiUrl = "https://api.github.com/repos/GhastCraftHD/Showcase/releases/latest";

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                try {
                    connection.setRequestProperty("Content-Type", "application/json");

                    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) return;

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String input;
                    StringBuilder response = new StringBuilder();

                    while ((input = in.readLine()) != null) {
                        response.append(input);
                    }

                    in.close();
                    connection.disconnect();

                    if (!response.toString().contains("tag_name")) return;

                    latestVersion = response.toString().split("\"tag_name\":\"v")[1].split("\",")[0];

                } finally {
                    connection.disconnect();
                }

            } catch (Exception ignore) {}

            updateAvailable = !Objects.equals(latestVersion, this.getPluginMeta().getVersion());
        });

    }

    public boolean isUpdateAvailable(){
        return updateAvailable;
    }

    public String getLatestReleaseVersion(){
        return latestVersion;
    }

}
