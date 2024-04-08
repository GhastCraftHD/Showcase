package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.constant.Message;
import de.leghast.showcase.display.DisplayWrapper;
import de.leghast.showcase.handler.DisplaySpawnHandler;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropListener implements Listener {

    private final Showcase main;

    public PlayerDropListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        if(!player.hasPermission(Showcase.PERMISSION)) return;
        if(!player.isSneaking()) return;

        if(!main.getSettingsManager().isEnabled(player.getUniqueId())){
            player.sendMessage(Message.NOT_ENABLED);
            return;
        }

        e.setCancelled(true);
        new DisplaySpawnHandler(main, player, e.getItemDrop().getItemStack());

    }

}
