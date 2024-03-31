package de.leghast.showcase.constant;

import cloud.commandframework.execution.preprocessor.CommandPreprocessingContext;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextDecoration;

public class Message {

    public static final Component NOT_ENABLED = Prefix.SHOWCASE.append(
            Component.text("Spawning of items is currently disabled ", Colors.ERROR)
                    .append(Component.text("[ACTIVATE]", Colors.ACCENT)
                            .clickEvent(ClickEvent.runCommand("/showcase enable"))
                            .hoverEvent(HoverEvent.showText(
                                    Component.text("Click to activate the spawning of items", Colors.ACCENT))))
    );

    public static final Component DISABLED = Prefix.SHOWCASE.append(
            Component.text("Disabled", Colors.ACCENT)
                    .append(Component.text(" the spawning of items", Colors.ERROR))
    );

    public static final Component ENABLED = Prefix.SHOWCASE.append(
            Component.text("Enabled", Colors.ACCENT)
                    .append(Component.text(" the spawning of items", Colors.SUCCESS))
    );

    public static final Component NO_CLIPBOARD = Prefix.SHOWCASE.append(
            Component.text("You have no Item Display selected", Colors.ERROR)
    );

    public static final Component INVALID_FACTOR = Prefix.SHOWCASE.append(
            Component.text("Please provide a valid factor", Colors.ERROR)
    );

    public static Component newVersionAvailable(String newVersion){
        return Prefix.SHOWCASE.append(
                Component.text("A new version of Showcase is available: ", Colors.SUCCESS)
                        .append(Component.text("Version " + newVersion, Colors.ACCENT))
                        .append(Component.newline())
                        .append(Prefix.SHOWCASE)
                        .append(Component.text("Get it from ", Colors.SUCCESS))
                        .append(Component.text("Hangar", Colors.ACCENT)
                                .clickEvent(ClickEvent.openUrl("https://hangar.papermc.io/GhastCraftHD/Showcase/versions/" + newVersion))
                                .hoverEvent(HoverEvent.showText(Component.text("Click to open web page", Colors.ACCENT)))
                                .decorate(TextDecoration.UNDERLINED))
        );
    }

    public static Component changedFactor(double factor){
        return Prefix.SHOWCASE.append(
                Component.text("The factor was set to ", Colors.SUCCESS)
                        .append(Component.text(factor + " block" + (factor == 1 ? "" : "s"), Colors.ACCENT))
        );
    }

}
