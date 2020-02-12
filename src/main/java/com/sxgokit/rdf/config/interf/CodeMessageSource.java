package com.sxgokit.rdf.config.interf;

import com.sxgokit.rdf.config.entity.CodeMessage;

import java.util.List;

/**
 * 编码和信息的源加载接口
 * <p>
 * Author : secondriver
 * Date   : 2016/7/17
 */
public interface CodeMessageSource {

    /**
     * 加载CodeMessage源
     *
     * @return
     */
    List<CodeMessage> loadSource();

    /**
     * CodeMessageSource源的读取顺序，order()值越大越优先读取，较大值的源覆盖较小源中的同code的message
     * <p>
     * 比如: Source1的order()=10有'code_a'='Hello' Source2的order()=11有'code_11'='Hello World'
     * 那么实际运行中'code_a'='Hello World'
     *
     * @return
     */
    int order();

}