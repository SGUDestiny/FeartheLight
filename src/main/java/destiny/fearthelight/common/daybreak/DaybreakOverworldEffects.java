package destiny.fearthelight.common.daybreak;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class DaybreakOverworldEffects extends DimensionSpecialEffects.OverworldEffects {
    public static final ResourceLocation OVERWORLD_EFFECTS = new ResourceLocation("overworld");

    public DaybreakOverworldEffects(){}

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        if(StellarViewConfig.replace_overworld.get())
            return ViewCenters.renderViewCenterSky(level, ticks, partialTick, poseStack, camera, projectionMatrix, isFoggy, setupFog);

        return false;
    }


}
