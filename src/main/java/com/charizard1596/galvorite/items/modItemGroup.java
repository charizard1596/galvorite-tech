package com.charizard1596.galvorite.items;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class modItemGroup {
    public static final ItemGroup galvoriteGroup = new ItemGroup("galvoriteGroup") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(modItems.GALVORITE_INGOT.get());
        }
    };
}
