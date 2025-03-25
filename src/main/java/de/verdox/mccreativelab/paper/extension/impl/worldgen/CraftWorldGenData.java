package de.verdox.mccreativelab.paper.extension.impl.worldgen;

import de.verdox.mccreativelab.paper.extension.api.worldgen.NoiseRouter;
import de.verdox.mccreativelab.paper.extension.api.worldgen.WorldGenData;
import net.minecraft.server.level.ServerLevel;

public class CraftWorldGenData implements WorldGenData {
    private final ServerLevel serverLevel;

    public CraftWorldGenData(ServerLevel serverLevel){
        this.serverLevel = serverLevel;
    }

    @Override
    public NoiseRouter getNoiseRouter() {
        return new CraftNoiseRouter(this.serverLevel.chunkSource.randomState().router());
    }
}
