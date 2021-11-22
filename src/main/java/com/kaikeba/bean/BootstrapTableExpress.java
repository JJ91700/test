package com.kaikeba.bean;


/**
 * 重新封装Express的数据，以String类型作为展示数据
 */
public class BootstrapTableExpress {
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
    private String inTime;
    /**
     * 取出时间
     */
    private String outTime;
    /**
     * 快递状态
     */
    private String status;
    /**
     * 录入人电话
     */
    private String sysPhone;

    public BootstrapTableExpress() {
    }

    public BootstrapTableExpress(Integer id, String number, String username, String userPhone, String company, String code, String inTime, String outTime, String status, String sysPhone) {
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

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }
}
