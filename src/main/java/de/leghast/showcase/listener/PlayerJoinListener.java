package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private Showcase main;

    public PlayerJoinListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(player.isOp()){
            if(main.isUpdateAvailable()){
                player.sendMessage(Util.PREFIX + "§aA new version of Miniaturise is available: §e" + main.getLatestReleaseVersion());
                player.sendMessage(Util.PREFIX + "§aDownload it now: §ehttps://github.com/LeGhast/Showcase/releases");
            }
        }
    }

}
