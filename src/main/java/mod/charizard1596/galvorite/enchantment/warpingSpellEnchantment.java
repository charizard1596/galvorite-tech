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

@Mod.EventBusSubscriber(modid = galvorite.MOD_ID)
public class warpingSpellEnchantment extends Enchantment {

    protected warpingSpellEnchantment(Rarity p_i46731_1_, EnchantmentType p_i46731_2_, EquipmentSlotType[] p_i46731_3_) {
        super(p_i46731_1_, p_i46731_2_, p_i46731_3_);
    }

    @Override
    public void doPostAttack(LivingEntity target, Entity attacker, int level) {
        super.doPostAttack(target, attacker, level);
        if (Math.random() < 0.1) {
            if (attacker instanceof PlayerEntity && target instanceof PlayerEntity) {
                PlayerEntity playerattacker = (PlayerEntity) attacker;
                if (EnchantmentHelper.getItemEnchantmentLevel(modEnchantments.SPELL_WARPING.get(), playerattacker.getItemBySlot(EquipmentSlotType.MAINHAND)) != 0) {
                    for (int i = 0; i < 16; ++i) {
                        double d3 = playerattacker.getX() + (playerattacker.getRandom().nextDouble() - 0.5D) * 16.0D;
                        double d4 = MathHelper.clamp(playerattacker.getY() + (double) (playerattacker.getRandom().nextInt(16) - 8), 0.0D, (double) (attacker.level.getHeight() - 1));
                        double d5 = playerattacker.getZ() + (playerattacker.getRandom().nextDouble() - 0.5D) * 16.0D;
                        if (attacker.isPassenger()) {
                            attacker.stopRiding();
                        }
                        playerattacker.randomTeleport(d3, d4, d5, true);
                    }
                }
            }
        }
    }
    @Override
    public void doPostHurt(LivingEntity target, Entity attacker, int level) {
        super.doPostHurt(target, attacker, level);
        if (Math.random() < 0.1) {
            if (target instanceof PlayerEntity) {
                if (EnchantmentHelper.getItemEnchantmentLevel(modEnchantments.SPELL_WARPING.get(), target.getItemBySlot(EquipmentSlotType.MAINHAND)) != 0) {
                    for (int i = 0; i < 16; ++i) {
                        double d3 = target.getX() + (target.getRandom().nextDouble() - 0.5D) * 16.0D;
                        double d4 = MathHelper.clamp(target.getY() + (double) (target.getRandom().nextInt(16) - 8), 0.0D, (double) (attacker.level.getHeight() - 1));
                        double d5 = target.getZ() + (target.getRandom().nextDouble() - 0.5D) * 16.0D;
                        if (attacker.isPassenger()) {
                            attacker.stopRiding();
                        }
                        target.randomTeleport(d3, d4, d5, true);
                    }
                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
