package mymyxyz.noctuaAPI.items.utils.Enum;

import net.minecraft.item.Item;

public class EnumItem {
    private final Item item;

    public EnumItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
