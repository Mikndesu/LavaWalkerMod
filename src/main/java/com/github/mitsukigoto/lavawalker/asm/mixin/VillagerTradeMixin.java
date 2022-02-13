package com.github.mitsukigoto.lavawalker.asm.mixin;

import com.github.mitsukigoto.lavawalker.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(targets="net.minecraft.entity.merchant.villager.VillagerTrades$EnchantedBookForEmeraldsTrade")
public class VillagerTradeMixin {
    @ModifyVariable(method= "getOffer(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/item/MerchantOffer;", at=@At("STORE"), ordinal=0)
    private List<Enchantment> inject(List<Enchantment> list) {
        list.add(EnchantmentInit.LAVA_WALKER.get());
        return list;
    }
}
