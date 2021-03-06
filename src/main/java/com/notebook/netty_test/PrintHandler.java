package com.notebook.netty_test;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author luorigong.
 */
@Sharable
public class PrintHandler extends ChannelInboundHandlerAdapter {

  static ChannelHandler SHARE = new PrintHandler();

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("1");
    System.out.println("msg:" + msg);
    if ("needNext".equals(msg)) {
      //继续调用下一个channel
      ctx.fireChannelRead(msg);
    } else {
      //终止
      ctx.fireChannelReadComplete();
    }
  }
}
