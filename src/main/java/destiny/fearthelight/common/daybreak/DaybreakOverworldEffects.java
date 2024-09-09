package destiny.fearthelight.common.daybreak;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class DaybreakOverworldEffects extends DimensionSpecialEffects {
    public static final ResourceLocation OVERWORLD_EFFECTS = new ResourceLocation("overworld");

    public DaybreakOverworldEffects(float p_108866_, boolean p_108867_, SkyType p_108868_, boolean p_108869_, boolean p_108870_) {
        super(p_108866_, p_108867_, p_108868_, p_108869_, p_108870_);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight) {
        return null;
    }

    @Override
    public boolean isFoggyAt(int x, int y) {
        return false;
    }


}
