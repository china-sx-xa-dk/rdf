/*
 * FileName：ParentTreeNode.java
 * Description：
 * Copyright: Copyright (c) 2013-2020
 * Company: GOK Technology
 * Author:  zhengjian
 * Version: V100R01C02
 * Time:2015年7月13日 下午4:06:18
 */

package com.sxgokit.rdf.util.treeUtil;

import java.util.List;

/**
 * 
 * 父节点
 * 〈功能详细描述〉
 * @author zhengjian
 * @version V100R01C02
 * @see ParentTreeNode
 */
public class ParentTreeNode
{
    private int id;
    
    private String name;
    
    private String imgURL;
    
    private int sortNum;
    
    private List<ChilderTreeNode> childerTreeNodeList;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImgURL()
    {
        return imgURL;
    }

    public void setImgURL(String imgURL)
    {
        this.imgURL = imgURL;
    }

    public List<ChilderTreeNode> getChilderTreeNodeList()
    {
        return childerTreeNodeList;
    }

    public void setChilderTreeNodeList(List<ChilderTreeNode> childerTreeNodeList)
    {
        this.childerTreeNodeList = childerTreeNodeList;
    }

    public int getSortNum()
    {
        return sortNum;
    }

    public void setSortNum(int sortNum)
    {
        this.sortNum = sortNum;
    }
    
}
