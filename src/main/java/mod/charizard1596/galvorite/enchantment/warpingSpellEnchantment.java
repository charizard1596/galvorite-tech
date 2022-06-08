package mod.charizard1596.galvorite.enchantment;

import mod.charizard1596.galvorite.galvorite;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import net.minecraft.enchantment.Enchantment.Rarity;

@Mod.EventBusSubscriber(modid = galvorite.MOD_ID)
public class warpingSpellEnchantment extends Enchantment {

    protected warpingSpellEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }
    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }
    @Override
    public void onEntityDamaged(LivingEntity target, Entity attacker, int level) {
        super.onEntityDamaged(target, attacker, level);
        if (Math.random() < 0.1) {
            if (attacker instanceof PlayerEntity && target instanceof PlayerEntity) {
                if (EnchantmentHelper.getEnchantmentLevel(modEnchantments.SPELL_WARPING.get(), ((PlayerEntity) attacker).getItemStackFromSlot(EquipmentSlotType.MAINHAND)) != 0) {
                    for (int i = 0; i < 16; ++i) {
                        double d3 = target.getPosX() + (target.getRNG().nextDouble() - 0.5D) * 16.0D;
                        double d4 = MathHelper.clamp(target.getPosY() + (double) (target.getRNG().nextInt(16) - 8), 0.0D, (double) (attacker.world.func_234938_ad_() - 1));
                        double d5 = target.getPosZ() + (target.getRNG().nextDouble() - 0.5D) * 16.0D;
                        if (target.isPassenger()) {
                            target.stopRiding();
                        }
                        target.attemptTeleport(d3, d4, d5, true);
                    }
                }
            }
        }
    }
    @Override
    public void onUserHurt(LivingEntity target, Entity attacker, int level) {
        super.onUserHurt(target, attacker, level);
        if (Math.random() < 0.1) {
            if (target instanceof PlayerEntity) {
                //if (EnchantmentHelper.getItemEnchantmentLevel(modEnchantments.SPELL_WARPING.get(), target.getItemBySlot(EquipmentSlotType.CHEST)) != 0) {
                    for (int i = 0; i < 16; ++i) {
                        double d3 = target.getPosX() + (target.getRNG().nextDouble() - 0.5D) * 16.0D;
                        double d4 = MathHelper.clamp(target.getPosY() + (double) (target.getRNG().nextInt(16) - 8), 0.0D, (double) (attacker.world.func_234938_ad_() - 1));
                        double d5 = target.getPosZ() + (target.getRNG().nextDouble() - 0.5D) * 16.0D;
                        if (attacker.isPassenger()) {
                            attacker.stopRiding();
                        }
                        target.attemptTeleport(d3, d4, d5, true);
                    }
                //}
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
