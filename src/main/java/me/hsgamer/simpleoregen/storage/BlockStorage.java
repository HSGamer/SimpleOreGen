package me.hsgamer.simpleoregen.storage;

import me.hsgamer.simpleoregen.SimpleOreGen;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class BlockStorage {
    private final SimpleOreGen instance;

    public BlockStorage(SimpleOreGen simpleOreGen) {
        this.instance = simpleOreGen;
    }

    public void setOwner(UUID uuid, Block block) {
        block.setMetadata("block_owner", new FixedMetadataValue(instance, uuid.toString()));
    }

    public Optional<UUID> getUUID(Block block) {
        List<MetadataValue> metas = block.getMetadata("block_owner");
        for (MetadataValue meta : metas) {
            Plugin plugin = meta.getOwningPlugin();
            if (plugin != null && plugin.getName().equals(instance.getName())) {
                return Optional.of(UUID.fromString(meta.asString()));
            }
        }
        return Optional.empty();
    }
}
