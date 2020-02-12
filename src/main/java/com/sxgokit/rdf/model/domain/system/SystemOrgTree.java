package com.sxgokit.rdf.model.domain.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2019/4/25.
 */
public class SystemOrgTree {

    /**
     * ID
     */
    private String id;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 是否还有子节点
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

    private boolean checked;


    /**
     * 所有的子节点
     */
    private List<SystemOrgTree> children = new ArrayList<SystemOrgTree>();


    public boolean isLeaf()
    {
        return children.isEmpty();
    }

    public void addChild(SystemOrgTree child)
    {
        children.add(child);
        hasChildren = true;
    }

    public void removeChild(SystemTreeImpl child)
    {
        children.remove(child);
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


    public List<SystemOrgTree> getChildren()
    {
        return children;
    }

    public void setChildren(List<SystemOrgTree> children)
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String toString()
    {
        return "Tree [id=" + id + ", parentId=" + parentId + ", hasChildren="
                + hasChildren + ", name=" + name + ", value=" + value
                + ", checked=" + checked + ", children=" + children + "]";
    }

    public void setParent(SystemTree parent)
    {

    }

    public SystemTree getParent()
    {
        return null;
    }
}
