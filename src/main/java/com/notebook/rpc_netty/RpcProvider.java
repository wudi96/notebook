package com.notebook.rpc_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author luorigong
 */
public class RpcProvider {

  private static void startServer0(String hostName, int port) {
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
      bootstrap.group(eventLoopGroup)
          .channel(NioServerSocketChannel.class)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              ChannelPipeline p = ch.pipeline();
              p.addLast(new StringDecoder());
              p.addLast(new StringEncoder());
              p.addLast(new HelloServerHandler());
            }
          });
      bootstrap.bind(hostName, port).sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    RpcProvider.startServer0("localhost", 8088);
  }
}
