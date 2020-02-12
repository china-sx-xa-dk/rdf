package com.sxgokit.rdf.util.idUtil;

import com.sxgokit.rdf.config.component.SnowflakeIdWorker;
import com.sxgokit.rdf.util.dateUtil.DateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 获取ID工具类
 */
public class IdUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private static final SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();


    /**
     * 获取时间开头的23位ID字符串
     * return String yyyyMMddHHmmss_UUID(8位)
     */
    public static String getDateUUID() {
        String dateStr = DateUtil.getDateString(new Date(), "yyyyMMddHHmmss");
        StringBuffer shortBuffer = new StringBuffer(dateStr);
        shortBuffer.append("_");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


    /**
     * 生成主键字符串（订单号，产品号，库存记录号等）
     *
     * @return
     */
    public static String getPrimaryKey() {
        return snowflakeIdWorker.nextId().getStringId();
    }

    /**
     * 生成账户名
     *
     * @return
     * @see 1442923176937528
     */
    public static synchronized String getMemberLoginName() {
        return String.valueOf(System.currentTimeMillis()) + new Random().nextInt(10000);
    }

    /**
     * 生成8位数字密码
     *
     * @return
     */
    public static synchronized String getMemberLoginPass() {
        return String.valueOf(new Random().nextInt(100000000));
    }
}