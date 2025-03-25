package de.verdox.mccreativelab.paper.extension.impl.worldgen;

import de.verdox.mccreativelab.paper.extension.api.worldgen.DensityFunction;

public class CraftDensityFunction implements DensityFunction {
    private final net.minecraft.world.level.levelgen.DensityFunction handle;

    public CraftDensityFunction(net.minecraft.world.level.levelgen.DensityFunction handle) {
        this.handle = handle;
    }

    @Override
    public double compute(int x, int y, int z) {
        return handle.compute(new net.minecraft.world.level.levelgen.DensityFunction.SinglePointContext(x,y,z));
    }
}
