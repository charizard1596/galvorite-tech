package mod.charizard1596.galvorite.enchantment;

import mod.charizard1596.galvorite.galvorite;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Map;

import net.minecraft.enchantment.Enchantment.Rarity;

@Mod.EventBusSubscriber(modid = galvorite.MOD_ID)
public class remainingSpellEnchantment extends Enchantment {

    protected remainingSpellEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
    @SubscribeEvent
    public static void cancelDespawn(ItemExpireEvent event)
    {
        if (EnchantmentHelper.getItemEnchantmentLevel(modEnchantments.SPELL_REMAINING.get(),event.getEntityItem().getItem())!=0){
            event.setExtraLife(6000);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinWorldEvent event) {
        if (!event.getWorld().isClientSide){
            if (event.getEntity() instanceof ItemEntity) {
                if (EnchantmentHelper.getItemEnchantmentLevel(modEnchantments.SPELL_REMAINING.get(),((ItemEntity) event.getEntity()).getItem())!=0) {
                    event.getEntity().setInvulnerable(true);
                }
            }
        }
    }
}
