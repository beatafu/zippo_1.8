package onight.osgi.otransio.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import onight.osgi.otransio.netty.NSessionSets;
import onight.osgi.otransio.util.Packets;
import onight.tfw.otransio.api.PackHeader;
import onight.tfw.otransio.api.beans.FramePacket;

@Slf4j
public class HeartBeatHandler extends IdleStateHandler {

    private NSessionSets nss;

    public HeartBeatHandler(NSessionSets nss, int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
        this.nss = nss;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            //发送心跳消息
            FramePacket hb = Packets.newHB(nss.selfNodeName());
            ctx.writeAndFlush(hb);
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FramePacket){
            FramePacket fp = (FramePacket)msg;
            if(PackHeader.CMD_HB.equals(fp.getModuleAndCMD())){
                //处理心跳消息
                if(log.isTraceEnabled()){
                    log.trace("[HB] From {} to {}", ctx.channel().remoteAddress(), ctx.channel().localAddress());
                }
                return;
            }
        }
        //如果为其他消息则不处理
        super.channelRead(ctx, msg);
    }
}
