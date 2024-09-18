package destiny.fearthelight.common.advancements;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import destiny.fearthelight.FeartheLight;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;

import java.util.Objects;
import java.util.Optional;

public class DaybreakStartCriterion extends SimpleCriterionTrigger<DaybreakStartCriterion.DaybreakStartTrigger> {
    public static final DaybreakStartCriterion INSTANCE = new DaybreakStartCriterion();
    private static final ResourceLocation CRITERION_ID = new ResourceLocation(FeartheLight.MODID, "daybreak_start");

    @Override
    protected DaybreakStartTrigger createInstance(JsonObject obj, ContextAwarePredicate playerPredicate, DeserializationContext predicateDeserializer)
    {
        Optional<ResourceLocation> triggerDimension = Optional.empty();

        if(GsonHelper.isStringValue(obj, "dimension"))
            triggerDimension = Optional.of(ResourceLocation.tryParse(GsonHelper.getAsString(obj, "dimension")));

        return new DaybreakStartTrigger(playerPredicate, triggerDimension);
    }

    public void trigger(ServerPlayer player, ResourceLocation triggerDimension)
    {
        this.trigger(player, (trigger -> trigger.matches(triggerDimension)));
    }

    @Override
    public ResourceLocation getId()
    {
        return CRITERION_ID;
    }

    public static class DaybreakStartTrigger extends AbstractCriterionTriggerInstance
    {
        private final Optional<ResourceLocation> triggerDimesion;

        public DaybreakStartTrigger(ContextAwarePredicate entity, Optional<ResourceLocation> triggerDimesion)
        {
            super(DaybreakStartCriterion.CRITERION_ID, entity);
            this.triggerDimesion = triggerDimesion;
        }

        public boolean matches(ResourceLocation triggerDimesion)
        {
            if(this.triggerDimesion.isPresent())
            {
                if(!Objects.equals(this.triggerDimesion.get(), triggerDimesion))
                    return false;
            }

            return true;
        }

        public JsonObject serializeToJson(SerializationContext predicateSerializer)
        {
            JsonObject jsonObject = super.serializeToJson(predicateSerializer);

            if(triggerDimesion.isPresent())
                jsonObject.add("triggerDimension", new JsonPrimitive(triggerDimesion.get().toString()));

            return jsonObject;
        }
    }
}
