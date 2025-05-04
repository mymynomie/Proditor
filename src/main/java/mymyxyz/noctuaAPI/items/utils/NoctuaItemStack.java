package mymyxyz.noctuaAPI.items.utils;

import mymyxyz.noctuaAPI.player.classe.EnumClasse;
import net.minecraft.item.ItemStack;

public class NoctuaItemStack {

    private final ItemStack stack;

    public NoctuaItemStack(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public NoctuaNBT getNbt() {
        return new NoctuaNBT(stack);
    }

    public boolean canUseWithClass(NoctuaItemStack nItemStack, EnumClasse classe) {
        return nItemStack.getNbt().getItemClass().equals(classe);
    }

}
