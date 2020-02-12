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


/**
 * 
 * 构建树形结构的工具类
 * 1、root节点搜索
 * 2、树形结构构建
 * 3、将构建好的树形结构序列化成json字符串
 * 
 * @author dengqinqin
 *
 */
public class TreeUtils
{
    //默认ROOT节点ID
    private static final int TREE_ROOT_ID = 0;

    private TreeUtils()
    {}

    /**
     * 搜索给定树的ROOT节点
     * 
     * @param list 树节点组成的List
     * @return
     */
    private static ITree findRoot(List<? extends ITree> list)
    {
        for (int i = 0; i < list.size(); i++ )
        {
            ITree tree = list.get(i);
            if (tree.getParentId() == null
                || tree.getParentId() == TREE_ROOT_ID)
            {
                return tree;
            }
        }
        return null;
    }

    /**
     * 通过给定的root节点和List<Tree>
     * @param root  根节点
     * @param list  所有节点组成的List
     * @return
     */
    private static ITree buildTree(ITree root, List<? extends ITree> list)
    {
        if (root != null)
        {
            int pid = root.getId();
            for (int i = 0; i < list.size(); i++ )
            {
                ITree t = list.get(i);
                if (t.getParentId() != null
                    && t.getParentId().intValue() == pid)
                {
                    if (!(root.getChildren() != null && root.getChildren().contains(
                        t)))
                    {
                        root.addChild(t);
                        t.setParent(root);
                    }
                    buildTree(t, list);
                }
            }
            return root;
        }
        return null;
    }

    /**
     * 构造树形结构
     * @param list 
     * @return
     */
    public static ITree getTree(List<? extends ITree> list)
    {
        ITree root = findRoot(list);
        return buildTree(root, list);
    }

    /**
     * 通过给定的root节点和List<Tree>
     * @param root  根节点
     * @param list  所有节点组成的List
     * @return
     */
    private static ITree buildTree2(ITree root, List<? extends ITree> list)
    {
        int pid = root.getId();
        for (int i = 0; i < list.size(); i++ )
        {
            ITree t = list.get(i);
            if (t.getParentId() != null
                && Math.abs(t.getParentId().intValue()) == Math.abs(pid))
            {
                if (!root.getChildren().contains(t))
                {
                    root.addChild(t);
                    t.setParent(root);
                }
                buildTree2(t, list);
            }
        }
        return root;
    }

    /**
     * 构造树形结构
     * @param list 
     * @return
     */
    public static ITree getTree2(List<? extends ITree> list)
    {
        ITree root = findRoot(list);
        return buildTree2(root, list);
    }

    /**
     * 构造树形结构并且将构造好的树形结构序列化成json字符串
     * @param list 
     * @return
     */
    public static String getTreeJson(List<? extends ITree> list)
    {
        ITree root = findRoot(list);
        ITree tree = buildTree(root, list);
        if (tree != null)
        {
            return GsonUtil.toVersionJson(tree);
        }
        return null;
    }
}
