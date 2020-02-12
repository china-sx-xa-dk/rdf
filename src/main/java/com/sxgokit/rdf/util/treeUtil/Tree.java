/*
 * FileName：Node.java
 * Description：
 * Copyright: Copyright (c) 2013-2020
 * Company: GOK Technology
 * Author: wangqi
 * Version: V100R01C02
 * Time:2014年6月27日 下午4:24:02
 */

package com.sxgokit.rdf.util.treeUtil;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Tree
{
    /**
     * ID
     */
    private Integer id;

    /**
     * 父节点ID
     */
    @SerializedName("pid")
    private Integer parentId;

    /**
     * 是否为叶子节点
     */
    private boolean hasChildren;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点值
     */
    private String value;

    /**
     * 是否选中
     */
    private boolean checked;

    /**
     * 所有的子节点
     */
    private List<Tree> children = new ArrayList<Tree>();

    public Tree()
    {
        super();
    }

    public Tree(Integer id, Integer parentId, String name)
    {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Tree(Integer id, Integer parentId, String name, String value)
    {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.value = value;
    }

    public boolean isLeaf()
    {
        return children.isEmpty();
    }

    public void addChild(Tree child)
    {
        children.add(child);
        hasChildren = true;
    }

    public void removeChild(Tree child)
    {
        children.remove(child);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public List<Tree> getChildren()
    {
        return children;
    }

    public void setChildren(List<Tree> children)
    {
        this.children = children;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public boolean isHasChildren()
    {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren)
    {
        this.hasChildren = hasChildren;
    }

    @Override
    public String toString()
    {
        return "Tree [id=" + id + ", parentId=" + parentId + ", hasChildren=" + hasChildren + ", name=" + name + ", value=" + value + ", checked=" + checked + ", children=" + children + "]";
    }

}
