package com.project.gimme;

import com.project.gimme.nettyServer.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author DrGilbert
 */
@SpringBootApplication
public class GimmeApplication {
    public static void main(String[] args) {

        SpringApplication.run(GimmeApplication.class, args);
        new NettyServer().start(8888);
        //启动netty
    }
}
