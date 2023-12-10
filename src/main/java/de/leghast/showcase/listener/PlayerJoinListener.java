package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private Showcase main;

    public PlayerJoinListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        main.getSettingsManager().addAdjusterSettings(e.getPlayer().getUniqueId());
    }

}
