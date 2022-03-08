package svip_homework.c_mybatis;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import svip_homework.b_spring.StarterProcess;

/**
 * @author iris
 * @date 2022/2/19
 */
@RestController
@SpringBootApplication(scanBasePackages = "svip_homework.c_mybatis")
@MapperScan(value = "svip_homework.c_mybatis")
public class Mybatis {

    @Autowired
    private  SqlSessionFactory sqlSessionFactory;
    public static void main(String[] args) {
        SpringApplication.run(Mybatis.class, args);
        ApplicationContext context = new ClassPathXmlApplicationContext();

    }
    @GetMapping("test")
    public void test(){
        Configuration configuration = null;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        System.out.println(mapper.selectA());
        // 二级缓存 必须配置 useCache="true"
        System.out.println(mapper.selectA());
    }
}
