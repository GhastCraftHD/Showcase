package de.leghast.showcase.constant;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Prefix {

    public static final Component SHOWCASE = Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("Showcase", Colors.SHOWCASE))
            .append(Component.text("] ", NamedTextColor.GRAY));

}
