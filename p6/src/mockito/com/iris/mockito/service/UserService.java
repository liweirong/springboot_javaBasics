package mockito.com.iris.mockito.service;


import mockito.com.iris.mockito.entity.User;
import mockito.com.iris.mockito.mapper.UserMapper;

/**
 * @author iris
 * @date 2023/5/25
 */
public class UserService {

    private UserMapper userMapper;

    public UserService(UserMapper userDao) {
        this.userMapper = userDao;
    }

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}
