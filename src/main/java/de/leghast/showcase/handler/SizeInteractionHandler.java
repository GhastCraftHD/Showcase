package de.leghast.showcase.handler;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.settings.AdjusterSettings;
import de.leghast.showcase.settings.FactorSettings;
import de.leghast.showcase.ui.AnvilInputHelper;
import de.leghast.showcase.ui.Page;
import de.leghast.showcase.ui.UserInterface;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SizeInteractionHandler {

    public SizeInteractionHandler(Showcase main, int slot, Player player) {

        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        FactorSettings sizeSettings = settings.getSizeSettings();

        switch (slot){
            case 20 -> sizeSettings.setFactor(0.25);
            case 21 -> sizeSettings.setFactor(0.5);
            case 22 -> sizeSettings.setFactor(1);
            case 23 -> sizeSettings.setFactor(5);
            case 24 -> AnvilInputHelper.setCustomNumberInput(main, player, settings.getPage(), sizeSettings.getFactor());
            default -> new GeneralInterfaceHandler(main, slot, player);
        }
        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }

    }
}
