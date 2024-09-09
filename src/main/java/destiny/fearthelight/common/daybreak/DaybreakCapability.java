package destiny.fearthelight.common.daybreak;

import destiny.fearthelight.Config;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;

public class DaybreakCapability implements INBTSerializable<CompoundTag> {
    public static final String DAYBREAK_MODE = "daybreakMode";
    public static final String DAYBREAK_CHANCE = "daybreakChance";
    public static final String DAYBREAK_TIMER = "daybreakTimer";
    public static final String PREVIOUS_DAY = "previousDay";

    public String daybreakMode = "";
    public double daybreakChance = 0.0;
    public int daybreakTimer = 0;
    public int previousDay = 0;

    public void tick(Level level){
        if(level.isClientSide() || level.getServer() == null){
            return;
        }

        onLoad(level);

        if (Config.dayBreakMode == Config.DaybreakModes.CHANCE){
            daybreakChanceCalc(level);
        } else {
            daybreakCountdown();
        }

    }

    public void onLoad(Level level){
        int previousDay = getPreviousDay();

        if(previousDay == 0){
            setPreviousDay(getCurrentDay(level));
        }

        if (getDaybreakMode() == null) {
            setDaybreakMode(Config.dayBreakMode.toString());
            setDaybreakChance(Config.dayBreakStartingChance);
            setDaybreakTimer(Config.dayBreakTimer);
        }
    }


    public void daybreakChanceCalc(Level level){
        double additive = Config.dayBreakAdditiveChance;
        int currentDay = getCurrentDay(level);
        double chance = getDaybreakChance();

        setDaybreakChance(chance + (additive * (currentDay - 1)));

        //Do RNG for daybreak here soon
    }

    public void daybreakCountdown(){
        int timer = getDaybreakTimer();

        if(timer > 0){
            setDaybreakTimer(timer - 1);
        } else {
            //Trigger Daybreak here
        }
    }

    public void daybreakTrigger(Level level){

    }

    public String getDaybreakMode(){
        return daybreakMode;
    }
    public double getDaybreakChance(){
        return daybreakChance;
    }
    public int getDaybreakTimer(){
        return daybreakTimer;
    }
    public int getPreviousDay(){
        return previousDay;
    }

    public void setDaybreakMode(String daybreakMode){
        this.daybreakMode = daybreakMode;
    }
    public void setDaybreakChance(double daybreakChance){
        this.daybreakChance = daybreakChance;
    }
    public void setDaybreakTimer(int daybreakTimer){
        this.daybreakTimer = daybreakTimer;
    }
    public void setPreviousDay(int previousDay){
        this.previousDay = previousDay;
    }

    public int getCurrentDay(Level level){
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
    public void deserializeNBT(CompoundTag tag){
        this.daybreakMode = tag.getString(DAYBREAK_MODE);
        this.daybreakChance = tag.getDouble(DAYBREAK_CHANCE);
        this.daybreakTimer = tag.getInt(DAYBREAK_TIMER);
    }
}
