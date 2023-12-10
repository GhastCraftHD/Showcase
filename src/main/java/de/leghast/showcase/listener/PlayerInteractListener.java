package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.instance.DisplayCache;
import de.leghast.showcase.instance.settings.AdjusterSettings;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.ui.UserInterface;
import org.bukkit.Material;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private Showcase main;

    public PlayerInteractListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player player){
            Material material = player.getInventory().getItemInMainHand().getType();
            if(material == ConfigManager.getToolMaterial() && e.getEntity() instanceof Interaction interaction && player.isSneaking()){
                if(main.getEntityManager().isLinked(interaction)){
                    e.setCancelled(true);
                    ItemDisplay display = main.getEntityManager().getItemDisplay(interaction);
                    main.getClipboardManager().updateClipboard(player.getUniqueId(), new DisplayCache(display, main.getEntityManager().getInteraction(display)));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Material material = player.getInventory().getItemInMainHand().getType();

        if(material == ConfigManager.getToolMaterial()){
            e.setCancelled(true);
            if(e.getAction().isLeftClick()){
                AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                switch (settings.getPage()){
                    case POSITION -> main.getClipboardManager().getClipboard(player.getUniqueId()).move(
                            settings.getPositionSettings().getAxis(),
                            settings.getPositionSettings().getFactor()
                    );
                    case ROTATION -> main.getClipboardManager().getClipboard(player.getUniqueId()).rotate(
                            settings.getPositionSettings().getAxis(),
                            settings.getPositionSettings().getFactor()
                    );
                }
            }else if(e.getAction().isRightClick()){
                if(player.isSneaking()){
                    new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
                }else{
                    AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                    switch (settings.getPage()){
                        case POSITION -> main.getClipboardManager().getClipboard(player.getUniqueId()).move(
                                settings.getPositionSettings().getAxis(),
                                -settings.getPositionSettings().getFactor()
                        );
                        case ROTATION -> main.getClipboardManager().getClipboard(player.getUniqueId()).rotate(
                                settings.getPositionSettings().getAxis(),
                                -settings.getPositionSettings().getFactor()
                        );
                    }
                }
            }
        }
    }

}
