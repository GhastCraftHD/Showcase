package de.leghast.showcase.manager;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.display.DisplayWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClipboardManager {

    private final Map<UUID, DisplayWrapper> clipboard;

    public ClipboardManager(){
        clipboard = new HashMap<>();
    }

    public boolean hasClipboard(UUID uuid){
        return clipboard.containsKey(uuid);
    }

    public DisplayWrapper getClipboard(UUID uuid){
        return clipboard.get(uuid);
    }

    public void updateClipboard(UUID uuid, DisplayWrapper displayCache){
        removeClipboard(uuid);
        clipboard.put(uuid, displayCache);
    }

    public void removeClipboard(UUID uuid){
        if(hasClipboard(uuid)){
            getClipboard(uuid).setGlowing(false);
        }
        clipboard.remove(uuid);
    }
}