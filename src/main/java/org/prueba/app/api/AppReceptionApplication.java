package org.prueba.app.api;

import javax.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.prueba.app.api.controllers.ReceptorMessageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class AppReceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppReceptionApplication.class, args);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("jms.message.mq");
    }
}
