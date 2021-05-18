package cn.liaocp.amireux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class
        }
)
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
