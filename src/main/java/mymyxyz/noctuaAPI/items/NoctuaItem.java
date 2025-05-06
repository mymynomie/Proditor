package mymyxyz.noctuaAPI.items;

import mymyxyz.noctuaAPI.NoctuaAPI;
import mymyxyz.noctuaAPI.NoctuaUtils;
import mymyxyz.noctuaAPI.player.classe.EnumClasse;
import mymyxyz.noctuaAPI.items.utils.Enum.EnumRarity;
import mymyxyz.noctuaAPI.items.utils.NoctuaItemStack;
import mymyxyz.noctuaAPI.tabs.NoctuaTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class NoctuaItem extends Item {

    private EnumRarity rarity = null;
    private EnumClasse itemClass = null;
    private int maxDurability = 0;

    public NoctuaItem(String name, Integer size, EnumRarity rarity) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setNoRepair();
        this.rarity = rarity;
        if (size != null) setMaxStackSize(size);
        setCreativeTab(NoctuaAPI.ItemsTabs);
    }

    public NoctuaItem(String name, Integer size, int maxDurability, EnumRarity rarity) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setNoRepair();
        this.rarity = rarity;
        if (maxDurability != 0) this.maxDurability = maxDurability;
        if (size != null) setMaxStackSize(size);
        setCreativeTab(NoctuaAPI.ItemsTabs);
    }

    public NoctuaItem(String name, Integer size, EnumRarity rarity, EnumClasse itemClass) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setNoRepair();
        this.rarity = rarity;
        this.itemClass = itemClass;
        if (size != null) setMaxStackSize(size);
        setCreativeTab(NoctuaAPI.ItemsTabs);
    }

    @Override
    public IRarity getForgeRarity(@NotNull ItemStack stack) {
        NoctuaItemStack nItemStack = new NoctuaItemStack(stack);
        return nItemStack.getNbt().getRarity();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        NoctuaItemStack nItemStack = new NoctuaItemStack(new ItemStack(this));
        if (this.isInCreativeTab(tab)) {
            if (this.rarity != null) {
                nItemStack.getNbt().setRarity(this.rarity);
            }
            if (this.itemClass != null) {
                nItemStack.getNbt().setItemClass(this.itemClass);
            }
            if (this.maxDurability != 0) {
                nItemStack.getNbt().setDurability(maxDurability);
                nItemStack.getNbt().setMaxDurability(maxDurability);
            }
            items.add(nItemStack.getStack());
        }
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NoctuaItemStack nItemStack = new NoctuaItemStack(stack);
        NoctuaUtils utils = new NoctuaUtils();
        tooltip.add("");
        if (nItemStack.getNbt().isRarity()) {
            tooltip.add(new TextComponentTranslation("tooltip.rarity").getFormattedText() + " : " + nItemStack.getNbt().getRarity().getColor() + utils.capitalizeFirstLetterOnly(nItemStack.getNbt().getRarity().getName()));
        }
        if (nItemStack.getNbt().isItemClass()) {
            tooltip.add(new TextComponentTranslation("tooltip.classe").getFormattedText() + " : " + nItemStack.getNbt().getItemClass().getColor() + utils.capitalizeFirstLetterOnly(nItemStack.getNbt().getItemClass().getName()));
        }
        if (nItemStack.getNbt().isDurability()) {
            tooltip.add("");
            tooltip.add(new TextComponentTranslation("tooltip.durability").getFormattedText() + " : " + nItemStack.getNbt().getDurabilityColor() + nItemStack.getNbt().getDurability() + " / " + nItemStack.getNbt().getMaxDurability());
        }
    }

    public void removeDurability(NoctuaItemStack nItemStack, int durability) {
        if (canUseDurability(nItemStack)) {
            nItemStack.getNbt().setDurability(nItemStack.getNbt().getDurability() - durability);
        }
    }

    public boolean canUseDurability(NoctuaItemStack nItemStack) {
        return nItemStack.getNbt().getDurability() > 0;
    }
}
