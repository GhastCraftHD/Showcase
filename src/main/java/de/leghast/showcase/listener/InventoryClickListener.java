package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.display.DisplayWrapper;
import de.leghast.showcase.handler.PositionInteractionHandler;
import de.leghast.showcase.handler.RotationInteractionHandler;
import de.leghast.showcase.handler.SizeInteractionHandler;
import de.leghast.showcase.handler.TransformInteractionHandler;
import de.leghast.showcase.settings.AdjusterSettings;
import de.leghast.showcase.settings.DimensionSettings;
import de.leghast.showcase.settings.FactorSettings;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.ui.Page;
import de.leghast.showcase.ui.UserInterface;
import de.leghast.showcase.ui.AnvilInputHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.Axis;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private final Showcase main;

    public InventoryClickListener(Showcase main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if(!player.hasPermission(Showcase.PERMISSION)) return;
        Component title = e.getView().title();
        if (title.contains(Page.POSITION.getTitle())) {
            new PositionInteractionHandler(main, e.getRawSlot(), player);
            e.setCancelled(true);
        } else if (title.contains(Page.SIZE.getTitle())) {
            new SizeInteractionHandler(main, e.getRawSlot(), player);
            e.setCancelled(true);
        } else if (title.contains(Page.ROTATION.getTitle())) {
            new RotationInteractionHandler(main, e.getRawSlot(), player);
            e.setCancelled(true);
        } else if (title.contains(Page.TRANSFORM.getTitle())) {
            new TransformInteractionHandler(main, e.getRawSlot(), player);
            e.setCancelled(true);
        }
    }

}


