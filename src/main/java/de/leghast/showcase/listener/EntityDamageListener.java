package de.leghast.showcase.listener;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.leghast.showcase.Showcase;
import de.leghast.showcase.display.DisplayWrapper;
import de.leghast.showcase.handler.AdjusterInteractionHandler;
import de.leghast.showcase.manager.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class EntityDamageListener implements Listener {

    private final Showcase main;

    public EntityDamageListener(Showcase main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Player player)) return;
        if(!player.hasPermission(Showcase.PERMISSION)) return;

        handlePlayerHit(e, player);
    }

    private void handlePlayerHit(EntityDamageByEntityEvent e, Player player) {
        Material material = player.getInventory().getItemInMainHand().getType();
        if(material != ConfigManager.TOOL) return;
        if(!(e.getEntity() instanceof Interaction interaction)) return;

        if (!main.getEntityManager().isLinked(interaction)) return;

        ItemDisplay display = main.getEntityManager().getItemDisplay(interaction);

        if(player.isSneaking()){
            e.setCancelled(true);
            if (main.getClipboardManager().hasClipboard(player.getUniqueId())) {
                if (display != main.getClipboardManager().getClipboard(player.getUniqueId()).getDisplay()) {
                    main.getClipboardManager().updateClipboard(player.getUniqueId(), new DisplayWrapper(display, main.getEntityManager().getInteraction(display)));
                } else {
                    main.getClipboardManager().removeClipboard(player.getUniqueId());
                }
            } else {
                main.getClipboardManager().updateClipboard(player.getUniqueId(), new DisplayWrapper(display, main.getEntityManager().getInteraction(display)));
            }
        }else{
            AdjusterInteractionHandler.handleAdjusterInteraction(main, player, Action.LEFT_CLICK_AIR);
        }
    }

}
