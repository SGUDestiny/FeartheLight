package destiny.fearthelight.common.advancements;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;

public class DaybreakStartCriterion {
    public static final PlayerTrigger DAYBREAK_START = CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation("daybreak_start")));
}
