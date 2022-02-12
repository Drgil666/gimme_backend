package com.project.gimme.nettyServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

/**
 * @author DrGilbert
 * @date 2022/2/11 17:15
 */
@Slf4j
@Service
public class NettyServer {
    @Value("${netty.port}")
    private Integer port;

    /**
     * SpringBoot 启动的时候 调用
     */
    @PostConstruct
    public void init() throws InterruptedException {
        //创建一个线程池
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        //设置线程池
        bootstrap.group(bossGroup)
                // 指定Channel为非阻塞IO,用于聊天
                .channel(NioDatagramChannel.class)
                //广播
                .option(ChannelOption.SO_BROADCAST, true)
                //设置发送缓冲区大小
                .option(ChannelOption.SO_RCVBUF, 1024 * 1024 * 100)
                //设置初始化方法
                .handler(new ChannelInitializer<Channel>() {
                    /**
                     * 为每一个channel(连接)建立channelPipeLine
                     *并在末尾写入一个handler(类似一个vector的结构)
                     */
                    @Override
                    protected void initChannel(Channel channel) {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new NettyUdpServerHandler());
                    }
                });
        try {
            //建立连接
            Channel channel = bootstrap.bind(new InetSocketAddress("127.0.0.1", port)).sync().channel();
            log.info("netty start success!");
            channel.closeFuture().await();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }
}
