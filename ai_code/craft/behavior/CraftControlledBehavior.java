package de.verdox.mccreativelab.paper.extension.impl.ai.behavior;


import de.verdox.mccreativelab.paper.extension.api.ai.behavior.ControlledBehavior;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import org.bukkit.entity.LivingEntity;

public class CraftControlledBehavior<E extends LivingEntity, T extends BehaviorControl<?>> implements ControlledBehavior<E> {
    private final T handle;
    public CraftControlledBehavior(T handle){
        this.handle = handle;
    }

    public T getHandle() {
        return handle;
    }
}
