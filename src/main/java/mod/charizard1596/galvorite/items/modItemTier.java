package mod.charizard1596.galvorite.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum modItemTier implements IItemTier {
    GALVORITE(5,
            2750,
            11f,
            5f,
            20,

            () -> {return Ingredient.fromItems(modItems.GALVORITE_INGOT.get());});
    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    modItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    @Override
    public int getMaxUses() {
        return uses;
    }

    @Override
    public float getEfficiency() {
        return speed;
    }

    @Override
    public float getAttackDamage() {
        return damage;
    }

    @Override
    public int getHarvestLevel() {
        return level;
    }

    @Override
    public int getEnchantability() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairIngredient.getValue();
    }
}
