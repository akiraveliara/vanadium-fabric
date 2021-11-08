package net.irisfeanora.vanadium.mixin.tools;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public class SpongeMissingToolMixin {
    @Inject(method = "getMiningSpeed", at = @At("HEAD"), cancellable = true)
    private void getCustomMaterial(ItemStack itemStack, BlockState blockState, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(5.0F);
        cir.cancel();
    }
}
