package de.leghast.showcase.handler;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.leghast.showcase.Showcase;
import de.leghast.showcase.constant.Colors;
import de.leghast.showcase.constant.Message;
import de.leghast.showcase.settings.AdjusterSettings;
import de.leghast.showcase.ui.UserInterface;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AdjusterInteractionHandler {

    private static final Cache<UUID, Long> cooldown = CacheBuilder.newBuilder()
            .expireAfterWrite(100, TimeUnit.MILLISECONDS)
            .build();

    public static void handleAdjusterInteraction(Showcase main, Player player, Action action) {

        if(cooldown.asMap().containsKey(player.getUniqueId())) return;

        cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 100);


        if(!main.getClipboardManager().hasClipboard(player.getUniqueId())){
            player.sendMessage(Message.NO_CLIPBOARD);
            return;
        }

        if(action.isLeftClick()){
            AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
            switch (settings.getPage()){
                case POSITION -> main.getClipboardManager().getClipboard(player.getUniqueId()).move(
                        settings.getPositionSettings().getAxis(),
                        settings.getPositionSettings().getFactor()
                );
                case ROTATION -> main.getClipboardManager().getClipboard(player.getUniqueId()).rotate(
                        settings.getRotationSettings().getAxis(),
                        settings.getRotationSettings().getFactor()
                );
                case SIZE -> main.getClipboardManager().getClipboard(player.getUniqueId()).scaleUp(settings.getSizeSettings().getFactor());
            }
        }else if(action.isRightClick()){
            if(player.isSneaking()){
                new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
            }else{
                AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                switch (settings.getPage()){
                    case POSITION -> main.getClipboardManager().getClipboard(player.getUniqueId()).move(
                            settings.getPositionSettings().getAxis(),
                            -settings.getPositionSettings().getFactor()
                    );
                    case ROTATION -> main.getClipboardManager().getClipboard(player.getUniqueId()).rotate(
                            settings.getRotationSettings().getAxis(),
                            -settings.getRotationSettings().getFactor()
                    );
                    case SIZE -> main.getClipboardManager().getClipboard(player.getUniqueId()).scaleDown(settings.getSizeSettings().getFactor());
                }
            }
        }

    }
}
