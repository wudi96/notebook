package com.notebook.netty_test;

import io.netty.channel.embedded.EmbeddedChannel;

/**
 * EmbeddedChannel嵌入式通道,想像成一个不需要建立客户端与服务端就可以用pipeline的东西
 * @author luorigong.
 */
public class EmbeddedTest {

  private final EmbeddedChannel apiChannel;

  public EmbeddedTest() {
    this.apiChannel = new EmbeddedChannel(new HttpServerInitializer());
  }

  private void test() {
    apiChannel.writeInbound("needNext");
    apiChannel.writeInbound("NoNeedNext");
  }

  public static void main(String[] args) {
    new EmbeddedTest().test();
  }
}
