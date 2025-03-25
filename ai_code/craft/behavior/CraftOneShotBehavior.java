package de.verdox.mccreativelab.paper.extension.impl.ai.behavior;

import de.verdox.mccreativelab.paper.extension.api.ai.behavior.OneShotBehavior;
import net.minecraft.world.entity.ai.behavior.OneShot;
import org.bukkit.entity.LivingEntity;

public class CraftOneShotBehavior<E extends LivingEntity> extends CraftControlledBehavior<E, OneShot<?>> implements OneShotBehavior<E> {
    public CraftOneShotBehavior(OneShot<?> handle) {
        super(handle);
    }
}
