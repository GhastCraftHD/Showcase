package de.leghast.showcase.ui;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.ui.page.PositionPage;
import de.leghast.showcase.ui.page.RotationPage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UserInterface {

    public UserInterface(Showcase main, Player player, Page page){
        Inventory inv = Bukkit.createInventory(null, 45, page.getTitle());
        switch (page){
            case POSITION -> {
                inv.setContents(PositionPage.getPositionPage(main, player));
            }
            case ROTATION -> {
                inv.setContents(RotationPage.getRotationPage(main, player));
            }
        }
        player.openInventory(inv);
    }

}
