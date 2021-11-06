package me.hsgamer.simpleoregen.handler;

import com.lewdev.probabilitylib.ProbabilityCollection;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class BlockGenerator {
    private final Map<String, ProbabilityCollection<Material>> groupGenerators = new LinkedHashMap<>();
    private final ProbabilityCollection<Material> defaultGenerator = new ProbabilityCollection<>();

    public void deserialize(Map<String, Map<String, Object>> groupMap) {
        groupMap.forEach((world, map) -> {
            ProbabilityCollection<Material> generator;
            if (world.equalsIgnoreCase("default")) {
                generator = defaultGenerator;
            } else {
                generator = groupGenerators.computeIfAbsent(world, s -> new ProbabilityCollection<>());
            }
            map.forEach((s, obj) -> {
                Material material = Material.matchMaterial(s);
                if (material == null) {
                    return;
                }
                int value;
                try {
                    value = Integer.parseInt(String.valueOf(obj));
                } catch (Exception e) {
                    return;
                }
                generator.add(material, value);
            });
        });
    }

    public Map<String, Map<String, Object>> serialize() {
        Map<String, Map<String, Object>> map = new LinkedHashMap<>();

        Map<String, Object> defaultMap = new LinkedHashMap<>();
        defaultGenerator.iterator().forEachRemaining(e -> defaultMap.put(e.getObject().name(), e.getProbability()));
        map.put("default", defaultMap);

        groupGenerators.forEach((group, generator) -> {
            Map<String, Object> genMap = new LinkedHashMap<>();
            generator.iterator().forEachRemaining(e -> genMap.put(e.getObject().name(), e.getProbability()));
            map.put(group, genMap);
        });

        return map;
    }

    public Optional<Material> getMaterial(Player player) {
        for (Map.Entry<String, ProbabilityCollection<Material>> entry : groupGenerators.entrySet()) {
            String group = entry.getKey();
            ProbabilityCollection<Material> generator = entry.getValue();
            if (generator.isEmpty() || !player.hasPermission("simpleoregen." + group)) {
                continue;
            }
            return Optional.of(generator.get());
        }

        if (defaultGenerator.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(defaultGenerator.get());
    }
}
