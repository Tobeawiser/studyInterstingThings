package group.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServer {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务器端启动对象，配置参数
            ServerBootstrap bootStrap = new ServerBootstrap();
            bootStrap.group(bossGroup, workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//NioSocketChannel作为服务器通道实现
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列等待连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //创建通道测试对象  给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //netty 提供 IdleStateHandler
                            //触发在管道 下一个handler的 userEventTiggered
                            ch.pipeline().addLast(new IdleStateHandler(
                                    3, 5, 7, TimeUnit.SECONDS
                            ));
                            ch.pipeline().addLast(new MyServerHandler());
                        }
                    });//给workerGroup的EventLoop对应管道设置处理器
            System.out.println("服务器 is Ready");
            //绑定一个端口并同步
            ChannelFuture channelFuture = bootStrap.bind(7000).sync();
            //注册监听器
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("绑定成功");
                    } else {
                        System.out.println("绑定失败");
                    }
                }
            });

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
