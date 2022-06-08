package mod.charizard1596.galvorite.network.jetpackMessage;

import mod.charizard1596.galvorite.items.custom.jetpackItem;
import mod.charizard1596.galvorite.items.modItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class jetpackMessage {
    boolean fly;
    public jetpackMessage() {
    }
    public jetpackMessage(Boolean fly) {
        this.fly = fly;
    }



    public static void encode(jetpackMessage message, PacketBuffer buffer) {
        buffer.writeBoolean(message.fly);
    }

    public static jetpackMessage decode(PacketBuffer buffer) {
        return new jetpackMessage(buffer.readBoolean());
    }

    public static void handle(jetpackMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity player = context.getSender();
            if (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem()==modItems.JETPACK.get()){
                ((jetpackItem)player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem()).keyDown = message.fly;
            }
        });
        context.setPacketHandled(true);
    }
}
