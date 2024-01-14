package de.leghast.showcase.ui.page;

import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.ui.Page;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    public static void addGlint(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
    }

    public static void addPageSwitchItems(ItemStack[] content, Page page){
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

        switch(page){
            case POSITION -> PageUtil.addGlint(position);
            case SIZE -> PageUtil.addGlint(size);
            case ROTATION -> PageUtil.addGlint(rotation);
            case TRANSFORM -> PageUtil.addGlint(transform);
        }

    }

}
