package com.company.project.service;
import com.company.project.model.User;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/03/01.
 */
public interface UserService extends Service<User> {
    Integer addUser(String did);
    User getUserById(Integer userId);
    Integer updateUser(User user);
}
