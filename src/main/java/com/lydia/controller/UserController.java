package com.lydia.controller;

import com.lydia.dto.UserRegister;
import com.lydia.dto.UserResult;
import com.lydia.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: UserController
 * @Description:
 * @date 2016/8/24
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/getUserList/{userId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List getUserList(@PathVariable int userId) {
        logger.info("从数据库读取User集合");
        List userList = userService.getUserList(userId);
        return userList;
    }

    //把下面method改成get请求，可以直接访问http://localhost:8080/login.jsp?userName=lsm&password=1212
    @RequestMapping(value = "/loginIn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserResult login(HttpServletRequest request) {
        logger.info("登录");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UserResult userResult = userService.login(userName, password);
        return userResult;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserResult register(HttpServletRequest request) {
//        String result = "{\"id\": \"u\", \"umessage\":\"注册成功\"}";
        String userName = request.getParameter("userName");
        int userType = Integer.parseInt(request.getParameter("userType"));
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String graduateInstitution = request.getParameter("graduateInstitution");
        String major = request.getParameter("major");
        String workingLife = request.getParameter("workingLife");
        int userLevel = Integer.parseInt(request.getParameter("userLevel"));

        UserRegister userRegister = new UserRegister(userName, userType, password, fullname, age, sex, graduateInstitution, major, workingLife, userLevel);
        UserResult userResult = userService.register(userRegister);
        return userResult;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateUser(HttpServletRequest request) {
        String result = "{\"message\":\"修改成功\"}";
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String graduateInstitution = request.getParameter("graduateInstitution");
        String major = request.getParameter("major");
        String workingLife = request.getParameter("workingLife");
        int userLevel = Integer.parseInt(request.getParameter("userLevel"));

        userService.updateUser(userId, userName, password, fullname, age, sex, graduateInstitution, major, workingLife, userLevel);
        return result;

    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteUser(HttpServletRequest request) {
        String result = "{\"message\":\"删除成功\"}";
        int userId = Integer.parseInt(request.getParameter("userId"));
        userService.deleteUser(userId);
        return result;

    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserResult getUserById(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getAttribute("loginUserId").toString());
        return userService.getUserById(userId);
    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String commit(HttpServletRequest request) {
        String result = "提交成功";

        int userId = Integer.parseInt(request.getAttribute("loginUserId").toString());
        Date now = new Date();
        userService.commit(userId, now);
        return result;
    }
}

