package com.sxgokit.rdf.config.entity;

import com.sxgokit.rdf.config.CodeMessageManageFactory;
import com.sxgokit.rdf.config.exception.ResponseBeanException;
import com.sxgokit.rdf.model.dto.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于在webservice中进行返回的标准类
 */
public class WebServiceCallBack {

    Logger logger = LoggerFactory.getLogger(WebServiceCallBack.class);

    /**
     * 成功
     */
    public static final ResponseBean succ(){
        return excute("200","");
    }

    /**
     * 成功
     */
    public static final ResponseBean succ(Object data){
        return excute("200", data);
    }

    /**
     * 失败
     */
    public static final ResponseBean fail(){
        return excute("500", null);
    }

    /**
     * 失败
     */
    public static final ResponseBean fail(Object data){
        return excute("500", data);
    }

    /**
     * 按照ResponseCode中的对应关系,通过传入的code自动构建返回值,
     * @param code
     */
    public static final ResponseBean autoBuild(String code){
        return excute(code, "");
    }

    /**
     * 功能描述: 自定义接口常用返回类
     * @param code
     * @param msg
     */
    public static final ResponseBean build(Integer code, String msg){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(code);
        responseBean.setMsg(msg);
        return responseBean;
    }

    /**
     * 功能描述: 自定义接口常用返回类
     * @param code
     * @param msg
     * @param data
     */
    public static final ResponseBean build(Integer code, String msg, Object data){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(code);
        responseBean.setMsg(msg);
        responseBean.setData(data);
        return responseBean;
    }

    /**
     * 构造响应内容
     */
    private static final ResponseBean excute(String code, Object data) {
        ResponseBean responseBean = new ResponseBean();
        try {
            CodeMessage ok = CodeMessageManageFactory.getInstance().codeMessage(code);
            responseBean.setCode(Integer.valueOf(ok.getCode()));
            responseBean.setMsg(ok.getMessage());
            responseBean.setData(data);
        } catch (ResponseBeanException e) {
            responseBean.setCode(Integer.valueOf(e.getCode()));
            responseBean.setMsg(e.getMessage());
        } catch (Exception | Error e) {
            CodeMessage server = CodeMessageManageFactory.getInstance().codeMessage("500");
            responseBean.setCode(Integer.valueOf(server.getCode()));
            responseBean.setMsg(server.getMessage());
        }
        return responseBean;
    }

}
