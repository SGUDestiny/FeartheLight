package destiny.fearthelight.common.daybreak;

import destiny.fearthelight.Config;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;

public class DaybreakCapability implements INBTSerializable<CompoundTag> {
    public static final String DAYBREAK_MODE = "daybreakMode";
    public static final String DAYBREAK_CHANCE = "daybreakChance";
    public static final String DAYBREAK_TIMER = "daybreakTimer";
    public static final String DAYBREAK_DAYS_LEFT = "daybreakDaysLeft";

    public String daybreakMode = "";
    public double daybreakChance = 0.0;
    public int daybreakTimer = 0;
    public int daybreakDaysLeft = 0;
    public int previousDay = 0;

    public void tick(Level level) {
        if(level.isClientSide() || level.getServer() == null) {
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
        int previousDay = getPreviousDay();

        if(previousDay == 0) {
            setPreviousDay(getCurrentDay(level));
        }

        if (getDaybreakMode() == null) {
            setDaybreakMode(Config.daybreakMode.toString());
            setDaybreakChance(Config.daybreakStartingChance);
            setDaybreakTimer(Config.daybreakTimer);
        }
    }


    public void daybreakChanceCalc(Level level) {
        double additive = Config.daybreakAdditiveChance;
        int currentDay = getCurrentDay(level);
        double chance = getDaybreakChance();

        setDaybreakChance(chance + (additive * (currentDay - 1)));

        if(currentDay > getPreviousDay()) {
            if(getDaybreakChance() > level.random.nextDouble() || getDaybreakChance() >= 1) {
                daybreakTrigger(level);
            }
            setPreviousDay(currentDay);
        }
    }

    public void daybreakCountdownCalc(Level level) {
        int timer = getDaybreakTimer();

        if(timer > 0) {
            setDaybreakTimer(timer - 1);
        } else {
            daybreakTrigger(level);
        }
    }

    public void daybreakTrigger(Level level) {
        int lengthMultiplier = Config.daybreakLengthMultiplier;

        setDaybreakDaysLeft((int) (lengthMultiplier * level.random.nextDouble()));
    }

    public String getDaybreakMode() {
        return daybreakMode;
    }
    public double getDaybreakChance() {
        return daybreakChance;
    }
    public int getDaybreakTimer() {
        return daybreakTimer;
    }
    public int getDaybreakDaysLeft() {
        return daybreakDaysLeft;
    }
    public int getPreviousDay() {
        return previousDay;
    }

    public void setDaybreakMode(String daybreakMode) {
        this.daybreakMode = daybreakMode;
    }
    public void setDaybreakChance(double daybreakChance) {
        this.daybreakChance = daybreakChance;
    }
    public void setDaybreakTimer(int daybreakTimer) {
        this.daybreakTimer = daybreakTimer;
    }
    public void setDaybreakDaysLeft(int daybreakDaysLeft) {
        this.daybreakDaysLeft = daybreakDaysLeft;
    }
    public void setPreviousDay(int previousDay) {
        this.previousDay = previousDay;
    }

    public int getCurrentDay(Level level) {
        return (int) level.getDayTime() / 24000;
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putString(DAYBREAK_MODE, daybreakMode);
        tag.putDouble(DAYBREAK_CHANCE, daybreakChance);
        tag.putInt(DAYBREAK_TIMER, daybreakTimer);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.daybreakMode = tag.getString(DAYBREAK_MODE);
        this.daybreakChance = tag.getDouble(DAYBREAK_CHANCE);
        this.daybreakTimer = tag.getInt(DAYBREAK_TIMER);
    }
}
