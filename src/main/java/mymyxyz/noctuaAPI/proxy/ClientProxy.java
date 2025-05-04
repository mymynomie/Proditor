package mymyxyz.noctuaAPI.proxy;


import mymyxyz.noctuaAPI.NoctuaAPI;
import mymyxyz.noctuaAPI.network.PaquetJobsOnJoinServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends ServerProxy {

    public ClientProxy() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void register() {
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onEntityJoin(EntityJoinWorldEvent evt) {
        if (evt.getEntity() instanceof EntityPlayer) {
            NoctuaAPI.network.sendToServer(new PaquetJobsOnJoinServer());
        }
    }
}
