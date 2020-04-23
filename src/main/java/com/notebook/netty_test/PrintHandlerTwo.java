package com.notebook.netty_test;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author luorigong
 */
@Sharable
public class PrintHandlerTwo extends ChannelInboundHandlerAdapter {

  static ChannelHandler SHARE = new PrintHandlerTwo();

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("2");
    System.out.println("msg:" + msg);
    ctx.fireChannelRead(msg);
  }
}
