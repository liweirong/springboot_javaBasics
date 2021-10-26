package svip_homework.b_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author iris
 * @date 2021/10/18
 */
@SpringBootApplication(scanBasePackages = "svip_homework.b_spring")
public class StarterProcess {

    public static void main(String[] args) {
        SpringApplication.run(StarterProcess.class, args);
        ApplicationContext context = new ClassPathXmlApplicationContext();
        System.out.println(context.getBean("aService"));
        System.out.println(context.getBean("bService"));

    }
}
