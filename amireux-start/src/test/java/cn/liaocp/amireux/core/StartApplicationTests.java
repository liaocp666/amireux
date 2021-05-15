package cn.liaocp.amireux.core;

import cn.liaocp.amireux.StartApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = StartApplication.class)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
class StartApplicationTests {

    @Test
    void contextLoads() {

    }

}
