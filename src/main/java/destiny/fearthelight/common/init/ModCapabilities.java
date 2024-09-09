package destiny.fearthelight.common.init;

import destiny.fearthelight.FeartheLight;
import destiny.fearthelight.common.daybreak.DaybreakCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FeartheLight.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModCapabilities
{
    public static final Capability<DaybreakCapability> DAYBREAK = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event)
    {
        event.register(DaybreakCapability.class);
    }
}