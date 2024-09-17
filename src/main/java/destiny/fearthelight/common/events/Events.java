package destiny.fearthelight.common.events;

import destiny.fearthelight.FeartheLight;
import destiny.fearthelight.common.GenericProvider;
import destiny.fearthelight.common.daybreak.DaybreakCapability;
import destiny.fearthelight.common.init.ModCapabilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FeartheLight.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Events {
    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Level> event) {
        if (event.getObject() instanceof ServerLevel) {
            event.addCapability(new ResourceLocation(FeartheLight.MODID, "daybreak"), new GenericProvider<>(ModCapabilities.DAYBREAK, new DaybreakCapability()));
        }
    }
//
//    @SubscribeEvent
//    public static void levelTick(TickEvent.ServerTickEvent event) {
//        if(event.phase == TickEvent.Phase.END && event.side.isServer() && event.getServer() instanceof Level level)
//            event..getCapability(CapabilitiesInit.DIVING).ifPresent(cap -> cap.tick(event.player.level(), player));
//    }
}
