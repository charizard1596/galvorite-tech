package mod.charizard1596.galvorite;

import mod.charizard1596.galvorite.blocks.modBlocks;
import mod.charizard1596.galvorite.container.modContainers;
import mod.charizard1596.galvorite.data.recipes.modRecipeTypes;
import mod.charizard1596.galvorite.enchantment.modEnchantments;
import mod.charizard1596.galvorite.items.modItems;

import mod.charizard1596.galvorite.network.jetpackMessage.jetpackMessage;
import mod.charizard1596.galvorite.network.network;
import mod.charizard1596.galvorite.screen.recyclerScreen;
import mod.charizard1596.galvorite.structure.modStructures;
import mod.charizard1596.galvorite.tileentity.modTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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
        modEnchantments.register(modEventBus);
        modTileEntities.register(modEventBus);
        modContainers.register(modEventBus);
        modRecipeTypes.register(modEventBus);
        modStructures.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(modBlocks.DIAMOND_TROPHY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(modBlocks.NETHERITE_TROPHY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(modBlocks.GALVORITE_TROPHY.get(), RenderType.cutout());
        ScreenManager.register(modContainers.RECYCLER_CONTAINER.get(),
                recyclerScreen::new);
    }
    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        network.init();
        event.enqueueWork(() -> {
            modStructures.setupStructures();
        });
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
    public void onKey(InputEvent.KeyInputEvent event) {
        if (Minecraft.getInstance().player.getItemBySlot(EquipmentSlotType.CHEST).getItem()==modItems.JETPACK.get()){
            network.CHANNEL.sendToServer(new jetpackMessage(Minecraft.getInstance().options.keyJump.isDown()));
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
    public void onLightning(EntityJoinWorldEvent event){
        if (!event.getWorld().isClientSide()) {
            if (event.getEntity() instanceof LightningBoltEntity) {
                System.out.println("Sussi");
                LightningBoltEntity lightningBolt = (LightningBoltEntity) event.getEntity();
                if (event.getWorld().getBlockState(new BlockPos(lightningBolt.getX(), lightningBolt.getY() - 1, lightningBolt.getZ())).getBlock() == Blocks.DIAMOND_BLOCK.getBlock()) {
                    event.getWorld().setBlockAndUpdate(new BlockPos(lightningBolt.getX(), lightningBolt.getY() - 1, lightningBolt.getZ()), modBlocks.GALVORITE_BLOCK.get().defaultBlockState());
                }
            }
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
