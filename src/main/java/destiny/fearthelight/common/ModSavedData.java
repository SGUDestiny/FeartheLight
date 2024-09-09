package destiny.fearthelight.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import javax.annotation.Nonnull;

public class ModSavedData extends SavedData {
    private MinecraftServer server;
    public ModSavedData(MinecraftServer server)
    {
        this.server = server;
    }

    public static ModSavedData create(MinecraftServer server)
    {
        return new ModSavedData(server);
    }

    public static ModSavedData load(MinecraftServer server, CompoundTag tag)
    {
        ModSavedData data = create(server);

        data.server = server;
        data.deserialize(tag);

        return data;
    }

    public CompoundTag save(CompoundTag tag)
    {
        tag = serialize();

        return tag;
    }

    @Nonnull
    public static ModSavedData get(Level level)
    {
        if(level.isClientSide())
            throw new RuntimeException("Don't access this client-side!");

        return ModSavedData.get(level.getServer());
    }

    @Nonnull
    public static ModSavedData get(MinecraftServer server)
    {
        DimensionDataStorage storage = server.overworld().getDataStorage();

        return storage.computeIfAbsent((tag) -> load(server, tag), () -> create(server), FILE_NAME);
    }
}
}
