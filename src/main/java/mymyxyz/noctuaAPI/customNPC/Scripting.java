package mymyxyz.noctuaAPI.customNPC;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import noppes.npcs.api.event.NpcEvent;

public class Scripting {

    @SubscribeEvent
    public void Collied(NpcEvent.CollideEvent e) {
        e.npc.say("collied\n");
    }



}
