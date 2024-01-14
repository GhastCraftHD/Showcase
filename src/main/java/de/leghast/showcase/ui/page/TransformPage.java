package de.leghast.showcase.ui.page;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.instance.DisplayWrapper;
import de.leghast.showcase.ui.Page;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class TransformPage {

    public static ItemStack[] getTransformPage(Showcase main, Player player){
        ItemStack[] content = new ItemStack[45];

        DisplayWrapper wrapper = main.getClipboardManager().getClipboard(player.getUniqueId());

        PageUtil.addPageSwitchItems(content, Page.TRANSFORM);

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
