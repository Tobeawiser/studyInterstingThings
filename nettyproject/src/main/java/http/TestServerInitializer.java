package http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //向管道加入处理器

        ChannelPipeline pipeline = ch.pipeline();
        //加入netty提供的httpServerCodec 处理http的编解码器
        pipeline.addLast("myHttpServerCodec", new HttpServerCodec());
        //增加自定义Handler
        pipeline.addLast("myHttpHandler", new TestServerHandler());
    }
}
