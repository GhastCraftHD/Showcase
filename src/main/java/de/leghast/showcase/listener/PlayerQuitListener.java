package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final Showcase main;

    public PlayerQuitListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        main.getClipboardManager().removeClipboard(e.getPlayer().getUniqueId());
        main.getSettingsManager().removeAdjusterSettings(e.getPlayer().getUniqueId());
        main.getSettingsManager().removeEnabled(e.getPlayer().getUniqueId());
    }

}
