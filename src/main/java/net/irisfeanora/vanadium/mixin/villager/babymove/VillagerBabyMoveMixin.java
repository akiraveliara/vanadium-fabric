package net.irisfeanora.vanadium.mixin.villager.babymove;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerBabyMoveMixin extends AbstractTraderEntity {

    public VillagerBabyMoveMixin(EntityType<? extends AbstractTraderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initialize(Lnet/minecraft/world/IWorld;Lnet/minecraft/world/LocalDifficulty;Lnet/minecraft/entity/SpawnType;Lnet/minecraft/entity/EntityData;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/entity/EntityData;",
            at = @At("HEAD"))
    public void TickInject(IWorld world, LocalDifficulty difficulty, SpawnType spawnType, CallbackInfo ci) {
        if(spawnType == SpawnType.BREEDING) {
            this.move(MovementType.SELF, new Vec3d(0.1, 0, 0.1));
        }
    }
}
