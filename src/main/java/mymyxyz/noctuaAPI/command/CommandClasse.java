package mymyxyz.noctuaAPI.command;

import mymyxyz.noctuaAPI.NoctuaUtils;
import mymyxyz.noctuaAPI.json.FileUtils;
import mymyxyz.noctuaAPI.player.classe.EnumClasse;
import mymyxyz.noctuaAPI.json.ProfileManager;
import mymyxyz.noctuaAPI.player.Profile;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandClasse extends NoctuaCommand {
    public CommandClasse() {
        super("classe", Collections.singletonList("class"), false);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        NoctuaUtils utils = new NoctuaUtils();
        EntityPlayer player = getPlayer(server, sender, args[0]);
        ProfileManager profileManager = new ProfileManager();
        Profile profile = profileManager.loadJson(player.getUniqueID());

        if (args.length == 2 && args[1].equalsIgnoreCase("get")) {
            if (sender instanceof EntityPlayer) {
                utils.msgTranslate(getPlayer(sender), "class.get", "§3" + player.getName(), "§6" + utils.capitalizeFirstLetterOnly(profile.getClasse().isActive().toString()));
            }
        }
        else if (args.length == 3 && args[1].equalsIgnoreCase("set")) {
            if (EnumClasse.getItemClassListString().contains(args[2].toLowerCase())) {
                profile.getClasse().setActive(EnumClasse.comparator(args[2].toLowerCase()));
                FileUtils.save(player.getUniqueID(), profileManager.serialize(profile));
                if (sender instanceof EntityPlayer) {
                    utils.msgTranslate(getPlayer(sender), "class.change.success", "§3" + player.getName(), "§6" + utils.capitalizeFirstLetterOnly(profile.getClasse().isActive().toString()));
                }
            } else {
                if (sender instanceof EntityPlayer) {
                    utils.msgTranslate(getPlayer(sender), "class.error.notExist");
                }
            }
        } else {
            if (sender instanceof EntityPlayer) {
                utils.errorMsg(getPlayer(sender), "/classe <player> get" + "\n>>> /class <player> set <classe>");
            }
        }

    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, "get", "set");
        } else if (args.length == 3 && args[1].equalsIgnoreCase("set")) {
            return getListOfStringsMatchingLastWord(args, EnumClasse.getItemClassListString());
        }
        return Collections.emptyList();
    }
}
