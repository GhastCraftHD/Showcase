package de.leghast.showcase.command;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.constant.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ShowcaseCommand implements TabExecutor {

    private final Showcase main;

    public ShowcaseCommand(Showcase main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Showcase.PERMISSION)) return false;

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("enable")){
                main.getSettingsManager().setEnabled(player.getUniqueId(), true);
                player.sendMessage(Message.ENABLED);
            }else if(args[0].equalsIgnoreCase("disable")){
                main.getSettingsManager().setEnabled(player.getUniqueId(), false);
                player.sendMessage(Message.DISABLED);
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> results = new ArrayList<>();

        if(args.length == 1){
            results.add("enable");
            results.add("disable");
            return results.stream().filter(val -> val.startsWith(args[0])).toList();
        }

        return results;
    }
}
