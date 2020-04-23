package com.notebook.netty_test;

import io.netty.channel.embedded.EmbeddedChannel;

/**
 * Created by luorigong on 2020-04-22.
 */
public class EmbeddedTest {

  private final EmbeddedChannel apiChannel;

  public EmbeddedTest() {
    this.apiChannel = new EmbeddedChannel(new HttpServerInitializer());
  }

  public void test() {
    apiChannel.writeInbound("234");
  }

  public static void main(String[] args) {
    new EmbeddedTest().test();
  }
}
