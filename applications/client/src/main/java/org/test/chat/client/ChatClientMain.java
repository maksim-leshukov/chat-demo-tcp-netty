package org.test.chat.client;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatClientMain {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ChatClientMain.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

}
