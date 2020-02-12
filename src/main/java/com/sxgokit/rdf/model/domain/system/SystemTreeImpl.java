package com.sxgokit.rdf.model.domain.system;


import java.util.ArrayList;
import java.util.List;


public class SystemTreeImpl implements SystemTree
{
    /**
     * ID
     */
    private Integer id;

    /**
     * 父节点ID
     */
    private Integer parentId;
    
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

    private int typeCode;

    /**
     * 所有的子节点
     */
    private List<SystemTree> children = new ArrayList<SystemTree>();

    public SystemTreeImpl()
    {
        super();
    }

    public SystemTreeImpl(Integer id, Integer parentId, String name)
    {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public SystemTreeImpl(Integer id, Integer parentId, String name, String value)
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

    public void addChild(SystemTree child)
    {
        children.add(child);
        hasChildren = true;
    }

    public void removeChild(SystemTreeImpl child)
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

    public List<SystemTree> getChildren()
    {
        return children;
    }

    public void setChildren(List<SystemTree> children)
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

    public int getTypeCode()
    {
        return typeCode;
    }

    public void setTypeCode(int typeCode)
    {
        this.typeCode = typeCode;
    }

    @Override
    public String toString()
    {
        return "Tree [id=" + id + ", parentId=" + parentId + ", hasChildren="
               + hasChildren + ", name=" + name + ", value=" + value
               + ", checked=" + checked + ", children=" + children + "]";
    }

    @Override
    public void setParent(SystemTree parent)
    {

    }

    @Override
    public SystemTree getParent()
    {
        return null;
    }

}
