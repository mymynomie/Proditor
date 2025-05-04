package mymyxyz.noctuaAPI.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CommandGetNBT extends NoctuaCommand {

    public CommandGetNBT() {
        super("nbt", null, false);
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        EntityPlayer player = server.getPlayerList().getPlayerByUUID(Objects.requireNonNull(sender.getCommandSenderEntity()).getUniqueID());
        if (!player.getHeldItemMainhand().equals(new ItemStack(Items.AIR))) {
            if (args.length == 1) {
                if (args[0].equals("get")) {
                    if (player.getHeldItemMainhand().getTagCompound() != null) {
                        player.sendMessage(new TextComponentString(player.getHeldItemMainhand().getTagCompound().toString()));
                    } else {
                        player.sendMessage(new TextComponentString("l'Item n'a pas d'NBT"));
                    }
                }
            } else if (args.length == 3) {
                if (args[0].equals("set")) {
                    if (player.getHeldItemMainhand().getTagCompound() == null) {
                        player.getHeldItemMainhand().setTagCompound(new NBTTagCompound());
                    }
                    player.getHeldItemMainhand().getTagCompound().setString(args[1], args[2]);
                }
            }
        } else {
            player.sendMessage(new TextComponentString("Pas d'Item dans les mains"));
        }
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "get", "set");
        } else if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, "<Key>");
        } else if (args.length == 3) {
            return getListOfStringsMatchingLastWord(args, "<Value>");
        }
        return Collections.emptyList();
    }
}