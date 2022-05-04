package mod.charizard1596.galvorite.enchantment;

import mod.charizard1596.galvorite.galvorite;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class modEnchantments {
public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, galvorite.MOD_ID);
public static RegistryObject<Enchantment> SPELL_RETURNING = ENCHANTMENTS.register("spell_returning", () -> new returningSpellEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.VANISHABLE, EquipmentSlotType.values()));
public static void register(IEventBus eventBus) {
    ENCHANTMENTS.register(eventBus);
}
}
