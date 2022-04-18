package com.charizard1596.galvoritetech.items;

import com.charizard1596.galvoritetech.GalvoriteTech;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class modItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GalvoriteTech.MOD_ID);
    public static final RegistryObject<Item> GALVORITE_INGOT = ITEMS.register("galvorite_ingot",
            () -> new Item(new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> RAW_GALVORITE = ITEMS.register("raw_galvorite",
            () -> new Item(new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_NUGGET = ITEMS.register("galvorite_nugget",
            () -> new Item(new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_COMPONENT = ITEMS.register("galvorite_component",
            () -> new Item(new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_SWORD = ITEMS.register("galvorite_sword",
            () -> new SwordItem(modItemTier.GALVORITE, 3, -2.4f, new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_AXE = ITEMS.register("galvorite_axe",
            () -> new ShovelItem(modItemTier.GALVORITE, 5f, -3f, new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_PICKAXE = ITEMS.register("galvorite_pickaxe",
            () -> new PickaxeItem(modItemTier.GALVORITE, 1, -2.8f, new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_SHOVEL = ITEMS.register("galvorite_shovel",
            () -> new ShovelItem(modItemTier.GALVORITE, 1.5f, -3f, new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_HOE = ITEMS.register("galvorite_hoe",
            () -> new HoeItem(modItemTier.GALVORITE, -4, 0f, new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
