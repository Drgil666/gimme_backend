package com.project.gimme.nettyServer;

import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author DrGilbert
 */
@Component
@Slf4j
public class NettyUdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        //16进制包进行转换
        String data = ByteBufUtil.hexDump(msg.content());
        log.info("udp数据:" + data);
        String sendString = "已接收到信息!" + System.currentTimeMillis();
        byte[] bytes = sendString.getBytes(StandardCharsets.UTF_8);
        DatagramPacket sendData = new DatagramPacket(Unpooled.copiedBuffer(bytes), msg.sender());
        ctx.writeAndFlush(sendData);
    }
}
