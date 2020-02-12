package com.sxgokit.rdf.config;

import com.sxgokit.rdf.config.entity.Asserts;
import com.sxgokit.rdf.config.entity.CodeMessage;
import com.sxgokit.rdf.config.entity.CodeMessageSourceFactory;
import com.sxgokit.rdf.config.exception.CodeMessageException;
import com.sxgokit.rdf.config.interf.CodeMessageSource;
import com.sxgokit.rdf.config.interf.provide.JsonCodeMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * CodeMessage管理工厂类，进行注册CodeMessageSource和根据code获取message
 * Author : secondriver
 * Date   : 2016/7/17
 */
public final class CodeMessageManageFactory {

    private final ConcurrentMap<String, CodeMessageSource> registerSources = new ConcurrentHashMap<>();

    private final Set<CodeMessage> codeMessageSet = new HashSet<>();

    private final Logger logger = LoggerFactory.getLogger(CodeMessageManageFactory.class);

    private CodeMessageManageFactory() {
    }

    /**
     * 注册CodeMessageSource源
     *
     * @param sourceName 注册名
     * @param source     CodeMessageSource源实现
     */
    public synchronized void register(String sourceName, CodeMessageSource source) {
        Asserts.notNull(sourceName, "sourceName can't be null.");
        Asserts.notNull(source, "CodeMessageSource can't be null.");
        if (!registerSources.containsKey(sourceName)) {
            registerSources.put(sourceName, source);
            logger.info("Register code message source name={} type={} .", sourceName, source.getClass().getName());
        } else {
            throw new CodeMessageException("Repeat register CodeMessageSource implements " +
                    "instance " + sourceName + " source already exists.");
        }
        rebuildUseSources();
    }

    private synchronized void rebuildUseSources() {
        logger.info("## Rebuild code message sources start ##.");
        synchronized (codeMessageSet) {
            codeMessageSet.clear();
            Collection<CodeMessageSource> sources = registerSources.values();
            List<CodeMessageSource> list = new ArrayList<>(sources.size());
            list.addAll(sources);
            Collections.sort(list, new Comparator<CodeMessageSource>() {

                @Override
                public int compare(CodeMessageSource o1, CodeMessageSource o2) {
                    return o1.order() > o2.order() ? -1 : o1.order() < o2.order() ? 1 : 0;
                }
            });
            for (CodeMessageSource source : list) {
                for (CodeMessage cm : source.loadSource()) {
                    if (!codeMessageSet.contains(cm)) {
                        codeMessageSet.add(cm);
                    }
                }
            }
        }
        logger.info("## Rebuild code message sources end ##.");
    }

    /**
     * 根据CodeMessage的code获取Message
     *
     * @param code
     * @return
     */
    public String message(String code) {
        return codeMessage(code).getMessage();
    }

    public CodeMessage codeMessage(String code) {
        for (CodeMessage codeMessage : codeMessageSet) {
            if (codeMessage.getCode().equals(code)) {
                return codeMessage;
            }
        }
        throw new CodeMessageException("CodeMessage Code=" + code + " not exist.");
    }


    public Collection<CodeMessage> allCodeMessage() {
        return new ArrayList<>(codeMessageSet);
    }

    public static CodeMessageManageFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {

        private static final CodeMessageManageFactory INSTANCE = new CodeMessageManageFactory();

        static {
            INSTANCE.register("_JSON_CM_SOURCE", new JsonCodeMessageSource());
            INSTANCE.register("_CONSTANT_CM_SOURCE", CodeMessageSourceFactory.create());
        }
    }
}