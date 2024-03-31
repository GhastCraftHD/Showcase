package de.leghast.showcase.ui.page;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.ui.FrequentItems;
import de.leghast.showcase.ui.Page;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PositionPage {

    public static ItemStack[] getPositionPage(Showcase main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getFactor();
        Axis axis = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getAxis();

        FrequentItems.addPageSwitchItems(content, Page.POSITION);

        List<ItemStack> valueItems = FrequentItems.getValueItems(factor);

        int index = 11;
        for (ItemStack item : valueItems) {
            content[index++] = item;
        }

        FrequentItems.addAxisItems(content, axis);

        FrequentItems.addGeneralItems(content, main.getSettingsManager().isEnabled(player.getUniqueId()));

        return content;
    }

}
