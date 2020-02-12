/*
 * FileName：KeyWordsUtil.java
 * Description：
 * Copyright: Copyright (c) 2013-2020
 * Company: GOK Technology
 * Author:  zhengjian
 * Version: V100R01C02
 * Time:2015年12月29日 上午10:34:50
 */

package com.sxgokit.rdf.util.keyWordUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class KeyWordsUtil
{

    //编码 UTF-8
    private final static String KEY_WORDS_ENCODING = "UTF-8";
    
    @SuppressWarnings("rawtypes")
    private static HashMap KEY_WORD_MAP;
    
    private static KeyWordsUtil o = null;
    
    public static int minMatchTYpe = 1;      //最小匹配规则
    public static int maxMatchType = 2;      //最大匹配规则
    
    /**
     * 构造函数
     * @throws Exception
     */
    private KeyWordsUtil() throws Exception{
        Set<String> keyWordSet = readKeyWordFile();
        addKeyWordToHashMap(keyWordSet);
    }
    /**
     * 初始化
     * Description: <br>
     * Time：2015年12月29日 上午11:33:58<br>
     * @author zhengjian
     * @throws Exception 
     */
    public static synchronized KeyWordsUtil getEntity() throws Exception{
        if(o == null){
            try
            {
                o = new KeyWordsUtil();
            }
            catch (Exception e)
            {
                throw new Exception("create KeyWordsUtil entity error");
            }
        }
        return o;
    }
    
    /**
     * 构建DFA算法关键词模型
     * Description: <br>
     * Time：2015年12月29日 上午11:50:58<br>
     * @author zhengjian
     * @param keyWordSet
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void addKeyWordToHashMap(Set<String> keyWordSet) {
        KEY_WORD_MAP = new HashMap(keyWordSet.size());
        String key = null;  
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next(); 
            nowMap = KEY_WORD_MAP;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i); 
                Object wordMap = nowMap.get(keyChar);
                
                if(wordMap != null){
                    nowMap = (Map) wordMap;
                }
                else{
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0"); 
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }
                
                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");
                }
            }
        }
    }
    
    
    /**
     * 读取关键词库 将关键词放入到set中
     * Description: <br>
     * Time：2015年12月29日 上午11:39:37<br>
     * @author zhengjian
     * @return
     * @throws Exception
     */
    private Set<String> readKeyWordFile() throws Exception{
        Set<String> set = null;
        BufferedReader bufferedReader = null;
        File file = new File(this.getClass().getResource("").getPath()  + "/word/SensitiveWord.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),KEY_WORDS_ENCODING);
        try {
            if(file.isFile() && file.exists()){
                set = new HashSet<String>();
                bufferedReader = new BufferedReader(read);
                String txt = null;
                while((txt = bufferedReader.readLine()) != null){
                    set.add(txt);
                }
            }
            else{
                throw new Exception("敏感词库文件不存在");
            }
        } catch (Exception e) {
            throw e;
        }finally{
            read.close();
        }
        return set;
    }
    
    /**
     * 是否包含关键字
     * Description: <br>
     * Time：2015年12月29日 下午2:09:52<br>
     * @author zhengjian
     * @param txt
     * @param matchType
     * @return
     */
    public boolean isContaintSensitiveWord(String txt,int matchType){
        boolean flag = false;
        for(int i = 0 ; i < txt.length() ; i++){
            int matchFlag = this.CheckSensitiveWord(txt, i, matchType); 
            if(matchFlag > 0){
                flag = true;
            }
        }
        return flag;
    }
    
    /**
     * 获取文字中的敏感词
     * @author chenming 
     * @date 2014年4月20日 下午5:10:52
     * @param txt 文字
     * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
     * @return
     * @version 1.0
     */
    public Set<String> getSensitiveWord(String txt , int matchType){
        Set<String> sensitiveWordList = new HashSet<String>();
        
        for(int i = 0 ; i < txt.length() ; i++){
            int length = CheckSensitiveWord(txt, i, matchType);    //判断是否包含敏感字符
            if(length > 0){    //存在,加入list中
                sensitiveWordList.add(txt.substring(i, i+length));
                i = i + length - 1;    //减1的原因，是因为for会自增
            }
        }
        
        return sensitiveWordList;
    }
    
    /**
     * 替换敏感字字符
     * @author chenming 
     * @date 2014年4月20日 下午5:12:07
     * @param txt
     * @param matchType
     * @param replaceChar 替换字符，默认*
     * @version 1.0
     */
    public String replaceSensitiveWord(String txt,int matchType,String replaceChar){
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);     //获取所有的敏感词
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }
        
        return resultTxt;
    }
    
    /**
     * 获取替换字符串
     * @author chenming 
     * @date 2014年4月20日 下午5:21:19
     * @param replaceChar
     * @param length
     * @return
     * @version 1.0
     */
    private String getReplaceChars(String replaceChar,int length){
        String resultReplace = replaceChar;
        for(int i = 1 ; i < length ; i++){
            resultReplace += replaceChar;
        }
        
        return resultReplace;
    }
    
    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     * @author chenming 
     * @date 2014年4月20日 下午4:31:03
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     * @version 1.0
     */
    @SuppressWarnings({ "rawtypes"})
    public int CheckSensitiveWord(String txt,int beginIndex,int matchType){
        boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0;     //匹配标识数默认为0
        char word = 0;
        Map nowMap = KEY_WORD_MAP;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);     //获取指定key
            if(nowMap != null){     //存在，则判断是否为最后一个
                matchFlag++;     //找到相应key，匹配标识+1 
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    flag = true;       //结束标志位为true   
                    if(KeyWordsUtil.minMatchTYpe == matchType){    //最小规则，直接返回,最大规则还需继续查找
                        break;
                    }
                }
            }
            else{     //不存在，直接返回
                break;
            }
        }
        if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词 
            matchFlag = 0;
        }
        return matchFlag;
    }
    
    
    public static void main(String[] args) throws Exception {
        
       
    }
    
}
