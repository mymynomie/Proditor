package mymyxyz.noctuaAPI.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static mymyxyz.noctuaAPI.NoctuaAPI.network;

public class PaquetDimensionRequestServer implements IMessage {


    public PaquetDimensionRequestServer() {
    }


    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PaquetDimensionRequestServer, IMessage> {
        @Override
        public IMessage onMessage(PaquetDimensionRequestServer message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            network.sendTo(new PaquetDimensionRequestClient(), player);
            return null;
        }
    }
}