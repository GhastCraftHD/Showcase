package de.leghast.showcase.ui.page;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.constant.Colors;
import de.leghast.showcase.ui.FrequentItems;
import de.leghast.showcase.ui.InterfaceItem;
import de.leghast.showcase.ui.Page;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RotationPage {

    public static ItemStack[] getRotationPage(Showcase main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getRotationSettings().getFactor();
        Axis axis = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getRotationSettings().getAxis();

        FrequentItems.addPageSwitchItems(content, Page.ROTATION);

        content[11] = new InterfaceItem(
                Material.COAL,
                Component.text("22.5 degrees", NamedTextColor.GRAY),
                () -> factor == 22.5
        );

        content[12] = new InterfaceItem(
                Material.IRON_INGOT,
                Component.text("45 degrees", NamedTextColor.GRAY),
                () -> factor == 45
        );

        content[13] = new InterfaceItem(
                Material.DIAMOND,
                Component.text("90 degrees", NamedTextColor.GRAY),
                () -> factor == 90
        );

        content[14] = new InterfaceItem(
                Material.GRASS_BLOCK,
                Component.text("180 degrees", NamedTextColor.GRAY),
                () -> factor == 180
        );

        content[15] = new InterfaceItem(
                Material.PAPER,
                Component.text("Custom factor ", NamedTextColor.GRAY)
                        .append(Component.text("(" + factor + " degree" + (factor == 1 ? "" : "s") + ")", Colors.ACCENT)),
                () -> factor != 22.5 && factor != 45 && factor != 90 && factor != 180
        );

        FrequentItems.addAxisItems(content, axis);

        FrequentItems.addGeneralItems(content, main.getSettingsManager().isEnabled(player.getUniqueId()));

        return content;
    }

}
