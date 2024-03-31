package de.leghast.showcase.ui;


import de.leghast.showcase.constant.Colors;
import net.kyori.adventure.text.Component;

public enum Page {

    POSITION(Component.text("Adjust the displays position", Colors.ACCENT)),
    SIZE(Component.text("Adjust the displays size", Colors.ACCENT)),
    ROTATION(Component.text("Adjust the displays rotation", Colors.ACCENT)),
    TRANSFORM(Component.text("Adjust the displays transform", Colors.ACCENT));

    private final Component title;

    Page(Component title){
        this.title = title;
    }

    public Component getTitle(){
        return title;
    }

}
