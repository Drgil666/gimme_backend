package com.project.gimme;

import com.project.gimme.nettyServer.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author DrGilbert
 */
@SpringBootApplication
public class GimmeApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(GimmeApplication.class, args);
        new NettyServer().init();
        //启动netty
    }
}
