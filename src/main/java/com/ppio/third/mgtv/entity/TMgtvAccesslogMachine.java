package com.ppio.third.mgtv.entity;

import java.util.Date;
//---
public class TMgtvAccesslogMachine {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mgtv_accesslog_machine.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mgtv_accesslog_machine.machine_id
     *
     * @mbg.generated
     */
    private String machineId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mgtv_accesslog_machine.ip
     *
     * @mbg.generated
     */
    private String ip;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mgtv_accesslog_machine.usename
     *
     * @mbg.generated
     */
    private String usename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mgtv_accesslog_machine.pwd
     *
     * @mbg.generated
     */
    private String pwd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mgtv_accesslog_machine.enable
     *
     * @mbg.generated
     */
    private Boolean enable;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mgtv_accesslog_machine.crate_time
     *
     * @mbg.generated
     */
    private Date crateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mgtv_accesslog_machine.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mgtv_accesslog_machine.id
     *
     * @return the value of t_mgtv_accesslog_machine.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mgtv_accesslog_machine.id
     *
     * @param id the value for t_mgtv_accesslog_machine.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mgtv_accesslog_machine.machine_id
     *
     * @return the value of t_mgtv_accesslog_machine.machine_id
     *
     * @mbg.generated
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mgtv_accesslog_machine.machine_id
     *
     * @param machineId the value for t_mgtv_accesslog_machine.machine_id
     *
     * @mbg.generated
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId == null ? null : machineId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mgtv_accesslog_machine.ip
     *
     * @return the value of t_mgtv_accesslog_machine.ip
     *
     * @mbg.generated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mgtv_accesslog_machine.ip
     *
     * @param ip the value for t_mgtv_accesslog_machine.ip
     *
     * @mbg.generated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mgtv_accesslog_machine.usename
     *
     * @return the value of t_mgtv_accesslog_machine.usename
     *
     * @mbg.generated
     */
    public String getUsename() {
        return usename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mgtv_accesslog_machine.usename
     *
     * @param usename the value for t_mgtv_accesslog_machine.usename
     *
     * @mbg.generated
     */
    public void setUsename(String usename) {
        this.usename = usename == null ? null : usename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mgtv_accesslog_machine.pwd
     *
     * @return the value of t_mgtv_accesslog_machine.pwd
     *
     * @mbg.generated
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mgtv_accesslog_machine.pwd
     *
     * @param pwd the value for t_mgtv_accesslog_machine.pwd
     *
     * @mbg.generated
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mgtv_accesslog_machine.enable
     *
     * @return the value of t_mgtv_accesslog_machine.enable
     *
     * @mbg.generated
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mgtv_accesslog_machine.enable
     *
     * @param enable the value for t_mgtv_accesslog_machine.enable
     *
     * @mbg.generated
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mgtv_accesslog_machine.crate_time
     *
     * @return the value of t_mgtv_accesslog_machine.crate_time
     *
     * @mbg.generated
     */
    public Date getCrateTime() {
        return crateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mgtv_accesslog_machine.crate_time
     *
     * @param crateTime the value for t_mgtv_accesslog_machine.crate_time
     *
     * @mbg.generated
     */
    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mgtv_accesslog_machine.update_time
     *
     * @return the value of t_mgtv_accesslog_machine.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mgtv_accesslog_machine.update_time
     *
     * @param updateTime the value for t_mgtv_accesslog_machine.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}