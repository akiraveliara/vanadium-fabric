package net.irisfeanora.vanadium.mixin.entity.zombie;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ZombieEntity.class)
public class ReinforcementFixMixin extends HostileEntity {

    protected ReinforcementFixMixin(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
    }

    @Inject(
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/BlockPos;<init>(III)V"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    public void spawnReinforcementFix(DamageSource damageSource, float amount, LivingEntity lv, int i, int j, int k,
                                      ZombieEntity lv2, int m, int n, int o, BlockPos lv3, CallbackInfo ci) {
        if(!(world.getBlockState(lv3).getBlock().canMobSpawnInside() || world.getBlockState(lv3.up()).getBlock().canMobSpawnInside()
            || world.getBlockState(lv3.up().up()).getBlock().canMobSpawnInside()))
        {
            ci.cancel();
        }
    }
}
