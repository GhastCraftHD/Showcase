package de.leghast.showcase.ui.page;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.instance.DisplayWrapper;
import de.leghast.showcase.manager.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TransformPage {

    public static ItemStack[] getTransformPage(Showcase main, Player player){
        ItemStack[] content = new ItemStack[45];

        DisplayWrapper wrapper = main.getClipboardManager().getClipboard(player.getUniqueId());

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
        PageUtil.addGlint(transform);
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

        ItemStack firstPerson = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta firstPersonMeta = firstPerson.getItemMeta();
        firstPersonMeta.setDisplayName("§7First Person");
        firstPerson.setItemMeta(firstPersonMeta);
        if(wrapper.getTransform() == ItemDisplay.ItemDisplayTransform.FIRSTPERSON_RIGHTHAND){
            PageUtil.addGlint(firstPerson);
        }
        content[20] = firstPerson;

        ItemStack fixed = new ItemStack(Material.SHIELD);
        ItemMeta fixedMeta = fixed.getItemMeta();
        fixedMeta.setDisplayName("§7Fixed");
        fixed.setItemMeta(fixedMeta);
        if(wrapper.getTransform() == ItemDisplay.ItemDisplayTransform.FIXED){
            PageUtil.addGlint(fixed);
        }
        content[21] = fixed;

        ItemStack gui = new ItemStack(Material.CHEST);
        ItemMeta guiMeta = gui.getItemMeta();
        guiMeta.setDisplayName("§7GUI");
        gui.setItemMeta(guiMeta);
        if(wrapper.getTransform() == ItemDisplay.ItemDisplayTransform.GUI){
            PageUtil.addGlint(gui);
        }
        content[22] = gui;

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName("§7Head");
        head.setItemMeta(headMeta);
        if(wrapper.getTransform() == ItemDisplay.ItemDisplayTransform.HEAD){
            PageUtil.addGlint(head);
        }
        content[23] = head;

        ItemStack thirdPerson = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta thirdPersonMeta = thirdPerson.getItemMeta();
        thirdPersonMeta.setDisplayName("§7Third Person");
        thirdPerson.setItemMeta(thirdPersonMeta);
        if(wrapper.getTransform() == ItemDisplay.ItemDisplayTransform.THIRDPERSON_RIGHTHAND){
            PageUtil.addGlint(thirdPerson);
        }
        content[24] = thirdPerson;

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

        for(int i = 0; i < content.length; i++){
            if(content[i] == null){
                content[i] = filler;
            }
        }

        return content;
    }

}
