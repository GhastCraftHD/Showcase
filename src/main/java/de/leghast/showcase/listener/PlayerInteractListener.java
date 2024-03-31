package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.constant.Colors;
import de.leghast.showcase.constant.Message;
import de.leghast.showcase.display.DisplayWrapper;
import de.leghast.showcase.handler.AdjusterInteractionHandler;
import de.leghast.showcase.settings.AdjusterSettings;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.ui.UserInterface;
import de.leghast.showcase.ui.AnvilInputHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private final Showcase main;

    public PlayerInteractListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(!player.hasPermission(Showcase.PERMISSION)) return;

        Material material = player.getInventory().getItemInMainHand().getType();
        if(material != ConfigManager.TOOL) return;

        e.setCancelled(true);

        AdjusterInteractionHandler.handleAdjusterInteraction(main, player, e.getAction());

    }

}
