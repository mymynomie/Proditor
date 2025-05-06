package mymyxyz.noctuaAPI.init;

import mymyxyz.noctuaAPI.NoctuaAPI;
import mymyxyz.noctuaAPI.References;
import mymyxyz.noctuaAPI.items.NoctuaItem;
import mymyxyz.noctuaAPI.items.QuestScroll;
import mymyxyz.noctuaAPI.items.StoneOfReminder;
import mymyxyz.noctuaAPI.player.classe.EnumClasse;
import mymyxyz.noctuaAPI.items.utils.Enum.EnumRarity;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = References.MODID)
public class ItemsMod {
    public static Item noctuaItem, item,
    stone_of_reminder, quest_scroll;

    private static final List<Item> itemList = new ArrayList<>();

    public static void init() {
        noctuaItem = addItemList(new NoctuaItem("NoctuaItem", 1, EnumRarity.RARE));
        item = addItemList(new NoctuaItem("item", null, EnumRarity.EPIC, EnumClasse.ASSASSIN));
        stone_of_reminder = addItemList(new StoneOfReminder("stone_of_reminder", 1, 2, EnumRarity.RARE));
        quest_scroll = addItemList(new QuestScroll("quest_scroll", 1, EnumRarity.RARE));

    }

    public static List<Item> getItemList() {
        return itemList;
    }

    private static Item addItemList(Item item) {
        getItemList().add(item);
        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (int i = 0; i < getItemList().size(); i++) {
            event.getRegistry().register(getItemList().get(i));
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (int i = 0; i < getItemList().size(); i++) {
            registerRender(getItemList().get(i));
        }
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }
}