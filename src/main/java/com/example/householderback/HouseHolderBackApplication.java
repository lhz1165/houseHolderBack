package com.example.householderback;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;


@MapperScan("com.example.householderback.dao")
@SpringBootApplication
public class HouseHolderBackApplication implements ApplicationListener<WebServerInitializedEvent> {
    Logger log = LoggerFactory.getLogger(HouseHolderBackApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HouseHolderBackApplication.class, args);
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        WebServer server = event.getWebServer();
        int port = server.getPort();
        log.info("\n---------------------------------------------------------\n" +
                "\tApplication is running! Access address:\n" +
                "\n\tswagger-ui:\thttp://localhost:{}/swagger-ui.html#/" +
                "\n---------------------------------------------------------\n", port);
    }
}
