package com.auth2.permit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 *
 * @author panbo
 * @email ${email}
 * @date 2019-03-18 17:01:34
 */
@TableName("t_role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 角色ID
         */
            @TableId(type = IdType.ID_WORKER)
            private Long roleId;
            /**
         * 角色名称
         */
            private String roleName;
            /**
         * 角色描述
         */
            private String roleDesc;
            /**
         * 创建时间
         */
            private Date createTime;
    
            /**
         * 设置：角色ID
         */
        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }

        /**
         * 获取：角色ID
         */
        public Long getRoleId() {
            return roleId;
        }
            /**
         * 设置：角色名称
         */
        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        /**
         * 获取：角色名称
         */
        public String getRoleName() {
            return roleName;
        }
            /**
         * 设置：角色描述
         */
        public void setRoleDesc(String roleDesc) {
            this.roleDesc = roleDesc;
        }

        /**
         * 获取：角色描述
         */
        public String getRoleDesc() {
            return roleDesc;
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
    }
