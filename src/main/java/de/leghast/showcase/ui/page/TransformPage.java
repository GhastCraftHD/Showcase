package de.leghast.showcase.ui.page;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.display.DisplayWrapper;
import de.leghast.showcase.ui.FrequentItems;
import de.leghast.showcase.ui.InterfaceItem;
import de.leghast.showcase.ui.Page;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class TransformPage {

    public static ItemStack[] getTransformPage(Showcase main, Player player){
        ItemStack[] content = new ItemStack[45];

        ItemDisplay.ItemDisplayTransform transform = main.getClipboardManager().getClipboard(player.getUniqueId()).getTransform();

        FrequentItems.addPageSwitchItems(content, Page.TRANSFORM);

        content[20] = new InterfaceItem(
                Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                Component.text("First Person", NamedTextColor.GRAY),
                () -> transform == ItemDisplay.ItemDisplayTransform.FIRSTPERSON_RIGHTHAND
        );

        content[21] = new InterfaceItem(
                Material.SHIELD,
                Component.text("Fixed", NamedTextColor.GRAY),
                () -> transform == ItemDisplay.ItemDisplayTransform.FIXED
        );

        content[22] = new InterfaceItem(
                Material.CHEST,
                Component.text("GUI", NamedTextColor.GRAY),
                () -> transform == ItemDisplay.ItemDisplayTransform.GUI
        );

        content[23] = new InterfaceItem(
                Material.PLAYER_HEAD,
                Component.text("Head", NamedTextColor.GRAY),
                () -> transform == ItemDisplay.ItemDisplayTransform.HEAD
        );

        content[24] = new InterfaceItem(
                Material.ORANGE_STAINED_GLASS_PANE,
                Component.text("Third Person", NamedTextColor.GRAY),
                () -> transform == ItemDisplay.ItemDisplayTransform.THIRDPERSON_RIGHTHAND
        );

        FrequentItems.addGeneralItems(content, main.getSettingsManager().isEnabled(player.getUniqueId()));

        return content;
    }

}
