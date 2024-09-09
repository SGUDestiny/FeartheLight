package destiny.fearthelight;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = FeartheLight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    public enum DaybreakModes
    {
        CHANCE,
        COUNTDOWN
    }
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.EnumValue<DaybreakModes> DAYBREAK_MODE = BUILDER
            .comment("The mode of Daybreak event")
            .defineEnum("daybreak_Mode", DaybreakModes.CHANCE);

    private static final ForgeConfigSpec.DoubleValue DAYBREAK_STARTING_CHANCE = BUILDER
            .comment("Starting chance of the Daybreak happening")
            .defineInRange("daybreak_starting_chance", 0.1,0, 1);

    private static final ForgeConfigSpec.DoubleValue DAYBREAK_ADDITIVE_CHANCE = BUILDER
            .comment("Number added to chance of Daybreak each passing day")
            .defineInRange("daybreak_additive_chance", 0.1,0, 1);

    private static final ForgeConfigSpec.IntValue DAYBREAK_TIMER = BUILDER
            .comment("Time in ticks until Daybreak begins")
            .defineInRange("daybreak_timer", 24000,0, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static Enum<DaybreakModes> dayBreakMode;
    public static double dayBreakStartingChance;
    public static double dayBreakAdditiveChance;
    public static double dayBreakTimer;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        dayBreakMode = DAYBREAK_MODE.get();
        dayBreakStartingChance = DAYBREAK_STARTING_CHANCE.get();
        dayBreakAdditiveChance = DAYBREAK_ADDITIVE_CHANCE.get();
        dayBreakTimer = DAYBREAK_TIMER.get();
    }
}
