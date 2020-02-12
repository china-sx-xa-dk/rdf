/*
 * FileName：TreeUtil.java
 * Description：
 * Copyright: Copyright (c) 2013-2020
 * Company: GOK Technology
 * Author:  yangmei
 * Version: V100R01C04
 * Time:2014年7月20日 下午5:53:41
 */

package com.sxgokit.rdf.util.treeUtil;


import java.util.List;


public class TreeUtil
{
    private TreeUtil()
    {}

    private static Tree findRoot(List<Tree> list)
    {
        for (int i = 0; i < list.size(); i++ )
        {
            Tree tree = list.get(i);
            if (tree.getParentId() == 0 || "欢迎页".equals(tree.getName()))
            {
                return list.remove(i);
            }
        }

        return null;
    }

    private static Tree buildTree(Tree p, List<Tree> list)
    {
        int pid = p.getId();
        for (int i = 0; i < list.size(); i++ )
        {
            Tree t = list.get(i);
            t.isLeaf();
            if (t.getParentId().intValue() == pid)
            {
                Tree child = buildTree(t, list);
                p.addChild(child);
            }
        }
        return p;
    }

    public static String getTreeJson(List<Tree> list)
    {
        Tree root = findRoot(list);
        Tree tree = buildTree(root, list);
        return GsonUtil.toVersionJson(tree);
    }

}
