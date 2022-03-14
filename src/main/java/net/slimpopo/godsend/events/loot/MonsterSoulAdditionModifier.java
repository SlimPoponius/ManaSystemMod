package net.slimpopo.godsend.events.loot;

import com.google.gson.JsonObject;
import com.mojang.realmsclient.util.JsonUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class MonsterSoulAdditionModifier extends LootModifier {
    private final Item addition;

    public MonsterSoulAdditionModifier(LootItemCondition[] conditionsIn, Item addition){
        super(conditionsIn);
        this.addition = addition;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(addition, 1));
        return generatedLoot;
    }


    public static  class Serializer extends GlobalLootModifierSerializer<MonsterSoulAdditionModifier>{

        @Override
        public MonsterSoulAdditionModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object,"addition")));
            return new MonsterSoulAdditionModifier(ailootcondition,addition);
        }

        @Override
        public JsonObject write(MonsterSoulAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey((instance.addition)).toString());
            return json;
        }
    }
}
