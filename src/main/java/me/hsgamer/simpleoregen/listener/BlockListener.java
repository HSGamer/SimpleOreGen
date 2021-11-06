package me.hsgamer.simpleoregen.listener;

import me.hsgamer.simpleoregen.SimpleOreGen;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {
    private final SimpleOreGen instance;

    public BlockListener(SimpleOreGen instance) {
        this.instance = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onBreak(BlockBreakEvent event) {
        if (!event.isCancelled()) {
            instance.getBlockStorage().setOwner(event.getPlayer().getUniqueId(), event.getBlock());
        }
    }
}
