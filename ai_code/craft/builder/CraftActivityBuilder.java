package de.verdox.mccreativelab.paper.extension.impl.ai.builder;

import com.mojang.datafixers.util.Pair;
import de.verdox.mccreativelab.paper.extension.api.ai.behavior.AIBehavior;
import de.verdox.mccreativelab.paper.extension.api.ai.behavior.ControlledBehavior;
import de.verdox.mccreativelab.paper.extension.api.ai.behavior.CustomAIBehavior;
import de.verdox.mccreativelab.paper.extension.api.ai.behavior.OneShotBehavior;
import de.verdox.mccreativelab.paper.extension.api.ai.builder.ActivityBuilder;
import de.verdox.mccreativelab.paper.extension.api.ai.builder.BehaviorFactory;
import de.verdox.mccreativelab.paper.extension.impl.ai.CraftEntityBrain;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.schedule.Activity;
import org.bukkit.craftbukkit.entity.memory.CraftMemoryKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.memory.MemoryKey;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class CraftActivityBuilder<E extends LivingEntity> implements ActivityBuilder<E> {
    public final Activity activity;
    public final CraftWeightedBehaviorsBuilder<E> craftWeightedBehaviorsBuilder = new CraftWeightedBehaviorsBuilder<>();
    public final Set<Pair<MemoryModuleType<?>, MemoryStatus>> requiredMemories = new HashSet<>();
    public final Set<MemoryModuleType<?>> forgettingMemories = new HashSet<>();

    public CraftActivityBuilder(EntityActivity activity) {
        this.activity = CraftEntityActivity.bukkitToMinecraft(activity, Registries.ACTIVITY);
    }

    @Override
    public @NotNull CraftActivityBuilder<E> withRequiredMemory(@NotNull MemoryKey<?> requiredMemoryKey, de.verdox.mccreativelab.paper.extension.api.ai.@NotNull MemoryStatus memoryStatus) {
        requiredMemories.add(Pair.of(CraftMemoryKey.bukkitToMinecraft(requiredMemoryKey), CraftEntityBrain.toNMS(memoryStatus)));
        return this;
    }

    @Override
    public @NotNull CraftActivityBuilder<E> withForgettingMemoriesWhenStopped(@NotNull MemoryKey<?> forgettingMemoryKey) {
        forgettingMemories.add(CraftMemoryKey.bukkitToMinecraft(forgettingMemoryKey));
        return this;
    }

    @Override
    public @NotNull ActivityBuilder<E> withBehaviour(int priority, @NotNull ControlledBehavior<? super E> controlledBehavior) {
        craftWeightedBehaviorsBuilder.withBehaviour(priority, controlledBehavior);
        return this;
    }

    @Override
    public @NotNull ActivityBuilder<E> withBehaviour(int priority, @NotNull AIBehavior<? super E> aiBehavior) {
        craftWeightedBehaviorsBuilder.withBehaviour(priority, aiBehavior);
        return this;
    }

    @Override
    public @NotNull ActivityBuilder<E> withBehaviour(int priority, @NotNull CustomAIBehavior<? super E> customAIBehavior) {
        craftWeightedBehaviorsBuilder.withBehaviour(priority, customAIBehavior);
        return this;
    }

    @Override
    public @NotNull ActivityBuilder<E> withBehaviour(int priority, @NotNull OneShotBehavior<? super E> oneShotBehavior) {
        craftWeightedBehaviorsBuilder.withBehaviour(priority, oneShotBehavior);
        return this;
    }

    @Override
    public @NotNull ActivityBuilder<E> withBehaviour(int priority, @NotNull Function<BehaviorFactory, ControlledBehavior<? super E>> behaviourCreator) {
        craftWeightedBehaviorsBuilder.withBehaviour(priority, behaviourCreator);
        return this;
    }

    public void addToBrain(Brain<?> brain, boolean replaceCompleteActivity, boolean replaceActivityRequirements, boolean replaceForgettingMemories) {
        brain.addFromCraftBuilder(this, replaceCompleteActivity, replaceActivityRequirements, replaceForgettingMemories);
    }
}
