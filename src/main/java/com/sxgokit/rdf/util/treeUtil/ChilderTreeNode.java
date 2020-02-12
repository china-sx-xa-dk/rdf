/*
 * FileName：ChilderTreeNode.java
 * Description：
 * Copyright: Copyright (c) 2013-2020
 * Company: GOK Technology
 * Author:  zhengjian
 * Version: V100R01C02
 * Time:2015年7月13日 下午4:06:37
 */

package com.sxgokit.rdf.util.treeUtil;

/**
 * 
 * 子节点
 * 〈功能详细描述〉
 * @author zhengjian
 * @version V100R01C02
 * @see ChilderTreeNode
 */
public class ChilderTreeNode
{

    private int childerId;
    
    private int parentId;
    
    private String childerName;
    
    private String childerImgURL;
    
    private int childerSortNum;

    public int getChilderId()
    {
        return childerId;
    }

    public void setChilderId(int childerId)
    {
        this.childerId = childerId;
    }

    public String getChilderName()
    {
        return childerName;
    }

    public void setChilderName(String childerName)
    {
        this.childerName = childerName;
    }

    public String getChilderImgURL()
    {
        return childerImgURL;
    }

    public void setChilderImgURL(String childerImgURL)
    {
        this.childerImgURL = childerImgURL;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public int getChilderSortNum()
    {
        return childerSortNum;
    }

    public void setChilderSortNum(int childerSortNum)
    {
        this.childerSortNum = childerSortNum;
    }
}
