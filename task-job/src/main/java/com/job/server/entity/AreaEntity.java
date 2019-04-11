package com.job.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 *
 * @author panbo
 * @email ${email}
 * @date 2019-03-18 17:01:33
 */
@TableName("t_area")
public class AreaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 区域ID
         */
            @TableId(type = IdType.INPUT)
            private Long areaId;
            /**
         * 父ID
         */
            private Long parentRegionCode;
            /**
         * 区域名称
         */
            private String regionName;
            /**
         * 区域等级
         */
            private Integer regionLevel;
            /**
         * 经度
         */
            private String longitude;
            /**
         * 纬度
         */
            private String latitude;
            /**
         * 拼音
         */
            private String pinyin;
            /**
         * 域名
         */
            private String domain;
            /**
         * 电话
         */
            private String tel;
            /**
         * 地址
         */
            private String address;
            /**
         * 面积
         */
            private Float acreage;
            /**
         * 合并名称
         */
            private String mergerName;
            /**
         * 简称
         */
            private String shortName;
            /**
         * 合并名称缩写
         */
            private String mergerShortName;
            /**
         * 区号
         */
            private String cityCode;
            /**
         * 邮编
         */
            private String zipCode;
            /**
         * 简拼
         */
            private String jianpin;
            /**
         * 首字母
         */
            private String firstChar;
            /**
         * 备注
         */
            private String remarks;
    
            /**
         * 设置：区域ID
         */
        public void setAreaId(Long areaId) {
            this.areaId = areaId;
        }

        /**
         * 获取：区域ID
         */
        public Long getAreaId() {
            return areaId;
        }
            /**
         * 设置：父ID
         */
        public void setParentRegionCode(Long parentRegionCode) {
            this.parentRegionCode = parentRegionCode;
        }

        /**
         * 获取：父ID
         */
        public Long getParentRegionCode() {
            return parentRegionCode;
        }
            /**
         * 设置：区域名称
         */
        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        /**
         * 获取：区域名称
         */
        public String getRegionName() {
            return regionName;
        }
            /**
         * 设置：区域等级
         */
        public void setRegionLevel(Integer regionLevel) {
            this.regionLevel = regionLevel;
        }

        /**
         * 获取：区域等级
         */
        public Integer getRegionLevel() {
            return regionLevel;
        }
            /**
         * 设置：经度
         */
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        /**
         * 获取：经度
         */
        public String getLongitude() {
            return longitude;
        }
            /**
         * 设置：纬度
         */
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        /**
         * 获取：纬度
         */
        public String getLatitude() {
            return latitude;
        }
            /**
         * 设置：拼音
         */
        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        /**
         * 获取：拼音
         */
        public String getPinyin() {
            return pinyin;
        }
            /**
         * 设置：域名
         */
        public void setDomain(String domain) {
            this.domain = domain;
        }

        /**
         * 获取：域名
         */
        public String getDomain() {
            return domain;
        }
            /**
         * 设置：电话
         */
        public void setTel(String tel) {
            this.tel = tel;
        }

        /**
         * 获取：电话
         */
        public String getTel() {
            return tel;
        }
            /**
         * 设置：地址
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * 获取：地址
         */
        public String getAddress() {
            return address;
        }
            /**
         * 设置：面积
         */
        public void setAcreage(Float acreage) {
            this.acreage = acreage;
        }

        /**
         * 获取：面积
         */
        public Float getAcreage() {
            return acreage;
        }
            /**
         * 设置：合并名称
         */
        public void setMergerName(String mergerName) {
            this.mergerName = mergerName;
        }

        /**
         * 获取：合并名称
         */
        public String getMergerName() {
            return mergerName;
        }
            /**
         * 设置：简称
         */
        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        /**
         * 获取：简称
         */
        public String getShortName() {
            return shortName;
        }
            /**
         * 设置：合并名称缩写
         */
        public void setMergerShortName(String mergerShortName) {
            this.mergerShortName = mergerShortName;
        }

        /**
         * 获取：合并名称缩写
         */
        public String getMergerShortName() {
            return mergerShortName;
        }
            /**
         * 设置：区号
         */
        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        /**
         * 获取：区号
         */
        public String getCityCode() {
            return cityCode;
        }
            /**
         * 设置：邮编
         */
        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        /**
         * 获取：邮编
         */
        public String getZipCode() {
            return zipCode;
        }
            /**
         * 设置：简拼
         */
        public void setJianpin(String jianpin) {
            this.jianpin = jianpin;
        }

        /**
         * 获取：简拼
         */
        public String getJianpin() {
            return jianpin;
        }
            /**
         * 设置：首字母
         */
        public void setFirstChar(String firstChar) {
            this.firstChar = firstChar;
        }

        /**
         * 获取：首字母
         */
        public String getFirstChar() {
            return firstChar;
        }
            /**
         * 设置：备注
         */
        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        /**
         * 获取：备注
         */
        public String getRemarks() {
            return remarks;
        }
    }
