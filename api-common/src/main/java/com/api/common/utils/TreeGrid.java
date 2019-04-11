package com.api.common.utils;
import java.util.List;

public class TreeGrid {
    private Long menuId;
    private Long parentId;
    private String menuName;
    private String url;
    private Integer menuType;
    private String menuCode;
    private Boolean open;
    private Long orderNum;

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    private String menuDesc;

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    private List<TreeGrid> children;
    public Integer getMenuType() {
        return menuType;
    }
    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }


    public List<TreeGrid> getChildren() {
        return children;
    }
    public void setChildren(List<TreeGrid> children) {
        this.children = children;
    }
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }



}
