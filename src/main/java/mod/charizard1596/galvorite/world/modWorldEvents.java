package mod.charizard1596.galvorite.world;

import mod.charizard1596.galvorite.galvorite;
//import com.charizard1596.galvorite.world.gen.modOreGeneration;
import mod.charizard1596.galvorite.world.gen.modOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = galvorite.MOD_ID)
public class modWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        modOreGeneration.generateOres(event);
    }
}
