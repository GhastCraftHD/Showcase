package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldUnloadEvent;

public class WorldListener implements Listener {

    private Showcase main;

    public WorldListener(Showcase main){
        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorldUnload(WorldUnloadEvent e){
        for(Chunk chunk : e.getWorld().getLoadedChunks()){
            main.getEntityManager().removeInteractionEntitiesFromChunk(chunk);
        }
    }

}
