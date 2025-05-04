package mymyxyz.noctuaAPI.items.utils.Enum;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

import java.util.ArrayList;

public enum EnumRarity implements IRarity {
    NO_RARITY(TextFormatting.WHITE, "NoRarity"),
    COMMON(TextFormatting.GRAY, "common"),
    UNCOMMON(TextFormatting.GREEN, "uncommon"),
    RARE(TextFormatting.BLUE, "rare"),
    EPIC(TextFormatting.LIGHT_PURPLE, "epic"),
    LEGENDARY(TextFormatting.YELLOW, "legendary");
    private static final EnumRarity[] rarityList = new EnumRarity[]{NO_RARITY, COMMON, UNCOMMON, RARE, EPIC, LEGENDARY};
    private final TextFormatting rarityColor;
    private final String rarityName;

    EnumRarity(TextFormatting color, String name) {
        rarityColor = color;
        rarityName = name;
    }

    public static EnumRarity comparator(String rarityName) {
        for (EnumRarity enumRarity : rarityList) {
            if (enumRarity.getName().equals(rarityName)) {
                return enumRarity;
            }
        }
        return NO_RARITY;
    }

    @Override
    public TextFormatting getColor() {
        return rarityColor;
    }

    @Override
    public String getName() {
        return rarityName;
    }

    public static EnumRarity[] getRarityList() {
        return rarityList;
    }

    public static ArrayList<String> getRarityListString() {
        ArrayList<String> str = new ArrayList<>();
        for (EnumRarity enumRarity : rarityList) {
            str.add(enumRarity.getName());
        }
        str.remove(EnumRarity.NO_RARITY.getName());
        return str;
    }
}
