package com.lydia.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/6.
 */
public class UserResult implements Serializable{
    private static final long serialVersionUID = -503720886795820883L;

    private int userId;
    private String token;
    private String userName;
    private int userType;
    private String password;
    private String fullname;
    private String graduateInstitution;
    private int age;
    private String sex;
    private String major;
    private String workingLife;
    private int userLevel;
    private String message;
    private String startTime;
    private String endTime;
    private String redirectUrl;
    private String examMarker;
    private String totalGoal;

    public  UserResult(){}

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public int getUserType() {
        return userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getGraduateInstitution() { return graduateInstitution; }
    public void setGraduateInstitution(String graduateInstitution) { this.graduateInstitution = graduateInstitution; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getWorkingLife() { return workingLife; }
    public void setWorkingLife(String workingLife) { this.workingLife = workingLife; }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getExamMarker() { return examMarker; }
    public void setExamMarker(String examMarker) { this.examMarker = examMarker; }

    public int getUserLevel() { return userLevel; }
    public void setUserLevel(int userLevel) { this.userLevel = userLevel; }

    public String getTotalGoal() { return totalGoal; }
    public void setTotalGoal(String totalGoal) { this.totalGoal = totalGoal; }
}
