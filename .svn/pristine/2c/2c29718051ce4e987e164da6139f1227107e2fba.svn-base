package com.rate.web.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *   服务器端
 *  @ClassName: Server
 *  @Description
 *  @Author  liuYong
 *  @Date  2019/3/12 11:36
 *  @version 1.00 
 */
@Component
public class Server {

    private static Logger logger = LoggerFactory.getLogger(Server.class);

    private static EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private static EventLoopGroup workGroup = new NioEventLoopGroup();
    private static ServerBootstrap serverBootstrap = new ServerBootstrap();

    @Autowired
    private ServerFilter serverFilter;


    public void start(){
        try {
            serverBootstrap.group(bossGroup, workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(serverFilter);
            ChannelFuture f = serverBootstrap.bind(00001).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
