package mod.charizard1596.galvorite.items;

import mod.charizard1596.galvorite.galvorite;
import mod.charizard1596.galvorite.items.custom.*;
import mod.charizard1596.galvorite.items.custom.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class modItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, galvorite.MOD_ID);
    public static final RegistryObject<Item> GALVORITE_INGOT = ITEMS.register("galvorite_ingot",
            () -> new Item(new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> RAW_GALVORITE = ITEMS.register("raw_galvorite",
            () -> new Item(new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_NUGGET = ITEMS.register("galvorite_nugget",
            () -> new Item(new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_COMPONENT = ITEMS.register("galvorite_component",
            () -> new Item(new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_SWORD = ITEMS.register("galvorite_sword",
            () -> new galvoriteSwordItem(modItemTier.GALVORITE, 3, -2.4f, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_AXE = ITEMS.register("galvorite_axe",
            () -> new galvoriteAxeItem(modItemTier.GALVORITE, 5f, -3f, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_PICKAXE = ITEMS.register("galvorite_pickaxe",
            () -> new galvoritePickaxeItem(modItemTier.GALVORITE, 1, -2.8f, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_SHOVEL = ITEMS.register("galvorite_shovel",
            () -> new galvoriteShovelItem(modItemTier.GALVORITE, 1.5f, -3f, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_HOE = ITEMS.register("galvorite_hoe",
            () -> new galvoriteHoeItem(modItemTier.GALVORITE, -4, 0f, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_HELMET = ITEMS.register("galvorite_helmet",
            () -> new ArmorItem(modArmorMaterial.GALVORITE, EquipmentSlotType.HEAD, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_CHESTPLATE = ITEMS.register("galvorite_chestplate",
            () -> new ArmorItem(modArmorMaterial.GALVORITE, EquipmentSlotType.CHEST, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_LEGGINGS = ITEMS.register("galvorite_leggings",
            () -> new ArmorItem(modArmorMaterial.GALVORITE, EquipmentSlotType.LEGS, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> GALVORITE_BOOTS = ITEMS.register("galvorite_boots",
            () -> new ArmorItem(modArmorMaterial.GALVORITE, EquipmentSlotType.FEET, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> SHIMMER_HELM_SPEED = ITEMS.register("shimmer_helm_speed",
            () -> new shimmerHelmItem(modArmorMaterial.SHIMMER, EquipmentSlotType.HEAD, new Item.Properties().group(modItemGroup.galvoriteGroup).maxDamage(269)));
    public static final RegistryObject<Item> SHIMMER_HELM_STRENGTH = ITEMS.register("shimmer_helm_strength",
            () -> new shimmerHelmItem(modArmorMaterial.SHIMMER, EquipmentSlotType.HEAD, new Item.Properties().group(modItemGroup.galvoriteGroup).maxDamage(269)));
    public static final RegistryObject<Item> SHIMMER_HELM_HASTE = ITEMS.register("shimmer_helm_haste",
            () -> new shimmerHelmItem(modArmorMaterial.SHIMMER, EquipmentSlotType.HEAD, new Item.Properties().group(modItemGroup.galvoriteGroup).maxDamage(269)));
    public static final RegistryObject<Item> SHIMMER_HELM_JUMP_BOOST = ITEMS.register("shimmer_helm_jump_boost",
            () -> new shimmerHelmItem(modArmorMaterial.SHIMMER, EquipmentSlotType.HEAD, new Item.Properties().group(modItemGroup.galvoriteGroup).maxDamage(269)));
    public static final RegistryObject<Item> SHIMMER_HELM_RESISTANCE = ITEMS.register("shimmer_helm_resistance",
            () -> new shimmerHelmItem(modArmorMaterial.SHIMMER, EquipmentSlotType.HEAD, new Item.Properties().group(modItemGroup.galvoriteGroup).maxDamage(269)));
    public static final RegistryObject<Item> SHIMMER_HELM_REGENERATION = ITEMS.register("shimmer_helm_regeneration",
            () -> new shimmerHelmItem(modArmorMaterial.SHIMMER, EquipmentSlotType.HEAD, new Item.Properties().group(modItemGroup.galvoriteGroup).maxDamage(269)));
    public static final RegistryObject<Item> SHIMMER_HELM = ITEMS.register("shimmer_helm",
            () -> new shimmerHelmItem(modArmorMaterial.SHIMMER, EquipmentSlotType.HEAD, new Item.Properties().group(modItemGroup.galvoriteGroup).maxDamage(269)));
    //public static final RegistryObject<Item> WRENCH = ITEMS.register("wrench",
    //        () -> new wrenchItem(new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    public static final RegistryObject<Item> JETPACK = ITEMS.register("jetpack",
            () -> new jetpackItem(modArmorMaterial.GALVORITE, EquipmentSlotType.CHEST, new Item.Properties().group(modItemGroup.galvoriteGroup)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
