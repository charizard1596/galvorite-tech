package mod.charizard1596.galvorite.tileentity;

import mod.charizard1596.galvorite.blocks.modBlocks;
import mod.charizard1596.galvorite.galvorite;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class modTileEntities {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, galvorite.MOD_ID);
    public static RegistryObject<TileEntityType<wireTile>> WIRE_TILE = TILE_ENTITIES.register("wire_tile",() -> TileEntityType.Builder.of(wireTile::new, modBlocks.WIRE.get()).build(null));
    public static RegistryObject<TileEntityType<energyStorageTile>> ENERGY_STORAGE_TILE = TILE_ENTITIES.register("energy_storage_tile",() -> TileEntityType.Builder.of(energyStorageTile::new, modBlocks.ENERGY_STORAGE.get()).build(null));
    public static void register(IEventBus bus){
        TILE_ENTITIES.register(bus);
    }
}
