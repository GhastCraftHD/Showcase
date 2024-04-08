package de.leghast.showcase.handler;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.display.DisplayWrapper;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DisplaySpawnHandler {

    public DisplaySpawnHandler(Showcase main, Player player, ItemStack itemStack){
        ItemDisplay display = main.getEntityManager().spawnItemDisplay(
                player.getLocation().add(0, 1, 0),
                itemStack
        );

        main.getClipboardManager().updateClipboard(
                player.getUniqueId(),
                new DisplayWrapper(display, main.getEntityManager().getInteraction(display)));
    }

}
