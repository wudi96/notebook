package com.notebook.netty_test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;
import org.springframework.stereotype.Component;

/**
 * @author luorigong.
 */
@Component
public class HttpServer {

  private final static EventLoopGroup BOSS_GROUP = new NioEventLoopGroup(1);
  private final static EventLoopGroup WORKER_GROUP = new NioEventLoopGroup();

  private final HttpServerInitializer serverInitializer;

  public HttpServer(HttpServerInitializer serverInitializer) {
    this.serverInitializer = serverInitializer;
  }

  public void start() {
    try {
      ChannelFuture f = new ServerBootstrap()
          .group(BOSS_GROUP, WORKER_GROUP)
          .channel(NioServerSocketChannel.class)
          .childHandler(serverInitializer)
          .localAddress(new InetSocketAddress(8080))
          .bind()
          .sync();
      //在连接关闭之前保持等待
      f.channel().closeFuture().sync();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BOSS_GROUP.shutdownGracefully();
      WORKER_GROUP.shutdownGracefully();
    }
  }

  public void close() {
    BOSS_GROUP.shutdownGracefully();
    WORKER_GROUP.shutdownGracefully();
  }
}
