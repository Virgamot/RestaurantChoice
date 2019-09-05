package ru.graduation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("\nBeans:\n");
            for (String bean : appCtx.getBeanDefinitionNames()) {
                System.out.println(bean);
            }
        }
    }
}
