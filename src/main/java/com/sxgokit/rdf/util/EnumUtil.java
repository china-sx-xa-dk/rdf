package com.sxgokit.rdf.util;

import com.sxgokit.rdf.config.entity.DataDictVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举类
 */
public class EnumUtil {

    public static final String return_msg = "对应状态无值,请查看";

    /**
     * 是否：1是 2否
     */
    public enum YES_NO {
        YES(1, "是"),
        NO(2, "否");

        private Integer value;
        private String info;

        YES_NO(Integer value, String info) {
            this.value = value;
            this.info = info;
        }

        public Integer getValue() {
            return this.value;
        }

        public String getInfo() {
            return this.info;
        }

        public static List<DataDictVo> getDictList() {
            List<DataDictVo> dicts = new ArrayList<DataDictVo>();
            for (YES_NO ele : values()) {
                DataDictVo dict = new DataDictVo();
                dict.setKey(ele.getValue());
                dict.setValue(ele.getInfo());
                dicts.add(dict);
            }
            return dicts;
        }

        public static String getInfoByValue(Integer code) {
            for (YES_NO ele : values()) {
                if (ele.getValue().intValue() == (code.intValue())) return ele.getInfo();
            }
            return return_msg;
        }
    }

    /**
     * 性别：1男2女
     */
    public enum UserSex {
        SIR(1, "男"),
        LADY(2, "女");

        private Integer value;
        private String info;

        UserSex(Integer value, String info) {
            this.value = value;
            this.info = info;
        }

        public Integer getValue() {
            return this.value;
        }

        public String getInfo() {
            return this.info;
        }

        public static UserSex getByValue(int value) {
            for (UserSex t : UserSex.values()) {
                if (t.getValue() == value) {
                    return t;
                }
            }
            return null;
        }

        public static List<DataDictVo> getDictList() {
            List<DataDictVo> dicts = new ArrayList<DataDictVo>();
            for (UserSex ele : values()) {
                DataDictVo dict = new DataDictVo();
                dict.setKey(ele.getValue());
                dict.setValue(ele.getInfo());
                dicts.add(dict);
            }
            return dicts;
        }

        public static String getInfoByValue(Integer code) {
            for (UserSex ele : values()) {
                if (ele.getValue().intValue() == (code.intValue())) return ele.getInfo();
            }
            return return_msg;
        }
    }

    public static void main(String[] args) {
        //获取枚举对应字典列表
//        EnumUtil.SubmitUserType.getDictList().forEach(x -> System.out.println(x));
        //通过value获取对应汉字释义
//        System.out.println(EnumUtil.SubmitUserType.getInfoByValue(1));
//        EnumUtil.WorkOrderModel.getDictList().forEach(System.out::println);
    }
}
