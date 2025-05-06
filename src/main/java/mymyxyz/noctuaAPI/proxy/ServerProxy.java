package mymyxyz.noctuaAPI.proxy;

import mymyxyz.noctuaAPI.customNPC.Scripting;
import net.minecraftforge.common.MinecraftForge;
import noppes.npcs.api.NpcAPI;

public class ServerProxy {

    public ServerProxy() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void register() {
        MinecraftForge.EVENT_BUS.register(new Scripting());
        NpcAPI.Instance().events().register(new Scripting());
    }

}