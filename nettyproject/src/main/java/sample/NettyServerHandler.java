package sample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义一个Handler 需要继续netty   规划好的HandlerAdapter
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
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
        System.out.println(s);
        System.out.println("客户端地址:" + ctx.channel().remoteAddress());

        //有一个耗时业务操作,让其异步执行
        //提交到channel对应的NIOEventLoop的TaskQueue中
        Thread.sleep(5 * 1000);

        //解决办法1  自定义普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端w我处理完回来了", CharsetUtil.UTF_8));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });

        //解决办法2 用户自定义定时任务
//        ctx.channel().eventLoop().schedule(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5 * 1000);
//                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端w我用定时任务处理完回来了", CharsetUtil.UTF_8));
//                }catch (Exception e){
//                    System.out.println(e.toString());
//                }
//            }
//        }, 5 , TimeUnit.SECONDS);
    }

    /**
     * 数据读取完成
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //两个方法合并  数据写入到缓冲并刷新(写到管道发送)
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端", CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        System.out.println(cause.toString());
    }
}
