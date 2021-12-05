package com.kaikeba.bean;

import java.util.Objects;

public class BootstrapTableUser {
    private Integer id;
    private String nickName;
    private String userPhone;
    private String cardId;
    private String password;
    private String createTime;
    private String loginTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", cardId='" + cardId + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", loginTime=" + loginTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BootstrapTableUser user = (BootstrapTableUser) o;
        return Objects.equals(id, user.id) && Objects.equals(nickName, user.nickName) && Objects.equals(userPhone, user.userPhone) && Objects.equals(cardId, user.cardId) && Objects.equals(password, user.password) && Objects.equals(createTime, user.createTime) && Objects.equals(loginTime, user.loginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, userPhone, cardId, password, createTime, loginTime);
    }

    /**
     * for delete
     */
    public BootstrapTableUser(int id) {
        this.id = id;
    }

    /**
     * for insert
     */
    public BootstrapTableUser(String nickName, String userPhone, String cardId, String password) {
        this.nickName = nickName;
        this.userPhone = userPhone;
        this.cardId = cardId;
        this.password = password;
    }

    /**
     * for update
     */
    public BootstrapTableUser(Integer id, String nickName, String userPhone, String cardId, String password) {
        this.id = id;
        this.nickName = nickName;
        this.userPhone = userPhone;
        this.cardId = cardId;
        this.password = password;
    }

    public BootstrapTableUser() {
    }

    public BootstrapTableUser(Integer id, String nickName, String userPhone, String cardId, String password, String createTime, String loginTime) {
        this.id = id;
        this.nickName = nickName;
        this.userPhone = userPhone;
        this.cardId = cardId;
        this.password = password;
        this.createTime = createTime;
        this.loginTime = loginTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
