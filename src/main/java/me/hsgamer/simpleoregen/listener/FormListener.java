package me.hsgamer.simpleoregen.listener;

import me.hsgamer.simpleoregen.SimpleOreGen;
import me.hsgamer.simpleoregen.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

import java.util.Optional;
import java.util.UUID;

public class FormListener implements Listener {
    private final SimpleOreGen instance;

    public FormListener(SimpleOreGen instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onForm(BlockFormEvent event) {
        Block block = event.getBlock();
        if (!block.isLiquid()) {
            return;
        }

        BlockState newState = event.getNewState();
        Material material = newState.getType();
        if (material != Material.STONE && material != Material.COBBLESTONE && material != Material.BASALT) {
            return;
        }

        Optional<UUID> optionalOwner = instance.getBlockStorage().getUUID(block);
        if (optionalOwner.isEmpty()) {
            return;
        }
        UUID owner = optionalOwner.get();

        Player player = Bukkit.getPlayer(owner);
        if (player == null) {
            return;
        }

        switch (material) {
            case STONE:
                MainConfig.STONE_GENERATOR.getValue().getMaterial(player).ifPresent(newState::setType);
                break;
            case COBBLESTONE:
                MainConfig.COBBLESTONE_GENERATOR.getValue().getMaterial(player).ifPresent(newState::setType);
                break;
            case BASALT:
                MainConfig.BASALT_GENERATOR.getValue().getMaterial(player).ifPresent(newState::setType);
                break;
            default:
                break;
        }
    }
}
