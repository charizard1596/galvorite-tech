package mod.charizard1596.galvorite.network;

import mod.charizard1596.galvorite.galvorite;
import mod.charizard1596.galvorite.network.jetpackMessage.jetpackMessage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class network {
    public static final String NETWORK_VERSION = "0.1.0";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(galvorite.MOD_ID, "network"), () -> NETWORK_VERSION,
            version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

    public static void init() {
        CHANNEL.registerMessage(0, jetpackMessage.class, jetpackMessage::encode, jetpackMessage::decode, jetpackMessage::handle);

    }
}
