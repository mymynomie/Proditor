package mymyxyz.noctuaAPI.player.classe;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

import java.util.ArrayList;

public enum EnumClasse implements IRarity {
    NO_CLASS(TextFormatting.WHITE, "NoClass"),
    CHEVALIER(TextFormatting.GOLD, "chevalier"),
    DRUID(TextFormatting.LIGHT_PURPLE, "druide"),
    ASSASSIN(TextFormatting.YELLOW, "assassin");
    private static final EnumClasse[] classeList = new EnumClasse[]{NO_CLASS, CHEVALIER, DRUID, ASSASSIN};
    private final TextFormatting itemClassColor;
    private final String itemClassName;

    EnumClasse(TextFormatting color, String name) {
        itemClassColor = color;
        itemClassName = name;
    }

    public static EnumClasse comparator(String itemClassName) {
        for (EnumClasse enumRarity : classeList) {
            if (enumRarity.getName().equals(itemClassName)) {
                return enumRarity;
            }
        }
        return NO_CLASS;
    }

    @Override
    public TextFormatting getColor() {
        return itemClassColor;
    }

    @Override
    public String getName() {
        return itemClassName;
    }

    public static EnumClasse[] getItemClassList() {
        return classeList;
    }

    public static ArrayList<String> getItemClassListString() {
        ArrayList<String> str = new ArrayList<>();
        for (EnumClasse enumClasse : classeList) {
            str.add(enumClasse.getName());
        }
        str.remove(EnumClasse.NO_CLASS.getName());
        return str;
    }
}
