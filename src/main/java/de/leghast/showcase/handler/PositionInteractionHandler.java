package de.leghast.showcase.handler;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.settings.AdjusterSettings;
import de.leghast.showcase.settings.DimensionSettings;
import de.leghast.showcase.ui.AnvilInputHelper;
import de.leghast.showcase.ui.Page;
import de.leghast.showcase.ui.UserInterface;
import org.bukkit.Axis;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PositionInteractionHandler {

    public PositionInteractionHandler(Showcase main, int slot, Player player) {
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings positionSettings = settings.getPositionSettings();

        switch (slot) {
            case 11 -> positionSettings.setFactor(0.25);
            case 12 -> positionSettings.setFactor(0.5);
            case 13 -> positionSettings.setFactor(1);
            case 14 -> positionSettings.setFactor(10);
            case 15 -> AnvilInputHelper.setCustomNumberInput(main, player, settings.getPage(), positionSettings.getFactor());
            case 30 -> positionSettings.setAxis(Axis.X);
            case 31 -> positionSettings.setAxis(Axis.Y);
            case 32 -> positionSettings.setAxis(Axis.Z);
            default -> new GeneralInterfaceHandler(main, slot, player);
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }
}
