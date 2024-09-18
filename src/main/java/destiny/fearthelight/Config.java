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
            .defineEnum("daybreak_mode", DaybreakModes.CHANCE);

    private static final ForgeConfigSpec.DoubleValue DAYBREAK_STARTING_CHANCE = BUILDER
            .comment("Starting chance of the Daybreak happening")
            .defineInRange("daybreak_starting_chance", 0.1,0, 1);

    private static final ForgeConfigSpec.DoubleValue DAYBREAK_ADDITIVE_CHANCE = BUILDER
            .comment("Number added to chance of Daybreak each passing day")
            .defineInRange("daybreak_additive_chance", 0.1,0, 1);

    private static final ForgeConfigSpec.IntValue DAYBREAK_TIMER = BUILDER
            .comment("Time in ticks until Daybreak begins")
            .defineInRange("daybreak_timer", 24000,0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue DAYBREAK_LENGTH_MULTIPLIER = BUILDER
            .comment("Numeric multiplier of how long Daybreak lasts for")
            .defineInRange("daybreak_length_multiplier", 10,1, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static DaybreakModes daybreakMode;
    public static double daybreakStartingChance;
    public static double daybreakAdditiveChance;
    public static int daybreakTimer;
    public static int daybreakLengthMultiplier;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        daybreakMode = DAYBREAK_MODE.get();
        daybreakStartingChance = DAYBREAK_STARTING_CHANCE.get();
        daybreakAdditiveChance = DAYBREAK_ADDITIVE_CHANCE.get();
        daybreakTimer = DAYBREAK_TIMER.get();
        daybreakLengthMultiplier = DAYBREAK_LENGTH_MULTIPLIER.get();
    }
}
