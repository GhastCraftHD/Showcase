package de.leghast.showcase.ui.page;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.manager.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SizePage {

    public static ItemStack[] getSizePage(Showcase main, Player player) {
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getSizeSettings().getFactor();

        ItemStack position = new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA);
        ItemMeta positionMeta = position.getItemMeta();
        positionMeta.setDisplayName("§ePosition");
        List<String> positionLore = new ArrayList<>();
        positionLore.add("§7Adjust the position");
        positionLore.add("§7of the item display");
        positionMeta.setLore(positionLore);
        position.setItemMeta(positionMeta);
        content[0] = position;

        ItemStack size = new ItemStack(Material.PUFFERFISH);
        ItemMeta sizeMeta = size.getItemMeta();
        sizeMeta.setDisplayName("§eSize");
        List<String> sizeLore = new ArrayList<>();
        sizeLore.add("§7Adjust the size");
        sizeLore.add("§7of the item display");
        sizeMeta.setLore(sizeLore);
        size.setItemMeta(sizeMeta);
        PageUtil.addGlint(size);
        content[9] = size;

        ItemStack rotation = new ItemStack(Material.ITEM_FRAME);
        ItemMeta rotationMeta = rotation.getItemMeta();
        rotationMeta.setDisplayName("§eRotation");
        List<String> rotationLore = new ArrayList<>();
        rotationLore.add("§7Adjust the rotation");
        rotationLore.add("§7of the item display");
        rotationMeta.setLore(rotationLore);
        rotation.setItemMeta(rotationMeta);
        content[18] = rotation;

        ItemStack transform = new ItemStack(Material.BAMBOO_HANGING_SIGN);
        ItemMeta transformMeta = transform.getItemMeta();
        transformMeta.setDisplayName("§eTransform");
        List<String> transformLore = new ArrayList<>();
        transformLore.add("§7Set the transform");
        transformLore.add("§7of the item display");
        transformMeta.setLore(transformLore);
        transform.setItemMeta(transformMeta);
        content[27] = transform;

        ItemStack adjuster = new ItemStack(ConfigManager.getToolMaterial());
        ItemMeta adjusterMeta = adjuster.getItemMeta();
        adjusterMeta.setDisplayName("§6Showcase Tool");
        List<String> adjusterLore = new ArrayList<>();
        adjusterLore.add("§7The selected adjusting");
        adjusterLore.add("§7settings are bound to this item");
        adjusterMeta.setLore(adjusterLore);
        adjuster.setItemMeta(adjusterMeta);
        content[8] = adjuster;

        ItemStack quarter = new ItemStack(Material.COAL);
        ItemMeta quarterMeta = quarter.getItemMeta();
        quarterMeta.setDisplayName("§70.25 blocks");
        quarter.setItemMeta(quarterMeta);
        if (factor == 0.25) {
            PageUtil.addGlint(quarter);
        }
        content[20] = quarter;

        ItemStack half = new ItemStack(Material.IRON_INGOT);
        ItemMeta halfMeta = half.getItemMeta();
        halfMeta.setDisplayName("§70.5 blocks");
        half.setItemMeta(halfMeta);
        if (factor == 0.5) {
            PageUtil.addGlint(half);
        }
        content[21] = half;

        ItemStack full = new ItemStack(Material.DIAMOND);
        ItemMeta fullMeta = full.getItemMeta();
        fullMeta.setDisplayName("§71 block");
        full.setItemMeta(fullMeta);
        if (factor == 1) {
            PageUtil.addGlint(full);
        }
        content[22] = full;

        ItemStack five = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta fiveMeta = five.getItemMeta();
        fiveMeta.setDisplayName("§75 blocks");
        five.setItemMeta(fiveMeta);
        if (factor == 5) {
            PageUtil.addGlint(five);
        }
        content[23] = five;

        ItemStack custom = new ItemStack(Material.PAPER);
        ItemMeta customMeta = custom.getItemMeta();
        customMeta.setDisplayName("§7Custom factor §e(" + factor + " block" + (factor == 1 ? "" : "s") + ")");
        custom.setItemMeta(customMeta);
        boolean condition = factor != 0.25 && factor != 0.5 && factor != 1 && factor != 5;
        if (condition) {
            PageUtil.addGlint(custom);
        }
        content[24] = custom;

        ItemStack delete = new ItemStack(Material.BARRIER);
        ItemMeta deleteMeta = delete.getItemMeta();
        deleteMeta.setDisplayName("§cDelete Item Display");
        delete.setItemMeta(deleteMeta);
        content[44] = delete;

        ItemStack deselect = new ItemStack(Material.STRUCTURE_VOID);
        ItemMeta deselectMeta = deselect.getItemMeta();
        deselectMeta.setDisplayName("§cDeselect Item Display");
        deselect.setItemMeta(deselectMeta);
        content[26] = deselect;

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        for (int i = 0; i < content.length; i++) {
            if (content[i] == null) {
                content[i] = filler;
            }
        }

        return content;
    }

}
