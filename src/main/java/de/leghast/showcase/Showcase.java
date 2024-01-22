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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class Showcase extends JavaPlugin {

    private EntityManager entityManager;
    private ClipboardManager clipboardManager;
    private SettingsManager settingsManager;

    private String owner = "LeGhast";
    private String repo = "Showcase";

    private boolean updateAvailable;

    @Override
    public void onEnable() {
        registerListener();
        initialiseManagers();
        initialise();
        updateAvailable = isUpdateAvailable("v" + getDescription().getVersion());
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
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
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

    public String getLatestReleaseVersion(){
        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/releases/latest";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    return response.toString().contains("tag_name")
                            ? response.toString().split("\"tag_name\":\"")[1].split("\",")[0]
                            : null;
                } else {
                    // Handle the error response
                    System.out.println("Error: " + connection.getResponseCode() + " " + connection.getResponseMessage());
                    return null;
                }
            } finally {
                connection.disconnect();
            }
        }catch (Exception e){
            return null;
        }
    }

    private boolean isUpdateAvailable(String currentVersion){
        String latestVersion = getLatestReleaseVersion();
        return latestVersion != null && !latestVersion.equals(currentVersion);
    }

    public boolean isUpdateAvailable(){
        return updateAvailable;
    }

}
