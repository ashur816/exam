package com.lydia.service;

import com.lydia.dto.UserRegister;
import com.lydia.dto.UserResult;
import com.lydia.po.User;

import java.util.Date;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: IUserService
 * @Description:
 * @date 2016/8/25
 */
public interface IUserService {

    public UserResult login(String userName, String password);
    public List<UserResult> getUserList(int userId);
    public UserResult register(UserRegister userRegister);
    public void updateUser(int userId, String userName, String password, String fullname, int age, String sex, String graduateInstitution, String major, String workingLife, int userLevel);
    public void deleteUser(int userId);
    public User singleUser(String username);
    public void commit(int userId, Date endDate);
    public UserResult getUserById(int userId);
}
