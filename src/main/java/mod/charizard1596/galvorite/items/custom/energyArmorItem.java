package mod.charizard1596.galvorite.items.custom;

import mod.charizard1596.galvorite.tileentity.energyTile;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.util.math.BlockPos;

import net.minecraft.item.Item.Properties;

public class energyArmorItem extends ArmorItem {

    public energyArmorItem(IArmorMaterial pMaterial, EquipmentSlotType pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }
    int energy = 6969;

    public void addEnergy(int amount, BlockPos sendPos) {
        this.energy += amount;
    }

    public int getEnergy() {
        return energy;
    }


    public int getMaxEnergy() {
        return 0;
    }

    public void subtractEnergy(int amount) {
        this.energy -= amount;
    }
}
