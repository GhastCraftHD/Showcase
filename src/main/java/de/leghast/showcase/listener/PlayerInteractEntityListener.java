package de.leghast.showcase.listener;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.leghast.showcase.Showcase;
import de.leghast.showcase.handler.AdjusterInteractionHandler;
import de.leghast.showcase.manager.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PlayerInteractEntityListener implements Listener {

    private final Showcase main;

    public PlayerInteractEntityListener(Showcase main) {
        this.main = main;
    }


    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if(!player.hasPermission(Showcase.PERMISSION)) return;

        Material material = player.getInventory().getItemInMainHand().getType();
        if(material != ConfigManager.TOOL) return;

        if(!(e.getRightClicked() instanceof Interaction interaction)) return;
        if (!main.getEntityManager().isLinked(interaction)) return;

        AdjusterInteractionHandler.handleAdjusterInteraction(main, player, Action.RIGHT_CLICK_BLOCK);

    }
}
