package codelab.springboot;

import codelab.springboot.util.console.Console;
import codelab.springboot.util.console.GenericConsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@EnableScheduling
@SpringBootApplication
public class DesktopApplication {
    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        SpringApplication.run(DesktopApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Console getSystemConsole() {
        return new GenericConsole(System.out);
    }
}

