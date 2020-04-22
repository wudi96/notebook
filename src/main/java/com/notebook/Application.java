package com.notebook;

import com.notebook.netty_test.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

/**
 * @author tusu.
 */
@SpringBootApplication
public class Application implements ApplicationListener<ContextClosedEvent> {

  private final HttpServer httpServer;

  @Autowired
  public Application(HttpServer httpServer) {
    this.httpServer = httpServer;
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    HttpServer server = context.getBean(HttpServer.class);
    server.start();
  }

  @Override
  public void onApplicationEvent(ContextClosedEvent event) {
    httpServer.close();
  }
}
