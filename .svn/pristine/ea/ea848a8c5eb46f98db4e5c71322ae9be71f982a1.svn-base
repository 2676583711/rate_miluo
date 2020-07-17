package com.rate.web.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  
 *  @ClassName: ServerFilter
 *  @Description
 *  @Author  liuYong
 *  @Date  2019/3/12 14:45
 *  @version 1.00 
 */
@Component
public class ServerFilter extends ChannelInitializer<SocketChannel> {


    @Autowired
    private ServerHandle serverHandle;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast("serverHandle", serverHandle);
    }
}
