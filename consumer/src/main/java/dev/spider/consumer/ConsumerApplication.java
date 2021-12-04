package dev.spider.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {

    public static void main(String[] args) {


        /*
            +---------------------------+
            |                           |
            |                           |
            |                           |
            |                           |
            |      +--------------+     |
            |      |              |     |
            |      |     fuck     |     |
            |      |              |     |
            |      +--------------+     |
            |                           |
            |                           |
            |                           |
            |                           |
            +---------------------------+
        */
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
