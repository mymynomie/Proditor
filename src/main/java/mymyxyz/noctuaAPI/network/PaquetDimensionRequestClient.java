package mymyxyz.noctuaAPI.network;

import io.netty.buffer.ByteBuf;
import mymyxyz.noctuaAPI.items.StoneOfReminder;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.StandardCharsets;

public class PaquetDimensionRequestClient implements IMessage {

    private String dimension;

    public PaquetDimensionRequestClient() {
    }

    public PaquetDimensionRequestClient(String dimension) {
        this.dimension = dimension;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        this.dimension = new String(buf.readBytes(length).array(), StandardCharsets.UTF_8);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        byte[] bytes = dimension.getBytes(StandardCharsets.UTF_8);
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    public static class Handler implements IMessageHandler<PaquetDimensionRequestClient, IMessage> {
        @Override
        public IMessage onMessage(PaquetDimensionRequestClient message, MessageContext ctx) {


            return null;
        }
    }
}