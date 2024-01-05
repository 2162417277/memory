package com.gnahz.api;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-01-05-12:23
 * @create 忆项目(小白)
 * 分页数据封装类
 */
public class CommonPage <T>{
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将MyBatis Plus 分页结果转化为通用结果
     * @param pageResult
     * @param <T>
     * @return
     */
    public static <T> CommonPage<T> restPage(Page<T> pageResult){
        CommonPage<T> result = new CommonPage<>();
        //当前页
        result.setPageNum(Convert.toInt(pageResult.getCurrent()));
        //一页多少条数据
        result.setPageSize(Convert.toInt(pageResult.getSize()));
        //总数据数量
        result.setTotal(pageResult.getTotal());
        //总页数
        result.setTotalPage(Convert.toInt(pageResult.getTotal()/pageResult.getSize()+1));
        //当前页数据
        return result;
    }


    /**
     * get set 方法
     * @return
     */
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
