package net.irisfeanora.vanadium.mixin.villager.oldrestock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;

@Mixin(VillagerEntity.class)
public abstract class OldRestockMechanicMixin extends AbstractTraderEntity {

    public OldRestockMechanicMixin(EntityType<? extends AbstractTraderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract void method_21724();

    @Shadow
    public abstract VillagerData getVillagerData();

    @Shadow
    public abstract void craftBread();

    @Shadow
    public long lastRestockTime;
    
    @Overwrite
    public void restock() {
        this.method_21724();

        for(TradeOffer lv : this.getOffers()) {
            lv.resetUses();
         }
   
         if (this.getVillagerData().getProfession() == VillagerProfession.FARMER) {
            this.craftBread();
         }
   
         this.lastRestockTime = this.world.getTime();
    }
}
