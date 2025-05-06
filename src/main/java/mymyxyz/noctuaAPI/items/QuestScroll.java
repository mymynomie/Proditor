package mymyxyz.noctuaAPI.items;

import mymyxyz.noctuaAPI.items.utils.Enum.EnumRarity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;

import java.util.ArrayList;
import java.util.Random;

public class QuestScroll extends NoctuaItem {
    public QuestScroll(String name, Integer size, EnumRarity rarity) {
        super(name, size, rarity);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        IEntity<?> iEntity = NpcAPI.Instance().getIEntity(playerIn);
        if (!worldIn.isRemote && iEntity instanceof IPlayer) {
            IPlayer iPlayer = (IPlayer) iEntity;
            int questID = randomIdQuest();
            for (Integer questId : questList()) {
                if (iPlayer.hasActiveQuest(questId)) {
                    iPlayer.message("vous avez déjà une Quête en cours !");
                    return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                }
            }
            iPlayer.startQuest(questID);
            if (!playerIn.capabilities.isCreativeMode) {
                playerIn.getHeldItem(handIn).shrink(1);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    public int randomIdQuest() {
        int randomIndex = new Random().nextInt(questList().size());
        return questList().get(randomIndex);
    }

    public ArrayList<Integer> questList() {
        ArrayList<Integer> quest = new ArrayList<>();
        quest.add(1);
        quest.add(2);
        return quest;
    }
}
