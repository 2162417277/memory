package com.gnahz.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 张伟洁
 * Date:2024-01-10-19:42
 * @create 忆项目(小白)
 */
public class GrowEntity implements Serializable {
    private Integer id;
    private String pageName;
    private Integer pageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
