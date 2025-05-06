package mymyxyz.noctuaAPI;

import mymyxyz.noctuaAPI.command.CommandClasse;
import mymyxyz.noctuaAPI.command.CommandGetNBT;
import mymyxyz.noctuaAPI.command.CommandItemEdit;
import mymyxyz.noctuaAPI.customNPC.Scripting;
import mymyxyz.noctuaAPI.init.ItemsMod;
import mymyxyz.noctuaAPI.network.PaquetJobsOnJoinServer;
import mymyxyz.noctuaAPI.proxy.ServerProxy;
import mymyxyz.noctuaAPI.tabs.NoctuaTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import noppes.npcs.api.NpcAPI;

import java.io.IOException;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = References.MINECRAFT_VERSION)
public class NoctuaAPI {
    public static final CreativeTabs ItemsTabs = new NoctuaTabs("ItemsTabs");
    @SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.SERVER_PROXY, modId = References.MODID)
    public static ServerProxy proxy;
    public static SimpleNetworkWrapper network;
    @Mod.Instance
    public static NoctuaAPI instance;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel("ChannelNoctuaAPI");
        network.registerMessage(PaquetJobsOnJoinServer.Handler.class, PaquetJobsOnJoinServer.class, 1, Side.SERVER);
        ItemsMod.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.register();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandGetNBT());
        event.registerServerCommand(new CommandItemEdit());
        event.registerServerCommand(new CommandClasse());
    }
}