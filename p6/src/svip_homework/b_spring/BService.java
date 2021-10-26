package svip_homework.b_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author iris
 * @date 2021/10/21
 */
@Service
public class BService {
    @Autowired
    private AService aService;

}
