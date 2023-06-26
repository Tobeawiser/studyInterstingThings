package http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws Exception {
        //创建BossGroup WorkerGroup
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端启动对象，配置参数
            ServerBootstrap bootStrap = new ServerBootstrap();
            bootStrap.group(bossGroup, workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//NioSocketChannel作为服务器通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列等待连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//保持活动连接状态
                    .childHandler(new TestServerInitializer());//给workerGroup的EventLoop对应管道设置处理器
            System.out.println("服务器 is Ready");
            //绑定一个端口并同步
            ChannelFuture channelFuture = bootStrap.bind(6668).sync();
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
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
