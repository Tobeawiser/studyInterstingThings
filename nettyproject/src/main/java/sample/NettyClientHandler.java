package sample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义一个Handler 需要继续netty   规划好的HandlerAdapter
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {


    /**
     * 通道就绪处触该方法
     *
     * @param ctx 上下文对象，含有pipeline,通道channel，地址
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client ctx =" + ctx);

        //ctx.writeAndFlush("hello,servre:(<-->)");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,servre:(<-->)", CharsetUtil.UTF_8));

    }


    /**
     * 读取客户端发送消息
     *
     * @param ctx 上下文对象，含有pipeline,通道channel，地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx =" + ctx);

        ByteBuf buf = (ByteBuf) msg;
        byte[] res = new byte[buf.readableBytes()];
        buf.readBytes(res);
        String s = new String(res);
        System.out.println("接受到msg：" + s);
        System.out.println("服务端地址:" + ctx.channel().remoteAddress());
    }

}
