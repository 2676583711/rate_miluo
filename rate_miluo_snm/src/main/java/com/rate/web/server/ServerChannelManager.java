package com.rate.web.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  管理连接站点的的Channel
 *  @ClassName: ServerChannelManager
 *  @Description
 *  @Author  liuYong
 *  @Date  2019/3/13 14:30
 *  @version 1.00 
 */
public class ServerChannelManager {

    protected static Map<ChannelId, String> channelHandlerContextMap;

    private ServerChannelManager() {
        channelHandlerContextMap = new ConcurrentHashMap<>();
    }

    public static ServerChannelManager getInstance() {
        return ServerChannelManagerHolder.instance;
    }

    public void addChannel(ChannelId id, String code) {
        channelHandlerContextMap.put(id, code);
    }

    public void removeChannel(ChannelId id) {
        channelHandlerContextMap.remove(id);
    }

    public String getChannel(ChannelId id) {
        return channelHandlerContextMap.get(id);
    }

    private static class ServerChannelManagerHolder {
        private static ServerChannelManager instance = new ServerChannelManager();
    }

    public Map<ChannelId,String > getMap(){
        return channelHandlerContextMap;
    }
}
