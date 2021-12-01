package com.kaikeba.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Courier {
    private Integer id;
    private String userName;
    private String userPhone;
    private String cardId;
    private String password;
    private Integer sendExpress;
    private Timestamp createTime;
    private Timestamp loginTime;
    private String loginIp;

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", cardId='" + cardId + '\'' +
                ", password='" + password + '\'' +
                ", sendExpress=" + sendExpress +
                ", createTime=" + createTime +
                ", loginTime=" + loginTime +
                ", loginIp='" + loginIp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Objects.equals(id, courier.id) && Objects.equals(userName, courier.userName) && Objects.equals(userPhone, courier.userPhone) && Objects.equals(cardId, courier.cardId) && Objects.equals(password, courier.password) && Objects.equals(sendExpress, courier.sendExpress) && Objects.equals(createTime, courier.createTime) && Objects.equals(loginTime, courier.loginTime) && Objects.equals(loginIp, courier.loginIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userPhone, cardId, password, sendExpress, createTime, loginTime, loginIp);
    }

    public Courier(Integer id, String userName, String userPhone, String cardId, String password, Integer sendExpress, Timestamp createTime, Timestamp loginTime, String loginIp) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        this.cardId = cardId;
        this.password = password;
        this.sendExpress = sendExpress;
        this.createTime = createTime;
        this.loginTime = loginTime;
        this.loginIp = loginIp;
    }

    public Courier(Integer id, String userName, String userPhone, String cardId, String password) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        this.cardId = cardId;
        this.password = password;
    }

    public Courier() {
    }

    public Courier(String userName, String userPhone, String cardId, String password) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.cardId = cardId;
        this.password = password;
    }

    public Courier(Integer id, String userName, String userPhone, String cardId, String password, Integer sendExpress, Timestamp createTime, Timestamp loginTime) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        this.cardId = cardId;
        this.password = password;
        this.sendExpress = sendExpress;
        this.createTime = createTime;
        this.loginTime = loginTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getSendExpress() {
        return sendExpress;
    }

    public void setSendExpress(Integer sendExpress) {
        this.sendExpress = sendExpress;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
