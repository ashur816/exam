package com.lydia.dao.mapper;

import com.lydia.dto.UserRegister;
import com.lydia.dto.UserResult;
import com.lydia.po.User;
import com.lydia.po.UserGoal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: UserMapper
 * @Description:
 * @date 2016/8/24
 */
@Component
public interface UserMapper {

    UserResult getUserById(int userId);

    User getUser(@Param("userName") String userName, @Param("password") String password);

    List<UserResult> getUserList(@Param("userId") int userId);

    void register(UserRegister userRegister);

    void updateUser(@Param("userId") int userId, @Param("userName") String userName, @Param("password") String password,
                    @Param("fullname") String fullname, @Param("age") int age, @Param("sex") String sex,
                    @Param("graduateInstitution") String graduateInstitution, @Param("major") String major, @Param("workingLife") String workingLife, @Param("userLevel") int userLevel);

    void deleteUser(int userId);

    User singleUser(String name);

    void updateUserGoal(@Param("examMaker") String examMaker, @Param("userId") int userId, @Param("totalGoal") String totalGoal);

    void insertUserGoal(@Param("userId") int userId, @Param("startDate") Date startDate);

    UserGoal getLatestUserGoal(int userId);

    void commit(@Param("userId") int userId, @Param("endDate") Date endDate);
}
