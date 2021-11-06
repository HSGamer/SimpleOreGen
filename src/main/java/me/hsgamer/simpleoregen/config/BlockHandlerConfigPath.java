package me.hsgamer.simpleoregen.config;

import me.hsgamer.hscore.config.AdvancedConfigPath;
import me.hsgamer.hscore.config.Config;
import me.hsgamer.simpleoregen.handler.BlockHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockHandlerConfigPath extends AdvancedConfigPath<Map<String, Map<String, Map<String, Object>>>, BlockHandler> {
    private static final BlockHandler EMPTY_HANDLER = new BlockHandler();

    public BlockHandlerConfigPath(String path) {
        super(path, EMPTY_HANDLER);
    }

    @Override
    public Map<String, Map<String, Map<String, Object>>> getFromConfig(Config config) {
        Map<String, Map<String, Map<String, Object>>> worldMap = new LinkedHashMap<>();
        config.getKeys(getPath(), false).forEach(world -> {
            Map<String, Map<String, Object>> groupMap = new LinkedHashMap<>();
            config.getKeys(String.join(".", getPath(), world), false).forEach(group -> {
                Map<String, Object> genMap = config.getValues(String.join(".", getPath(), world, group), false);
                groupMap.put(group, genMap);
            });
            worldMap.put(world, groupMap);
        });
        return worldMap;
    }

    @Override
    public BlockHandler convert(Map<String, Map<String, Map<String, Object>>> rawValue) {
        BlockHandler handler = new BlockHandler();
        handler.deserialize(rawValue);
        return handler;
    }

    @Override
    public Map<String, Map<String, Map<String, Object>>> convertToRaw(BlockHandler value) {
        return value.serialize();
    }
}
