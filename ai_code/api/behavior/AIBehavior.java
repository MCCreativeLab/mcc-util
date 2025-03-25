package de.verdox.mccreativelab.paper.extension.api.ai.behavior;

import de.verdox.mccreativelab.paper.extension.api.ai.MemoryStatus;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.memory.MemoryKey;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Represents an AI behaviour that is run over more than one tick
 * @param <E> - The entity type
 */
public interface AIBehavior<E extends LivingEntity> extends ControlledBehavior<E> {
    int getMinDuration();

    int getMaxDuration();

    @NotNull Map<MemoryKey<?>, MemoryStatus> getRequiredMemoryStates();
}
