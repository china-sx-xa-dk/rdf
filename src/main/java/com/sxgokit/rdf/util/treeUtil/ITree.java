package com.sxgokit.rdf.util.treeUtil;

import java.util.List;


public interface ITree
{
    Integer getId();

    Integer getParentId();

    List<ITree> getChildren();

    void setParent(ITree parent);

    ITree getParent();

    Integer getTypeCode();

    void addChild(ITree tree);

    Boolean isChecked();

    Boolean isHasChildren();
}
