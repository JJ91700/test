package com.kaikeba.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Express {
    /**
     * 自动递增
     */
    private Integer id;
    /**
     * 快递单号
     */
    private String number;
    /**
     * 用户名
     */
    private String username;
    /**
     * 电话
     */
    private String userPhone;
    /**
     * 公司
     */
    private String company;
    /**
     * 取件码
     */
    private String code;
    /**
     * 录入时间
     */
    private Timestamp inTime;
    /**
     * 取出时间
     */
    private Timestamp outTime;
    /**
     * 快递状态
     */
    private Integer status;
    /**
     * 录入人电话
     */
    private String sysPhone;

    @Override
    public String toString() {
        return "Express{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", username='" + username + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", company='" + company + '\'' +
                ", code='" + code + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", status=" + status +
                ", sysPhone='" + sysPhone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Express express = (Express) o;
        return Objects.equals(id, express.id) && Objects.equals(number, express.number) && Objects.equals(username, express.username) && Objects.equals(userPhone, express.userPhone) && Objects.equals(company, express.company) && Objects.equals(code, express.code) && Objects.equals(inTime, express.inTime) && Objects.equals(outTime, express.outTime) && Objects.equals(status, express.status) && Objects.equals(sysPhone, express.sysPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
    }

    public Express() {
    }

    /**
     * 这个构造函数是为了更好的创建Express
     * @param number    快递单号
     * @param username  用户名
     * @param userPhone 用户手机
     * @param company   公司
     * @param sysPhone  录入手机
     */
    public Express(String number, String username, String userPhone, String company, String sysPhone) {
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.company = company;
        this.sysPhone = sysPhone;
    }

    public Express(String number, String username, String userPhone, String company, String code, String sysPhone) {
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.company = company;
        this.code = code;
        this.sysPhone = sysPhone;
    }

    public Express(Integer id, String number, String username, String userPhone, String company, String code, Timestamp inTime, Timestamp outTime, Integer status, String sysPhone) {
        this.id = id;
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.company = company;
        this.code = code;
        this.inTime = inTime;
        this.outTime = outTime;
        this.status = status;
        this.sysPhone = sysPhone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }
}
