package com.sxgokit.rdf.model.domain.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述: 组织结构
 * @auther: DuKang
 * @date: 2019/5/22/0022 下午 14:58
 */
@Data
public class SysOrganization extends Model<SysOrganization> implements Serializable {


    /**
     * 组织机构id
     */
    private Integer orgId;

    /**
     * 组织机构名称
     */
    private String orgName;

    /**
     * 父节点为空的是根机构
     */
    private Integer parentId;

    /**
     * 父级id串
     */
    private String parentIds;

    private SysOrganization parent; // 父级机构

    private Integer userId;

    @JsonIgnore
    public static void sortList(List<SysOrganization> list, List<SysOrganization> sourcelist,
                                Integer parentId, boolean cascade)
    {
        for (int i = 0; i < sourcelist.size(); i++ )
        {
            SysOrganization e = sourcelist.get(i);
            if (e.getParentId() != null
                    && e.getParentId().equals(parentId))
            {
                list.add(e);
                if (cascade)
                {
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j = 0; j < sourcelist.size(); j++ )
                    {
                        SysOrganization child = sourcelist.get(j);
                        if (child.getParentId() != null
                                && child.getParentId().equals(e.getOrgId()))
                        {
                            sortList(list, sourcelist, e.getOrgId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }
}
