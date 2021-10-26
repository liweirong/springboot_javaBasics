package svip_homework.b_spring;

import org.springframework.stereotype.Service;

/**
 * @author iris
 * @date 2021/10/21
 *
 * 报错信息如下
 * The dependencies of some of the beans in the application context form a cycle:
 *
 * ┌─────┐
 * |  AService defined in file [D:\workspace\idea_workspace\git_project\springboot_all\p6\target\classes\svip_homework\b_spring\AService.class]
 * ↑     ↓
 * |  BService (field private svip_homework.b_spring.AService svip_homework.b_spring.BService.aService)
 * └─────┘
 */
@Service
public class AService {

    private BService bService;

    public AService(BService bService) {
         this.bService = bService;
    }
}
