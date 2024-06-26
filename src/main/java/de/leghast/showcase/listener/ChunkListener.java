package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkListener implements Listener {

    private final Showcase main;

    public ChunkListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    private void onChunkLoad(ChunkLoadEvent e){
        main.getEntityManager().spawnInteractionEntitiesInChunk(e.getChunk());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onChunkUnload(ChunkUnloadEvent e){
        main.getEntityManager().removeInteractionEntitiesFromChunk(e.getChunk());
    }

}
