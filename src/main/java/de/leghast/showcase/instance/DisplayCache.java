package de.leghast.showcase.instance;

import org.bukkit.Axis;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;

public class DisplayCache {

    private ItemDisplay display;
    private Interaction interaction;
    private double size;

    public DisplayCache(ItemDisplay display, Interaction interaction){
        this.display = display;
        this.interaction = interaction;
        size = display.getTransformation().getScale().x;
        display.setGlowing(true);
    }

    public void setGlowing(boolean b){
        display.setGlowing(b);
    }

    public double getSize(){
        return size;
    }

    public void move(Axis axis, double factor){
        switch (axis){
            case X -> {
                display.teleport(display.getLocation().add(factor, 0, 0));
                interaction.teleport(display.getLocation().subtract(0, display.getTransformation().getScale().y/2, 0));
            }
            case Y -> {
                display.teleport(display.getLocation().add(0, factor, 0));
                interaction.teleport(display.getLocation().subtract(0, display.getTransformation().getScale().y/2, 0));
            }
            case Z -> {
                display.teleport(display.getLocation().add(0, 0, factor));
                interaction.teleport(display.getLocation().subtract(0, display.getTransformation().getScale().y/2, 0));
            }
        }
    }

    public void rotate(Axis axis, double factor){
        float radiant = (float) Math.toRadians(factor);
        switch (axis){
            case X -> display.getTransformation().getLeftRotation().rotateX(radiant);
            case Y -> display.getTransformation().getLeftRotation().rotateY(radiant);
            case Z -> display.getTransformation().getLeftRotation().rotateZ(radiant);
        }
    }

}
