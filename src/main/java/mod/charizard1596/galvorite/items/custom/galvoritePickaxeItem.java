package mod.charizard1596.galvorite.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.RegistryObject;

import java.util.UUID;

import net.minecraft.item.Item.Properties;

public class galvoritePickaxeItem extends PickaxeItem {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final UUID ATTACK_REACH_MODIFIER = UUID.fromString("410cc095-30eb-4f69-bab2-e5c8658e00c5");
    public galvoritePickaxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.defaultModifiers=super.getDefaultAttributeModifiers(EquipmentSlotType.MAINHAND);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slotType,ItemStack stack) {
        if (slotType == EquipmentSlotType.MAINHAND) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slotType,stack));
        builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ATTACK_REACH_MODIFIER, "Weapon modifier", 1.0D , AttributeModifier.Operation.ADDITION));
        return builder.build(); } else return super.getAttributeModifiers(slotType,stack);
    }


}
