package com.notebook.netty_test;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by luorigong on 2020-04-22.
 */
@Sharable
public class PrintHandler extends ChannelInboundHandlerAdapter {

  static ChannelHandler SHARE = new PrintHandler();

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("1");
    ctx.fireChannelRead(msg);
  }
}
