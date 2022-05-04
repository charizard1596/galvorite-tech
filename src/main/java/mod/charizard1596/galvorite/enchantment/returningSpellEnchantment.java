package mod.charizard1596.galvorite.enchantment;

import com.google.common.collect.ArrayListMultimap;
import mod.charizard1596.galvorite.galvorite;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.google.common.collect.Multimap;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = galvorite.MOD_ID)
public class returningSpellEnchantment extends Enchantment {
    private static ArrayList<ItemStack> list = new ArrayList<>();

    protected returningSpellEnchantment(Rarity p_i46731_1_, EnchantmentType p_i46731_2_, EquipmentSlotType[] p_i46731_3_) {
        super(p_i46731_1_, p_i46731_2_, p_i46731_3_);
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            for (int i = 0; i < player.inventory.items.size(); i++) {
                if (EnchantmentHelper.getItemEnchantmentLevel(modEnchantments.SPELL_RETURNING.get(), player.inventory.items.get(i)) != 0) {
                    list.add(player.inventory.items.get(i));
                }
            }
            for (int i = 0; i < player.inventory.armor.size(); i++) {
                if (EnchantmentHelper.getItemEnchantmentLevel(modEnchantments.SPELL_RETURNING.get(), player.inventory.armor.get(i)) != 0) {
                    list.add(player.inventory.armor.get(i));
                }
            }
            if (EnchantmentHelper.getItemEnchantmentLevel(modEnchantments.SPELL_RETURNING.get(), player.inventory.offhand.get(0)) != 0) {
                list.add(player.inventory.offhand.get(0));
            }
        }
    }

    @SubscribeEvent
    public static void onDrop(LivingDropsEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            event.getDrops().removeIf((ei) -> EnchantmentHelper.getItemEnchantmentLevel(modEnchantments.SPELL_RETURNING.get(), ei.getItem()) > 0);
        }
    }

    @SubscribeEvent
    public static void Respawn(PlayerEvent.PlayerRespawnEvent event) {
        for (int i = 0; i < list.size(); i++) {
            Map<Enchantment, Integer> enchantmentIntegerMap = EnchantmentHelper.getEnchantments(list.get(i));
            enchantmentIntegerMap.remove(modEnchantments.SPELL_RETURNING.get());
            EnchantmentHelper.setEnchantments(enchantmentIntegerMap,list.get(i));
            event.getPlayer().inventory.add(list.get(i));
            }
        }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
