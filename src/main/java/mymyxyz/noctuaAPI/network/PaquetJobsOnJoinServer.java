package mymyxyz.noctuaAPI.network;

import io.netty.buffer.ByteBuf;
import mymyxyz.noctuaAPI.json.FileUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.IOException;

public class PaquetJobsOnJoinServer implements IMessage {


    public PaquetJobsOnJoinServer() {
    }


    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PaquetJobsOnJoinServer, IMessage> {
        @Override
        public IMessage onMessage(PaquetJobsOnJoinServer message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            try {
                FileUtils.initJson(player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}