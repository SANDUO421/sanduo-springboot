package com.auth2.permit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@Builder
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */

    private Long userId;
    /**
     *
     */
    private Long orgId;
    /**
     * 登录账号
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 手机号
     */
    private String tel;
    /**
     * 电子邮箱
     */
    private String mailbox;

    public boolean isEnabled() {
        return true;
    }



    private  boolean enabled;




    /**
     * 是否激活
     */
    private Integer isActive;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 最近登录时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    /**
     * 创建人
     */
    private String createMan;
    /**
     * 有效时间
     */
    private Date validDate;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 到期提前提醒天数
     */
    private Integer remindDay;

    /**
     * 设置：用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户ID
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取：
     */
    public Long getOrgId() {
        return orgId;
    }
    /**
     * 设置：登录账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：登录账号
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置：登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：登录密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：手机号
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取：手机号
     */
    public String getTel() {
        return tel;
    }
    /**
     * 设置：电子邮箱
     */
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    /**
     * 获取：电子邮箱
     */
    public String getMailbox() {
        return mailbox;
    }
    /**
     * 设置：是否激活
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取：是否激活
     */
    public Integer getIsActive() {
        return isActive;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置：最近登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取：最近登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    /**
     * 设置：创建人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    /**
     * 获取：创建人
     */
    public String getCreateMan() {
        return createMan;
    }
    /**
     * 设置：有效时间
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * 获取：有效时间
     */
    public Date getValidDate() {
        return validDate;
    }
    /**
     * 设置：部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：部门ID
     */
    public Long getDeptId() {
        return deptId;
    }
    /**
     * 设置：到期提前提醒天数
     */
    public void setRemindDay(Integer remindDay) {
        this.remindDay = remindDay;
    }

    /**
     * 获取：到期提前提醒天数
     */
    public Integer getRemindDay() {
        return remindDay;
    }
}
