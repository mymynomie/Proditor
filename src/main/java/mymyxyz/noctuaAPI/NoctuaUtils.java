package mymyxyz.noctuaAPI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import javax.swing.text.html.parser.Entity;

public class NoctuaUtils {

    public NoctuaUtils() {
    }

    public String capitalizeFirstLetterOnly(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public void msg(EntityPlayer player, String msg) {
        player.sendMessage(new TextComponentString(msg));
    }

    public void errorMsg(EntityPlayer player, String msg) {
        player.sendMessage(new TextComponentString("\n" + msg).setStyle(new Style().setColor(TextFormatting.RED)));
    }

    public void validMsg(EntityPlayer player, String msg) {
        player.sendMessage(new TextComponentString("\n" + msg).setStyle(new Style().setColor(TextFormatting.GREEN)));
    }

    public void msgTranslate(EntityPlayer player, String id, Object... args) {
        player.sendMessage(new TextComponentTranslation(id, args));
    }

}
