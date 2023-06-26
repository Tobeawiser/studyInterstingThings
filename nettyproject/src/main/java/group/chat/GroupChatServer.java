package group.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class GroupChatServer {
    private int port;//监听端口

    public GroupChatServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new GroupChatServer(7000).run();
    }

    //处理客户端请求
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap boot = new ServerBootstrap();

        try {
            boot.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //获取到pipeline
                            //解码器
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            //编码器
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            //业务处理handler
                            ch.pipeline().addLast(new GroupChatServeHandler());
                        }
                    });

            System.out.println("netty 服务器启动完成");
            ChannelFuture sync = boot.bind(port).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
