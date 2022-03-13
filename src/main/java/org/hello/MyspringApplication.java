package org.hello;

import lombok.RequiredArgsConstructor;
import org.hello.netty.client.NettyClient;
import org.hello.netty.server.HttpServer;
import org.hello.netty.server.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class MyspringApplication implements CommandLineRunner {

    private final NettyServer server;
    private final NettyClient client;
    private final HttpServer httpServer;

    public static void main(String[] args) {
        SpringApplication.run(MyspringApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Thread sthread = new Thread(server);
        Thread cthread = new Thread(client);
        sthread.start();
        cthread.start();
        sthread.join();
        cthread.join();

    }

/*
    @Override
    public void run(String... args) throws Exception {
        Thread sthread = new Thread(httpServer);
        sthread.start();
        sthread.join();
    }
 */
}
