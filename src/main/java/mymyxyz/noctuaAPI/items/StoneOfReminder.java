package mymyxyz.noctuaAPI.items;

import mymyxyz.noctuaAPI.NoctuaUtils;
import mymyxyz.noctuaAPI.items.utils.Enum.EnumNBT;
import mymyxyz.noctuaAPI.items.utils.Enum.EnumRarity;
import mymyxyz.noctuaAPI.items.utils.NoctuaItemStack;
import mymyxyz.noctuaAPI.json.FileUtils;
import mymyxyz.noctuaAPI.json.ProfileManager;
import mymyxyz.noctuaAPI.player.Profile;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class StoneOfReminder extends NoctuaItem {

    public StoneOfReminder(String name, Integer size, int durability, EnumRarity rarity, CreativeTabs creativeTabs) {
        super(name, size, durability, rarity, creativeTabs);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        NoctuaUtils utils = new NoctuaUtils();
        NoctuaItemStack nItemStack = new NoctuaItemStack(playerIn.getHeldItem(handIn));
        if (!worldIn.isRemote) {
            if (worldIn.getSaveHandler().getWorldDirectory().getName().equals("world")) {
                ProfileManager profileManager = new ProfileManager();
                Profile profile = profileManager.loadJson(playerIn.getUniqueID());
                if (playerIn.isSneaking()) {
                    profile.getStone_of_reminder().setX(playerIn.getPosition().getX());
                    profile.getStone_of_reminder().setY(playerIn.getPosition().getY());
                    profile.getStone_of_reminder().setZ(playerIn.getPosition().getZ());
                    FileUtils.save(playerIn.getUniqueID(), profileManager.serialize(profile));
                    utils.validMsg(playerIn, "Votre point de téléportation à étais définie");
                } else if (profile.getStone_of_reminder().y != 0) {
                    if (canUseDurability(nItemStack)) {
                        double x = profile.getStone_of_reminder().x;
                        double z = profile.getStone_of_reminder().z;
                        if (!playerIn.isCreative()) removeDurability(nItemStack, 1);
                        playerIn.setPositionAndUpdate(x + 0.500, profile.getStone_of_reminder().y, z + 0.500);
                    } else {
                        utils.errorMsg(playerIn, "La Pierre n'as plus assez de magie pour être utilisé..");
                    }
                } else {
                    utils.msg(playerIn, "Vous devez d'abord définir votre point de téléportation");
                    return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
                }
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            } else {
                utils.msg(playerIn, "Ceci ne fonctionne pas ici !");
                return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    public static class StoneOfReminderConfig {
        private double x;
        private double y;
        private double z;

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setZ(int z) {
            this.z = z;
        }
    }


}


