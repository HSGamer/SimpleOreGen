package me.hsgamer.simpleoregen;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.simpleoregen.command.ReloadCommand;
import me.hsgamer.simpleoregen.config.MainConfig;
import me.hsgamer.simpleoregen.listener.BlockListener;
import me.hsgamer.simpleoregen.listener.FormListener;
import me.hsgamer.simpleoregen.storage.BlockStorage;

public final class SimpleOreGen extends BasePlugin {
    private final MainConfig mainConfig = new MainConfig(this);
    private final BlockStorage blockStorage = new BlockStorage(this);

    @Override
    public void enable() {
        mainConfig.setup();

        registerListener(new FormListener(this));
        registerListener(new BlockListener(this));

        registerCommand(new ReloadCommand(this));
    }

    public BlockStorage getBlockStorage() {
        return blockStorage;
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }
}
