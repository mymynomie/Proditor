package mymyxyz.noctuaAPI.tabs;

import mymyxyz.noctuaAPI.init.ItemsMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class NoctuaTabs extends CreativeTabs {

    public NoctuaTabs(String name) {
        super(name);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemsMod.noctuaItem);
    }
}
