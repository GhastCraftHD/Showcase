package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.instance.DisplayCache;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropListener implements Listener {

    private Showcase main;

    public PlayerDropListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        if(player.isSneaking()){
            e.setCancelled(true);
            ItemDisplay display = main.getEntityManager().spawnItemDisplay(
                    player.getLocation().add(0, 1, 0),
                    e.getItemDrop().getItemStack().getType()
            );
            main.getClipboardManager().updateClipboard(player.getUniqueId(), new DisplayCache(display, main.getEntityManager().getInteraction(display)));
        }
    }

}
