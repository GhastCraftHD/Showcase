package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.instance.settings.AdjusterSettings;
import de.leghast.showcase.instance.settings.PositionSettings;
import de.leghast.showcase.instance.settings.RotationSettings;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.ui.Page;
import de.leghast.showcase.ui.UserInterface;
import de.leghast.showcase.util.Util;
import org.bukkit.Axis;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private Showcase main;

    public InventoryClickListener(Showcase main){
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();

        if(title.contains(Page.POSITION.getTitle())){
            handlePositionInteractions(e.getRawSlot(), player);
            e.setCancelled(true);
        }else if(title.contains(Page.ROTATION.getTitle())){
            handleRotationInteractions(e.getRawSlot(), player);
            e.setCancelled(true);
        }
    }

    private void handlePositionInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        PositionSettings positionSettings = settings.getPositionSettings();

        switch (slot){
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.getToolMaterial()));
            case 9 -> settings.setPage(Page.ROTATION);
            case 11 -> positionSettings.setFactor(0.25);
            case 12 -> positionSettings.setFactor(0.5);
            case 13 -> positionSettings.setFactor(1);
            case 14 -> positionSettings.setFactor(10);
            case 15 -> Util.setCustomNumberInput(main, player, settings.getPage());
            case 30 -> positionSettings.setAxis(Axis.X);
            case 31 -> positionSettings.setAxis(Axis.Y);
            case 32 -> positionSettings.setAxis(Axis.Z);
        }

        new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
    }

    private void handleRotationInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        RotationSettings rotationSettings = settings.getRotationSettings();

        switch (slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.getToolMaterial()));
            case 11 -> rotationSettings.setFactor(22.5);
            case 12 -> rotationSettings.setFactor(45);
            case 13 -> rotationSettings.setFactor(90);
            case 14 -> rotationSettings.setFactor(180);
            case 15 -> Util.setCustomNumberInput(main, player, settings.getPage());
            case 30 -> rotationSettings.setAxis(Axis.X);
            case 31 -> rotationSettings.setAxis(Axis.Y);
            case 32 -> rotationSettings.setAxis(Axis.Z);
        }

        new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
    }
}


