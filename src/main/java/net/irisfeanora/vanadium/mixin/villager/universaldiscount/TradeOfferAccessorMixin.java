package net.irisfeanora.vanadium.mixin.villager.universaldiscount;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;

@Mixin(TradeOffer.class)
public interface TradeOfferAccessorMixin {

    @Accessor("firstBuyItem")
    public void setFirstBuyItem(ItemStack firstBuyItem);
}