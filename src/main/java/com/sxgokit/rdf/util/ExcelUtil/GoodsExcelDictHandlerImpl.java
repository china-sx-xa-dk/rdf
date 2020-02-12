package com.sxgokit.rdf.util.ExcelUtil;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import com.sxgokit.rdf.model.domain.system.SysDict;
import com.sxgokit.rdf.util.DictUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wuhao
 * @date 2019/6/26 16:33
 */
public class GoodsExcelDictHandlerImpl implements IExcelDictHandler {
    @Override
    public String toName(String dict, Object obj, String name, Object value) {
        if (StringUtils.isNotBlank(dict) && null != value){
            for (SysDict dictO : DictUtils.getDictList(dict)){
                if (dict.equals(dictO.getType()) && (value.toString()).equals(dictO.getValue())){
                    return dictO.getLabel();
                }
            }
        }
        return null;
    }

    @Override
    public String toValue(String dict, Object obj, String name, Object value) {
        if (StringUtils.isNotBlank(dict) && null != value){
            for (SysDict dictO : DictUtils.getDictList(dict)){
                if (dict.equals(dictO.getType()) && (value.toString()).equals(dictO.getLabel())){
                    return dictO.getValue();
                }
            }
        }
        return null;
    }
}
