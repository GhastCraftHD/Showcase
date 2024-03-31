package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.constant.Message;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.ui.AnvilInputHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Showcase main;

    public PlayerJoinListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        Bukkit.getScheduler().runTaskLaterAsynchronously(main,
                () -> {
                    if(player.isOp()){
                        if(main.isUpdateAvailable()){
                            player.sendMessage(Message.newVersionAvailable(main.getLatestReleaseVersion()));
                        }
                    }
                }, 10L
        );

    }

}
