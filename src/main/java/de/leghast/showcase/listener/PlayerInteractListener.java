package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.instance.DisplayWrapper;
import de.leghast.showcase.instance.settings.AdjusterSettings;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.ui.UserInterface;
import de.leghast.showcase.util.Util;
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
            if(player.hasPermission("showcase.use")) {
                Material material = player.getInventory().getItemInMainHand().getType();
                if (material == ConfigManager.getToolMaterial() && e.getEntity() instanceof Interaction interaction && player.isSneaking()) {
                    if (main.getEntityManager().isLinked(interaction)) {
                        e.setCancelled(true);
                        ItemDisplay display = main.getEntityManager().getItemDisplay(interaction);
                        if (main.getClipboardManager().hasClipboard(player.getUniqueId())) {
                            if (display != main.getClipboardManager().getClipboard(player.getUniqueId()).getDisplay()) {
                                main.getClipboardManager().updateClipboard(player.getUniqueId(), new DisplayWrapper(display, main.getEntityManager().getInteraction(display)));
                            } else {
                                main.getClipboardManager().removeClipboard(player.getUniqueId());
                            }
                        } else {
                            main.getClipboardManager().updateClipboard(player.getUniqueId(), new DisplayWrapper(display, main.getEntityManager().getInteraction(display)));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Material material = player.getInventory().getItemInMainHand().getType();

        if(material == ConfigManager.getToolMaterial() && player.hasPermission("showcase.use")){
            if(!main.getSettingsManager().hasAdjusterSettings(player.getUniqueId())){
                main.getSettingsManager().addAdjusterSettings(player.getUniqueId());
            }
            e.setCancelled(true);
            if(main.getClipboardManager().hasClipboard(player.getUniqueId())){
                if(e.getAction().isLeftClick()){
                    AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                    switch (settings.getPage()){
                        case POSITION -> main.getClipboardManager().getClipboard(player.getUniqueId()).move(
                                settings.getPositionSettings().getAxis(),
                                settings.getPositionSettings().getFactor()
                        );
                        case ROTATION -> main.getClipboardManager().getClipboard(player.getUniqueId()).rotate(
                                settings.getRotationSettings().getAxis(),
                                settings.getRotationSettings().getFactor()
                        );
                        case SIZE -> main.getClipboardManager().getClipboard(player.getUniqueId()).scaleUp(settings.getSizeSettings().getFactor());
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
                                    settings.getRotationSettings().getAxis(),
                                    -settings.getRotationSettings().getFactor()
                            );
                            case SIZE -> main.getClipboardManager().getClipboard(player.getUniqueId()).scaleDown(settings.getSizeSettings().getFactor());
                        }
                    }
                }
            }else {
                player.sendMessage(Util.PREFIX + "§cYou have no Item Display selected");
            }
        }
    }

}
