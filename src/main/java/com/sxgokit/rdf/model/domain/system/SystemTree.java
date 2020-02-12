package com.sxgokit.rdf.model.domain.system;


import java.util.List;


public interface SystemTree
{
    Integer getId();

    Integer getParentId();

    List<SystemTree> getChildren();

    void setParent(SystemTree parent);

    SystemTree getParent();

    int getTypeCode();

    void addChild(SystemTree tree);

    boolean isChecked();

    boolean isHasChildren();
}
