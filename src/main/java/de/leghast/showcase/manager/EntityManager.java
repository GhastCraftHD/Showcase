package de.leghast.showcase.manager;

import de.leghast.showcase.Showcase;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class EntityManager {

    private final NamespacedKey entityLinkKey;

    public EntityManager(Showcase main){
        entityLinkKey = new NamespacedKey(main, "showcase-entity-link");
    }

    public ItemDisplay spawnItemDisplay(Location location, Material material){
        location = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
        ItemDisplay display = (ItemDisplay) location.getWorld().spawnEntity(location, EntityType.ITEM_DISPLAY);
        display.setItemStack(new ItemStack(material));
        display.setItemDisplayTransform(ConfigManager.DEFAULT_TRANSFORM);

        spawnInteractionEntity(display);

        return display;
    }

    public Interaction spawnInteractionEntity(ItemDisplay display){
        if(!isLinked(display)){
            Interaction interaction = (Interaction)
                    display.getLocation().getWorld().spawnEntity(
                            display.getLocation().subtract(0, display.getTransformation().getScale().y/2, 0),
                            EntityType.INTERACTION
                    );


            linkEntities(display, interaction);

            return interaction;
        }else{
            Interaction interaction = getInteraction(display);
            interaction.setInteractionWidth(display.getTransformation().getScale().x);
            interaction.setInteractionHeight(display.getTransformation().getScale().y);
            interaction.teleport(display.getLocation().subtract(0, display.getTransformation().getScale().y/2, 0));
            return interaction;
        }
    }

    public void spawnInteractionEntitiesInChunk(Chunk chunk){
        for(Entity entity : chunk.getEntities()){
            if(entity instanceof ItemDisplay display){
                spawnInteractionEntity(display);
            }
        }
    }

    public void linkEntities(ItemDisplay display, Interaction interaction){
        unlinkEntities(display, interaction);
        display.getPersistentDataContainer().set(entityLinkKey, PersistentDataType.STRING, interaction.getUniqueId().toString());
        interaction.getPersistentDataContainer().set(entityLinkKey, PersistentDataType.STRING, display.getUniqueId().toString());
    }

    public void unlinkEntities(ItemDisplay display, Interaction interaction){
        display.getPersistentDataContainer().remove(entityLinkKey);
        interaction.getPersistentDataContainer().remove(entityLinkKey);
    }

    public ItemDisplay getItemDisplay(Interaction interaction){
        return (ItemDisplay) interaction.getWorld().getEntity(
                UUID.fromString(interaction.getPersistentDataContainer().get(entityLinkKey, PersistentDataType.STRING))
        );
    }

    public Interaction getInteraction(ItemDisplay display){
        return (Interaction) display.getWorld().getEntity(
                UUID.fromString(display.getPersistentDataContainer().get(entityLinkKey, PersistentDataType.STRING))
        );
    }

    public boolean isLinked(Entity entity){
        return entity.getPersistentDataContainer().has(entityLinkKey, PersistentDataType.STRING);
    }

    public void removeInteractionEntity(Interaction interaction){
        if(isLinked(interaction)){
            ItemDisplay display = getItemDisplay(interaction);
            if(display != null){
                unlinkEntities(display, interaction);
                display.setGlowing(false);
            }
            interaction.remove();
        }
    }

    public void removeInteractionEntitiesFromChunk(Chunk chunk){
        for(Entity entity : chunk.getEntities()){
            if(entity instanceof Interaction interaction){
                removeInteractionEntity(interaction);
            }
        }
    }

}
