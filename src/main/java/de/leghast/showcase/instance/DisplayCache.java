package de.leghast.showcase.instance;

import org.bukkit.Axis;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.util.Transformation;

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

    public ItemDisplay getDisplay(){
        return display;
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
        Transformation transformation = display.getTransformation();
        switch (axis){
            case X -> transformation.getLeftRotation().rotateX(radiant);
            case Y -> transformation.getLeftRotation().rotateY(radiant);
            case Z -> transformation.getLeftRotation().rotateZ(radiant);
        }
        display.setTransformation(transformation);
        System.out.println("Rotation executed");
    }

    public void scaleUp(double factor){
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(transformation.getScale().x * factor);
        display.setTransformation(transformation);

        interaction.setInteractionWidth(display.getTransformation().getScale().x);
        interaction.setInteractionHeight(display.getTransformation().getScale().y);
    }

    public void scaleDown(double factor){
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(transformation.getScale().x / factor);
        display.setTransformation(transformation);

        interaction.setInteractionWidth(display.getTransformation().getScale().x);
        interaction.setInteractionHeight(display.getTransformation().getScale().y);
    }

}
