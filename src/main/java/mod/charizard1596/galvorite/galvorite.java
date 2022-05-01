package mod.charizard1596.galvorite;

import mod.charizard1596.galvorite.blocks.modBlocks;
import mod.charizard1596.galvorite.items.modItems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(galvorite.MOD_ID)
public class galvorite
{
    public static final String MOD_ID = "galvorite";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public galvorite() {
        // Register the setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        modEventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        modEventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        modEventBus.addListener(this::doClientStuff);
        modItems.register(modEventBus);
        modBlocks.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(modBlocks.DIAMOND_TROPHY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(modBlocks.NETHERITE_TROPHY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(modBlocks.GALVORITE_TROPHY.get(), RenderType.cutout());
    }
    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }


    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("galvorite", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }

    @SubscribeEvent
    public void galvoriteSwordKill (LivingDeathEvent event) {
        Entity killer = event.getSource().getEntity();
        Entity dead = event.getEntity();
        World world = event.getEntityLiving().getEntity().level;
        if (!(dead instanceof PlayerEntity) && killer instanceof PlayerEntity && ((PlayerEntity) killer).getItemInHand(event.getEntityLiving().getUsedItemHand()).getItem() == modItems.GALVORITE_SWORD.get()) {
            ExperienceOrbEntity xp = new ExperienceOrbEntity(world,killer.getX(),killer.getY(),killer.getZ(),1);
            world.addFreshEntity(xp);
        }
    }
    @SubscribeEvent
    public void onCrit(CriticalHitEvent event) {
        if (event.getPlayer().getItemBySlot(EquipmentSlotType.MAINHAND).getItem() == modItems.GALVORITE_AXE.get())
        {
            if (event.isVanillaCritical()) {
                if (Math.random()<0.25) {
                    LivingEntity livingEntity = (LivingEntity) event.getTarget();
                    if (livingEntity != null) {
                        livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,200,0));
                    }
                }
                event.setResult(Event.Result.ALLOW);
            } else event.setResult(Event.Result.DENY);
        } else event.setResult(Event.Result.DEFAULT);
    }
}
