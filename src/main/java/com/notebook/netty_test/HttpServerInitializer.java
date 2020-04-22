package com.notebook.netty_test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.notebook.netty_test.PrintHandler.SHARE;

/**
 * @author tusu.
 */
@Component
public class HttpServerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline()
                .addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS))
                .addLast(SHARE);
    }
}
