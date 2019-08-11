package com.value.entities;
/**
* @Description 用户信息
* @Author bxf
* @Date   2019/8/8
*/
public class User {
    /** 用户id*/
    private Long userId;

    /** 用户名*/
    private String username;

    /** 密码*/
    private String password;

    /** 昵称*/
    private String nickname;

    /** 用户级别*/
    private String level;

    /** 备注*/
    private String note;

    /** 是否删除（1.是；0.否）*/
    private String sign;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
