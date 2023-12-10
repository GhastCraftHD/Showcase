package de.leghast.showcase.manager;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.instance.DisplayCache;

import java.util.HashMap;
import java.util.UUID;

public class ClipboardManager {

    private Showcase main;
    private HashMap<UUID, DisplayCache> clipboard;

    public ClipboardManager(Showcase main){
        this.main = main;
        clipboard = new HashMap<>();
    }

    public boolean hasClipboard(UUID uuid){
        return clipboard.containsKey(uuid);
    }

    public DisplayCache getClipboard(UUID uuid){
        return clipboard.get(uuid);
    }

    public void updateClipboard(UUID uuid, DisplayCache displayCache){
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