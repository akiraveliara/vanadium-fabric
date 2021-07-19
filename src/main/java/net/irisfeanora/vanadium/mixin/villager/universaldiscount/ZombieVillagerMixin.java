package net.irisfeanora.vanadium.mixin.villager.universaldiscount;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TraderOfferList;

@Mixin(ZombieVillagerEntity.class)
public class ZombieVillagerMixin {

    @Shadow
    public CompoundTag offerData;

    @Inject(
        method = "finishConversion(Lnet/minecraft/server/world/ServerWorld;)V", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/entity/passive/VillagerEntity;setExperience(I)V"
            ),
        locals = LocalCapture.CAPTURE_FAILHARD
        )
    public void universallyReducePrices(ServerWorld world, CallbackInfo ci, VillagerEntity lv)
    {
        if(this.offerData == null) {
            return;
        }

        TraderOfferList offers = lv.getOffers();

        for(int i = 0; i < offers.size(); i++)
        {
            TradeOffer offer = offers.get(i);

            ItemStack itemStack = offer.getOriginalFirstBuyItem();

            if(itemStack.getCount() < 8) {
                itemStack.setCount(1);
            }
            else {
                itemStack.setCount(itemStack.getCount() - 7);
            }

            ((TradeOfferAccessorMixin)offer).setFirstBuyItem(itemStack);

            offers.set(i, offer);
        }
    }
}
