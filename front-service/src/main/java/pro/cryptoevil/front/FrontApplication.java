package pro.cryptoevil.front;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringCloudApplication
public class FrontApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(FrontApplication.class, args);
    }
}
