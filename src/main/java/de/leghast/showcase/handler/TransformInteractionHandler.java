package de.leghast.showcase.handler;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.display.DisplayWrapper;
import de.leghast.showcase.settings.AdjusterSettings;
import de.leghast.showcase.ui.Page;
import de.leghast.showcase.ui.UserInterface;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;

public class TransformInteractionHandler {

    public TransformInteractionHandler(Showcase main, int slot, Player player) {

        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DisplayWrapper wrapper = main.getClipboardManager().getClipboard(player.getUniqueId());

        switch (slot){
            case 20 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.FIRSTPERSON_RIGHTHAND);
            case 21 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.FIXED);
            case 22 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.GUI);
            case 23 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.HEAD);
            case 24 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.THIRDPERSON_RIGHTHAND);
            default -> new GeneralInterfaceHandler(main, slot, player);
        }
        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }

    }
}
