package mod.charizard1596.galvorite.enchantment;

import mod.charizard1596.galvorite.galvorite;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class modEnchantments {
    public static final EnchantmentType WARPING_COMPATIBLE = EnchantmentType.create("warping_compatible", (item)->((item instanceof ArmorItem && ((ArmorItem)item).getSlot() == EquipmentSlotType.CHEST))||item instanceof SwordItem||item instanceof AxeItem);
    public static final EnchantmentType RETURNING_COMPATIBLE = EnchantmentType.create("returning_compatible", (item)->(item instanceof IVanishable || Block.byItem(item) instanceof IVanishable || EnchantmentType.BREAKABLE.canEnchant(item)));
    public static final EquipmentSlotType[] WARPING_SLOTS = {EquipmentSlotType.MAINHAND,EquipmentSlotType.CHEST};
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, galvorite.MOD_ID);
public static RegistryObject<Enchantment> SPELL_RETURNING = ENCHANTMENTS.register("spell_returning", () -> new returningSpellEnchantment(Enchantment.Rarity.VERY_RARE, RETURNING_COMPATIBLE, EquipmentSlotType.values()));
    public static RegistryObject<Enchantment> SPELL_WARPING = ENCHANTMENTS.register("spell_warping", () -> new warpingSpellEnchantment(Enchantment.Rarity.VERY_RARE, WARPING_COMPATIBLE, WARPING_SLOTS));
public static void register(IEventBus eventBus) {
    ENCHANTMENTS.register(eventBus);
}
}
