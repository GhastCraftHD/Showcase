package de.leghast.showcase.handler;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.settings.AdjusterSettings;
import de.leghast.showcase.ui.Page;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GeneralInterfaceHandler {

    public GeneralInterfaceHandler(Showcase main, int slot, Player player) {
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.TOOL));
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            case 27 -> settings.setPage(Page.TRANSFORM);
            case 26 -> {
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 35 -> main.getSettingsManager().toggleEnabled(player.getUniqueId());
            case 44 -> {
                main.getClipboardManager().getClipboard(player.getUniqueId()).remove();
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
        }


    }
}
