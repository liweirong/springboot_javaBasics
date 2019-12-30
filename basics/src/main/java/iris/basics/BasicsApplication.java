package iris.basics;

import iris.basics.annotation.TestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author iris
 * @date 2019/12/27
 */
@SpringBootApplication
public class BasicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicsApplication.class);
        TestClient beans = ApplicationContextUtils.getClass(TestClient.class);
        System.out.println(beans);
    }
}
