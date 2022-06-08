package mod.charizard1596.galvorite.items;

import mod.charizard1596.galvorite.galvorite;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum modArmorMaterial implements IArmorMaterial {
    GALVORITE("galvorite", 50, new int[] { 3 , 6 , 8 , 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0F, 0.1F, () -> {return Ingredient.fromItems(modItems.GALVORITE_INGOT.get());}),
    SHIMMER("shimmer", 0, new int[] { 0 , 0 , 0 , 0}, 20, SoundEvents.BLOCK_BEACON_ACTIVATE, 0.0F, 0.0F, () -> {return Ingredient.fromItems(Items.NETHER_STAR);});

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    private modArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.durabilityMultiplier = maxDamageFactor;
        this.slotProtections = damageReductionAmountArray;
        this.enchantmentValue = enchantability;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyValue<>(repairMaterial);
    }
    public int getDurability(EquipmentSlotType slotIn) {
        return HEALTH_PER_SLOT[slotIn.getIndex()] * this.durabilityMultiplier;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.slotProtections[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantmentValue;
    }

    public SoundEvent getSoundEvent() {
        return this.sound;
    }

    public Ingredient getRepairMaterial() {
        return this.repairIngredient.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return galvorite.MOD_ID+":"+this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
