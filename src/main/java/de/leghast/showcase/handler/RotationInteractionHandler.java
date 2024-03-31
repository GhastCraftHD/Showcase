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

public class RotationInteractionHandler {

    public RotationInteractionHandler(Showcase main, int slot, Player player) {

        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings rotationSettings = settings.getRotationSettings();

        switch (slot) {
            case 11 -> rotationSettings.setFactor(22.5);
            case 12 -> rotationSettings.setFactor(45);
            case 13 -> rotationSettings.setFactor(90);
            case 14 -> rotationSettings.setFactor(180);
            case 15 -> AnvilInputHelper.setCustomNumberInput(main, player, settings.getPage(), rotationSettings.getFactor());
            case 30 -> rotationSettings.setAxis(Axis.X);
            case 31 -> rotationSettings.setAxis(Axis.Y);
            case 32 -> rotationSettings.setAxis(Axis.Z);
            default -> new GeneralInterfaceHandler(main, slot, player);
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }

    }
}
