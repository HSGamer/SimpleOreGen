package me.hsgamer.simpleoregen.handler;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class BlockHandler {
    private final Map<String, BlockGenerator> worldGenerators = new HashMap<>();

    public Optional<Material> getMaterial(Player player) {
        World world = player.getWorld();
        return Optional.ofNullable(worldGenerators.get(world.getName())).flatMap(blockGenerator -> blockGenerator.getMaterial(player));
    }

    public void deserialize(Map<String, Map<String, Map<String, Object>>> worldMap) {
        worldMap.forEach((world, map) -> {
            BlockGenerator generator = new BlockGenerator();
            generator.deserialize(map);
            worldGenerators.put(world, generator);
        });
    }

    public Map<String, Map<String, Map<String, Object>>> serialize() {
        Map<String, Map<String, Map<String, Object>>> map = new LinkedHashMap<>();
        worldGenerators.forEach((world, generator) -> map.put(world, generator.serialize()));
        return map;
    }
}
