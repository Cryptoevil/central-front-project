package pro.cryptoevil.front;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.telegram.telegrambots.ApiContextInitializer;

@EnableFeignClients
@SpringCloudApplication
public class FrontApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(FrontApplication.class, args);
    }
}
