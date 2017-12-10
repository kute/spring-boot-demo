package com.kute.demo.po;

import java.sql.Timestamp;

/**
 * Created by kute on 2017/12/9.
 */
public class UserData extends Bean {
    private static final long serialVersionUID = 7463242860366735313L;

    private String userId;

    private String userName;

    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    public UserData() {
    }

    public UserData(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
