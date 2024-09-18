package destiny.fearthelight.common.advancements;

import destiny.fearthelight.FeartheLight;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;

public class DaybreakStartCriterion {
    public static final PlayerTrigger DAYBREAK_START = CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(FeartheLight.MODID, "daybreak_start")));
}
