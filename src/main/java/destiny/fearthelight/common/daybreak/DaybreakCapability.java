package destiny.fearthelight.common.daybreak;

import destiny.fearthelight.Config;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.WorldDimensions;
import net.minecraftforge.common.util.INBTSerializable;

public class DaybreakCapability implements INBTSerializable<CompoundTag> {
    public static final String DAYBREAK_CHANCE = "daybreakChance";
    public static final String DAYBREAK_TIMER = "daybreakTimer";
    public static final String DAYBREAK_DAYS_LEFT = "daybreakDaysLeft";
    public static final String IS_DAY_BROKEN = "isDayBroken";

    public double daybreakChance = 0.0;
    public int daybreakTimer = 0;
    public int daybreakDaysLeft = 0;
    public int previousDay = -1;
    public boolean isDayBroken = false;

    public void tick(Level level) {
        if(level.isClientSide() || level.getServer() == null || !level.dimensionTypeId().location().equals(new ResourceLocation("overworld"))) {
            return;
        }

        onLoad(level);

        if (Config.daybreakMode == Config.DaybreakModes.CHANCE) {
            daybreakChanceCalc(level);
        } else {
            daybreakCountdownCalc(level);
        }
    }

    public void onLoad(Level level) {
        if (previousDay == -1) {
            previousDay = getCurrentDay(level);
        }

        if (daybreakTimer == 0) {
            daybreakTimer = Config.daybreakTimer;
        }
    }


    public void daybreakChanceCalc(Level level) {
        double additive = Config.daybreakAdditiveChance;
        int currentDay = getCurrentDay(level);
        double chance = Config.daybreakStartingChance;

        System.out.println("=================");
        System.out.println("Next tick");
        System.out.println("=================");
        System.out.println("Chance: " + daybreakChance);
        System.out.println("Timer: " + daybreakTimer);
        System.out.println("-----------");
        System.out.println("Current day: " + currentDay);
        System.out.println("Previous day: " + previousDay);
        System.out.println("Days left: " + daybreakDaysLeft);

        daybreakChance = chance + (additive * currentDay);

        if(currentDay > previousDay) {
            System.out.println("!!! Random chance rolled !!!");
            if(daybreakChance > level.random.nextDouble() || daybreakChance >= 1) {
                daybreakTrigger(level);
            }
            previousDay = currentDay;
        }
    }

    public void daybreakCountdownCalc(Level level) {
        int timer = Config.daybreakTimer;

        if(timer > 0) {
            daybreakTimer = timer - 1;
        } else {
            daybreakTrigger(level);
        }
    }

    public void daybreakTrigger(Level level) {
        int lengthMultiplier = Config.daybreakLengthMultiplier;

        daybreakDaysLeft = (int) (lengthMultiplier * level.random.nextDouble());
        isDayBroken = true;
    }

    public int getCurrentDay(Level level) {
        return (int) (level.getDayTime() / 24000 % 2147483647L);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putDouble(DAYBREAK_CHANCE, daybreakChance);
        tag.putInt(DAYBREAK_TIMER, daybreakTimer);
        tag.putInt(DAYBREAK_DAYS_LEFT, daybreakDaysLeft);
        tag.putBoolean(IS_DAY_BROKEN, isDayBroken);

        System.out.println("-------------");
        System.out.println("Serialization");
        System.out.println("-------------");
        System.out.println("Chance: " + daybreakChance);
        System.out.println("Timer: " + daybreakTimer);
        System.out.println("Days left:" + daybreakDaysLeft);
        System.out.println("Daybreak: " + isDayBroken);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.daybreakChance = tag.getDouble(DAYBREAK_CHANCE);
        this.daybreakTimer = tag.getInt(DAYBREAK_TIMER);
        this.daybreakDaysLeft = tag.getInt(DAYBREAK_DAYS_LEFT);
        this.isDayBroken = tag.getBoolean(IS_DAY_BROKEN);
    }
}
