package com.kute.demo.orm.mybatis.plugin;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.List;


public class Pagination<T> implements Serializable {

    public static final int DEFAULT_PAGE_SIZE = 20;

    protected int currentPage = 1; // 当前页, 默认为第1页
    protected int pageSize = DEFAULT_PAGE_SIZE; // 每页记录数
    protected long totalCount = -1; // 总记录数, 默认为-1, 表示需要查询
    protected int totalPage = -1; // 总页数, 默认为-1, 表示需要计算
    protected T params;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    protected List<T> list; // 当前页记录List形式

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if(currentPage == 0) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
        computeTotalPage();
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        computeTotalPage();
    }

    public int getTotalPage() {
        return totalPage;
    }

    protected void computeTotalPage() {
        if (getPageSize() > 0 && getTotalCount() > -1) {
            this.totalPage = (int) (getTotalCount() % getPageSize() == 0 ? getTotalCount() / getPageSize() : getTotalCount() / getPageSize() + 1);
        }
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("currentPage", currentPage)
                .add("pageSize", pageSize)
                .add("totalCount", totalCount)
                .add("totalPage", totalPage)
                .add("params", params)
                .add("list", list)
                .toString();
    }
}
