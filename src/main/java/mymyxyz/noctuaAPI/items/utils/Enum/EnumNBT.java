package mymyxyz.noctuaAPI.items.utils.Enum;

public enum EnumNBT {
    RARITY("Rarity"),
    CLASS("Class"),
    DURABILITY_CURRENT("Durability_Current"),
    DURABILITY_MAX("Durability_Max")
    ;
    private final String name;

    EnumNBT(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
