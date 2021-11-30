package com.kaikeba.bean;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * 重新封装Courier的数据，以String类型作为展示数据
 */
public class BootstrapTableCourier {
    private Integer id;
    private String userName;
    private String userPhone;
    private String cardId;
    private String password;
    private String sendExpress;
    private String createTime;
    private String loginTime;
    private String loginIp;
    private String admin;

    @Override
    public String toString() {
        return "BootstrapTableCourier{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", cardId='" + cardId + '\'' +
                ", password='" + password + '\'' +
                ", sendExpress='" + sendExpress + '\'' +
                ", createTime='" + createTime + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }

    public BootstrapTableCourier(Integer id, String userName, String userPhone, String cardId, String password, String sendExpress, String createTime, String loginTime, String loginIp, String admin) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        this.cardId = cardId;
        this.password = password;
        this.sendExpress = sendExpress;
        this.createTime = createTime;
        this.loginTime = loginTime;
        this.loginIp = loginIp;
        this.admin = admin;
    }

    public BootstrapTableCourier() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BootstrapTableCourier that = (BootstrapTableCourier) o;
        return Objects.equals(id, that.id) && Objects.equals(userName, that.userName) && Objects.equals(userPhone, that.userPhone) && Objects.equals(cardId, that.cardId) && Objects.equals(password, that.password) && Objects.equals(sendExpress, that.sendExpress) && Objects.equals(createTime, that.createTime) && Objects.equals(loginTime, that.loginTime) && Objects.equals(loginIp, that.loginIp) && Objects.equals(admin, that.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userPhone, cardId, password, sendExpress, createTime, loginTime, loginIp, admin);
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

    public String getSendExpress() {
        return sendExpress;
    }

    public void setSendExpress(String sendExpress) {
        this.sendExpress = sendExpress;
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

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
