package net.slimpopo.godsend.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.item.ModItems;

import java.util.List;

@Mod.EventBusSubscriber(modid = GodSend.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if(event.getType() == VillagerProfession.CLERIC){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.MONSTERSOUL.get(), 10);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader,rand) -> new MerchantOffer(
                    stack, new ItemStack(Items.EMERALD,3),20,7,0.01F));
        }
    }
}
