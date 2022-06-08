package mod.charizard1596.galvorite.structure;

import mod.charizard1596.galvorite.galvorite;
import mod.charizard1596.galvorite.structure.structures.bunkerStructure;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class modStructures {
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, galvorite.MOD_ID);

    public static void register(IEventBus bus) {
        STRUCTURES.register(bus);
    }
}
