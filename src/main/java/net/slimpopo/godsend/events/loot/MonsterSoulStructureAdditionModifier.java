package net.slimpopo.godsend.events.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.slimpopo.godsend.GodSend;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

public class MonsterSoulStructureAdditionModifier extends LootModifier {
    private final Item addition;
    private final float chance;
    private static final Logger LOGGER = LogManager.getLogger();

    protected MonsterSoulStructureAdditionModifier(LootItemCondition[] conditionsIn, Item addition, float chance) {
        super(conditionsIn);
        this.addition = addition;
        this.chance = chance;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // generatedLoot is the loot that would be dropped, if we wouldn't add or replace
        // anything!
        if(context.getRandom().nextFloat() > chance) {
            generatedLoot.add(new ItemStack(addition, 1));
        }
        LOGGER.warn("generated loot: " + generatedLoot.toString());
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<MonsterSoulStructureAdditionModifier> {

        @Override
        public MonsterSoulStructureAdditionModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object,"addition")));
            float chance = object.get("chance").getAsFloat();

            LOGGER.error("Addition: " + addition + "\nChance: " + chance);
            return new MonsterSoulStructureAdditionModifier(conditionsIn, addition,chance);
        }

        @Override
        public JsonObject write(MonsterSoulStructureAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey((instance.addition)).toString());
            json.addProperty("chance", instance.chance);

            LOGGER.error("JSON: "+ json);
            return json;
        }
    }
}
