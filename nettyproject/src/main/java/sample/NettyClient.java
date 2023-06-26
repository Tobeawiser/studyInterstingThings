package sample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) throws Exception {
        //客户端需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        //创建启动助手
        Bootstrap bootstrap = new Bootstrap();
        try {
            //设置相关参数
            bootstrap.group(group)//线程组
                    .channel(NioSocketChannel.class)//客户端通道实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());//加入自己的处理器
                        }
                    });
            System.out.println("客户端 ok...");
            //启动客户端连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6688).sync();
            //给关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }


    }
}
