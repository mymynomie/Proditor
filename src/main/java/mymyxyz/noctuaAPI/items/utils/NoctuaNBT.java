package mymyxyz.noctuaAPI.items.utils;

import mymyxyz.noctuaAPI.player.classe.EnumClasse;
import mymyxyz.noctuaAPI.items.utils.Enum.EnumNBT;
import mymyxyz.noctuaAPI.items.utils.Enum.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

import java.util.Objects;

public class NoctuaNBT {

    private final ItemStack stack;

    public NoctuaNBT(ItemStack stack) {
        this.stack = stack;
    }

    public boolean isNbt() {
        return stack.getTagCompound() != null;
    }

    public void clearAllNBT() {
        if (stack.hasTagCompound()) {
            stack.setTagCompound(null);
        }
    }

    public boolean isRarity() {
        return isNbt() && Objects.requireNonNull(stack.getTagCompound()).hasKey(EnumNBT.RARITY.getName());
    }

    public EnumRarity getRarity() {
        if (isNbt() && isRarity()) {
            return EnumRarity.comparator(Objects.requireNonNull(stack.getTagCompound()).getString(EnumNBT.RARITY.getName()));
        }
        return EnumRarity.NO_RARITY;
    }

    public void setRarity(EnumRarity enumRarity) {
        if (!isNbt()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        Objects.requireNonNull(stack.getTagCompound()).setString(EnumNBT.RARITY.getName(), enumRarity.getName());
    }

    public boolean compareRarityOfString(String str) {
        return !EnumRarity.comparator(str).equals(EnumRarity.NO_RARITY);
    }

    public boolean isItemClass() {
        return isNbt() && Objects.requireNonNull(stack.getTagCompound()).hasKey(EnumNBT.CLASS.getName());
    }

    public EnumClasse getItemClass() {
        if (isNbt() && isItemClass()) {
            return EnumClasse.comparator(Objects.requireNonNull(stack.getTagCompound()).getString(EnumNBT.CLASS.getName()));
        }
        return EnumClasse.NO_CLASS;
    }

    public void setItemClass(EnumClasse enumClass) {
        if (!isNbt()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        Objects.requireNonNull(stack.getTagCompound()).setString(EnumNBT.CLASS.getName(), enumClass.getName());
    }

    public boolean compareItemClassOfString(String str) {
        return !EnumRarity.comparator(str).equals(EnumRarity.NO_RARITY);
    }

    public boolean isDurability() {
        return isNbt() && Objects.requireNonNull(stack.getTagCompound()).hasKey(EnumNBT.DURABILITY_CURRENT.getName());
    }

    public boolean isMaxDurability() {
        return isNbt() && Objects.requireNonNull(stack.getTagCompound()).hasKey(EnumNBT.DURABILITY_CURRENT.getName());
    }

    public int getDurability() {
        if (isNbt() && isDurability()) {
            return Objects.requireNonNull(stack.getTagCompound()).getInteger(EnumNBT.DURABILITY_CURRENT.getName());
        }
        return 0;
    }

    public int getMaxDurability() {
        if (isNbt() && isMaxDurability()) {
            return Objects.requireNonNull(stack.getTagCompound()).getInteger(EnumNBT.DURABILITY_MAX.getName());
        }
        return 0;
    }

    public TextFormatting getDurabilityColor() {
        float result = ((float) stack.getTagCompound().getInteger(EnumNBT.DURABILITY_CURRENT.getName()) / (float) getMaxDurability()) * 100;

        if (result == 0) {return TextFormatting.DARK_RED;}
        else if (result <= 24) {return TextFormatting.RED;}
        else if (result <= 49) {return TextFormatting.GOLD;}
        else if (result <= 74) {return TextFormatting.YELLOW;}
        else if (result <= 99) {return TextFormatting.GREEN;}
        else if (result == 100 ) {return TextFormatting.DARK_GREEN;}

        return TextFormatting.DARK_GRAY;
    }

    public void setDurability(int durability) {
        if (!isNbt()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        Objects.requireNonNull(stack.getTagCompound()).setInteger(EnumNBT.DURABILITY_CURRENT.getName(), durability);
    }

    public void setMaxDurability(int maxDurability) {
        if (!isNbt()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        Objects.requireNonNull(stack.getTagCompound()).setInteger(EnumNBT.DURABILITY_MAX.getName(), maxDurability);
    }
}
