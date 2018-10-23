package com.itaiti.tools;


import java.util.List;
import java.util.Objects;

public class PageList<T> {
    /** 当前页码 从1开始 */
    private int pageIndex = 1;
    /** 总页数 */
    public int totalPage = 0;
    /** 每页数据条数 */
    private int pageSize;
    /** 总数据数 */
    private int totalCount = 0;
    /** 数据 */
    private List<T> list;

    public PageList(List<T> list, int pageSize) {
        Objects.requireNonNull(list);
        init(list, pageSize);
    }

    /**
     * 初始化list,每页数据条数
     * 
     * @param list
     * @param pageSize
     */
    public void init(List<T> list, int pageSize) {
        this.pageSize = pageSize;
        this.list = list;
        totalCount = list.size();
        if ((totalCount % pageSize) == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
    }

    public boolean hasNextPage() {
        return pageIndex != totalPage + 1;
    }

    public boolean hasPreviousPage() {
        return pageIndex != 0;
    }

    public List<T> getNextPage() {
        List<T> listPage = getPage(pageIndex);
        pageIndex = pageIndex + 1;
        return listPage;
    }

    public List<T> getPreviousPage() {
        List<T> listPage = getPage(pageIndex);
        pageIndex = pageIndex - 1;
        return listPage;
    }

    /**
     * 获取第几页的内容
     *
     * @param pageIndex从1开始
     * @return
     */
    public List<T> getPage(int pageIndex) {
        setPageIndex(pageIndex);
        return list.subList(getPageStartRow(), getPageEndRow());
    }

    public List<T> getList() {
        return list;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex <= 0) {
            this.pageIndex = 1;
        } else {
            this.pageIndex = pageIndex;
        }

        if (pageIndex > totalPage) {
            throw new ArrayIndexOutOfBoundsException(pageIndex + "页不存在");
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageStartRow() {
        if (pageIndex * pageSize < totalCount) {
            return (pageIndex - 1) * pageSize;
        } else {
            return pageSize * (totalPage - 1);
        }
    }

    public int getPageEndRow() {
        if (pageIndex * pageSize < totalCount) {
            return pageIndex * pageSize;
        } else {
            return totalCount;
        }
    }
}
