package com.sxgokit.rdf.common;

import java.io.Serializable;


public class Page implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 每页显示的记录数
     */
    private Integer showCount = 10;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总记录数
     */
    private Integer totalResult;
    /**
     * 当前页
     */
    private Integer currentPage = 1;
    /**
     * 当前记录起始索引
     */
    private Integer currentResult;
    /**
     * true 需要分页的地方，传入的参数就是Page实体 false 需要分页的地方，传入的参数所代表的实体拥有Page属性
     */
    private Boolean entityOrField;
    
    /**
     * 需要排序的字段
     */
    private String orderColumn;
    /**
     * 排序方式，正序还是倒序
     */
    private String orderByString = "desc";
    
    /**
     * 是否有上一页
     */
    private boolean hasUpPage = false;
    
    /**
     * 是否有下一页
     */
    private boolean hasDownPage = false;
    
    /**
     * 跳转URL
     */
    private String linkUrl = "";
    
    /**
     * 每页显示的记录数
     */
    public Integer getShowCount() {
        return showCount;
    }
    
    /**
     * 每页显示的记录数
     */
    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }
    
    /**
     * 获取总页数
     *
     * @return
     */
    public Integer getTotalPage() {
        if (totalResult % showCount == 0) {
            totalPage = totalResult / showCount;
        } else {
            totalPage = totalResult / showCount + 1;
        }
        return totalPage;
    }
    
    /**
     * 总页数
     *
     * @param totalPage
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    
    /**
     * 总记录数
     *
     * @return
     */
    public Integer getTotalResult() {
        return totalResult;
    }
    
    /**
     * 总记录数
     *
     * @param totalResult
     */
    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
        totalPage = getTotalPage();
        
        // 设置是否有上一页或下一页
        this.hasUpPage = getCurrentPage() <= 1 ? false : true;
        this.hasDownPage = getCurrentPage() >= totalPage ? false : true;
    }
    
    /**
     * 获取当前页
     *
     * @return
     */
    public Integer getCurrentPage() {
        if (currentPage <= 0)
            currentPage = 1;
        if (totalPage != null && totalPage > 0) {
            if (currentPage > totalPage) {
                currentPage = totalPage;
            }
        }
        return currentPage;
    }
    
    /**
     * 当前页
     *
     * @param currentPage
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    
    /**
     * 获取当前条数
     *
     * @return
     */
    public Integer getCurrentResult() {
        currentResult = (getCurrentPage() - 1) * showCount;
        if (currentResult < 0) {
            currentResult = 0;
        }
        return currentResult;
    }
    
    /**
     * 当前记录起始索引
     *
     * @param currentResult
     */
    public void setCurrentResult(Integer currentResult) {
        this.currentResult = currentResult;
    }
    
    public Boolean isEntityOrField() {
        return entityOrField;
    }
    
    public void setEntityOrField(Boolean entityOrField) {
        this.entityOrField = entityOrField;
    }
    
    @Override
    public String toString() {
        return "Page [showCount=" + showCount + ", totalPage=" + totalPage
                + ", totalResult=" + totalResult + ", currentPage="
                + currentPage + ", currentResult=" + currentResult
                + ", entityOrField=" + entityOrField + "]";
    }
    
    public String getOrderColumn() {
        return orderColumn;
    }
    
    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }
    
    public String getOrderByString() {
        return orderByString;
    }
    
    public void setOrderByString(String orderByString) {
        this.orderByString = orderByString;
    }
    
    public boolean getHasUpPage() {
        return hasUpPage;
    }
    
    public void setHasUpPage(boolean hasUpPage) {
        this.hasUpPage = hasUpPage;
    }
    
    public boolean getHasDownPage() {
        return hasDownPage;
    }
    
    public void setHasDownPage(boolean hasDownPage) {
        this.hasDownPage = hasDownPage;
    }
    
    /**
     * @return the linkUrl
     */
    public String getLinkUrl() {
        return linkUrl;
    }
    
    /**
     * @param linkUrl the linkUrl to set
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    
    
}
