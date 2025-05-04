package mymyxyz.noctuaAPI.command;

import mymyxyz.noctuaAPI.NoctuaUtils;
import mymyxyz.noctuaAPI.items.utils.Enum.EnumNBT;
import mymyxyz.noctuaAPI.player.classe.EnumClasse;
import mymyxyz.noctuaAPI.items.utils.Enum.EnumRarity;
import mymyxyz.noctuaAPI.items.utils.NoctuaItemStack;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandItemEdit extends NoctuaCommand {
    public CommandItemEdit() {
        super("item", java.util.Arrays.asList("item-edit", "items"), false);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws NumberInvalidException {
        EntityPlayer player = getPlayer(sender);
        NoctuaItemStack nItemStack = new NoctuaItemStack(player.getHeldItemMainhand());
        NoctuaUtils utils = new NoctuaUtils();
        switch (args.length) {
            case 1:
                if (args[0].equalsIgnoreCase("reset")) {
                    nItemStack.getNbt().clearAllNBT();
                    utils.msgTranslate(player, "command.item.reset.success");
                }
                return;
            case 2:
                switch (args[0].toLowerCase()) {
                    case "rarity":
                        if (EnumRarity.getRarityListString().contains(EnumRarity.comparator(args[1]).getName())) {
                            nItemStack.getNbt().setRarity(EnumRarity.comparator(args[1]));
                            utils.msgTranslate(player, "command.item.rarity.success", nItemStack.getNbt().getRarity().getColor() + nItemStack.getNbt().getRarity().getName());
                        } else {
                            utils.msgTranslate(player, "command.item.rarity.denied", "§4" + args[1]);
                        }
                        return;

                    case "classe":
                        if (EnumClasse.getItemClassListString().contains(EnumClasse.comparator(args[1]).getName())) {
                            nItemStack.getNbt().setItemClass(EnumClasse.comparator(args[1]));
                            utils.msgTranslate(player, "command.item.classe.success", nItemStack.getNbt().getItemClass().getColor() + nItemStack.getNbt().getItemClass().getName());
                        } else {
                            utils.msgTranslate(player, "command.item.classe.denied","§4" + args[1]);
                        }
                        return;

                    case "remove":
                        switch (args[1].toLowerCase()) {
                            case "rarity":
                                if (nItemStack.getNbt().isRarity()) {
                                    nItemStack.getStack().getTagCompound().removeTag(EnumNBT.RARITY.getName());
                                    utils.msgTranslate(player, "command.item.remove.success", "§2" + EnumNBT.RARITY.getName());
                                } else {
                                    utils.msgTranslate(player, "command.item.remove.denied", "§4" + EnumNBT.RARITY.getName());
                                }
                                return;
                            case "classe":
                                if (nItemStack.getNbt().isItemClass()) {
                                    nItemStack.getStack().getTagCompound().removeTag(EnumNBT.CLASS.getName());
                                    utils.msgTranslate(player, "command.item.remove.success", "§2" + EnumNBT.CLASS.getName());
                                } else {
                                    utils.msgTranslate(player, "command.item.remove.denied", "§4" + EnumNBT.CLASS.getName());
                                }
                                return;
                            case "durability":
                                if (nItemStack.getNbt().isDurability()) {
                                    nItemStack.getStack().getTagCompound().removeTag(EnumNBT.DURABILITY_MAX.getName());
                                    nItemStack.getStack().getTagCompound().removeTag(EnumNBT.DURABILITY_CURRENT.getName());
                                    utils.msgTranslate(player, "command.item.remove.success", "§2Durability");
                                } else {
                                    utils.msgTranslate(player, "command.item.remove.denied", "§4Durability");
                                }
                                return;
                        }
                        return;
                }
            case 3:
                switch (args[0]) {
                    case "durability":
                        if (isInt(args[2])) {
                            switch (args[1]) {
                                case "current":
                                    nItemStack.getNbt().setDurability(parseInt(args[2]));
                                    utils.msgTranslate(player, "command.item.durability.current.success", "§2" + nItemStack.getNbt().getDurability());
                                    return;
                                case "max":
                                    nItemStack.getNbt().setMaxDurability(parseInt(args[2]));
                                    utils.msgTranslate(player, "command.item.durability.max.success", "§2" + nItemStack.getNbt().getDurability());
                                    return;
                            }
                        } else {
                            utils.msgTranslate(player, "command.item.durability.value.denied");
                        }
                        return;
                }
        }
        utils.errorMsg(player, "/item classe <classe>\n" +
                "/item rarity <rarity>\n" +
                "/item reset\n" +
                "/item durability <current|max> <Value>"
        );
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        switch (args.length) {
            case 1:
                return getListOfStringsMatchingLastWord(args, "rarity", "classe", "reset", "durability", "remove");
            case 2:
                switch (args[0].toLowerCase()) {
                    case "rarity":
                        return getListOfStringsMatchingLastWord(args, EnumRarity.getRarityListString());
                    case "classe":
                        return getListOfStringsMatchingLastWord(args, EnumClasse.getItemClassListString());
                    case "durability":
                        return getListOfStringsMatchingLastWord(args, "current", "max");
                    case "remove":
                        return getListOfStringsMatchingLastWord(args, "rarity", "classe", "reset", "durability");
                }
                break;
        }
        return Collections.emptyList();
    }
}
