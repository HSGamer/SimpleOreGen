package me.hsgamer.simpleoregen.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.StickyConfigPath;
import me.hsgamer.simpleoregen.handler.BlockHandler;
import org.bukkit.plugin.Plugin;

public class MainConfig extends PathableConfig {
    public static final StickyConfigPath<BlockHandler> COBBLESTONE_GENERATOR = new StickyConfigPath<>(new BlockHandlerConfigPath("cobblestone-generator"));
    public static final StickyConfigPath<BlockHandler> STONE_GENERATOR = new StickyConfigPath<>(new BlockHandlerConfigPath("stone-generator"));
    public static final StickyConfigPath<BlockHandler> BASALT_GENERATOR = new StickyConfigPath<>(new BlockHandlerConfigPath("basalt-generator"));

    public MainConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "config.yml"));
    }
}
