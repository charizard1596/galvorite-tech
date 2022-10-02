package mod.charizard1596.galvorite.items;

import mod.charizard1596.galvorite.enchantment.modEnchantments;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

public class modItemGroup {
    public static final ItemGroup galvoriteGroup = (new ItemGroup("galvoriteGroup") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(modItems.GALVORITE_INGOT.get());
        }
    }).setEnchantmentCategories(modEnchantments.WARPING_COMPATIBLE,modEnchantments.RETURNING_COMPATIBLE);
}
