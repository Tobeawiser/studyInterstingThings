package group.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent evt1 = (IdleStateEvent) evt;
            String enentType = null;
            switch (evt1.state()) {
                case READER_IDLE:
                    enentType = "读空闲";
                    break;
                case WRITER_IDLE:
                    enentType = "写空闲";
                    break;
                case ALL_IDLE:
                    enentType = "读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "--超时事件--" + enentType);

        }
    }
}
