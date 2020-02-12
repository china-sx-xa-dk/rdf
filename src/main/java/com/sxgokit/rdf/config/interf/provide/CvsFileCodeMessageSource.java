package com.sxgokit.rdf.config.interf.provide;

import com.sxgokit.rdf.config.entity.Asserts;
import com.sxgokit.rdf.config.entity.CodeMessage;
import com.sxgokit.rdf.config.exception.CodeMessageException;
import com.sxgokit.rdf.config.exception.CodeMessageSourceException;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Example:
 * id=code=message
 * Invalid_Parameter=4000=无效参数
 * Server_Inner_Exception=5000=服务内部错误
 * <p>
 * Author : secondriver
 * Date   : 2016/7/17
 */
public class CvsFileCodeMessageSource extends BaseFileCodeMessageSource {

    private static final String CVS_DEFAULT_NAME = "code_message.cvs";

    private String filename = CVS_DEFAULT_NAME;

    private static final String DEFAULT_SEP = "=";

    @Getter
    @Setter
    private String sep = DEFAULT_SEP;

    public CvsFileCodeMessageSource() {
    }

    public CvsFileCodeMessageSource(String filename) {
        Asserts.notEmpty(filename, "codeMessageFileName can't be null/empty.");
        this.filename = filename;
    }

    @Override
    public List<CodeMessage> loadSource() {
        List<CodeMessage> codeMessages = new ArrayList<>();
        InputStream stream = handler(this.filename);
        if (stream != null) {
            try {
                List<String> items = readLines(stream, StandardCharsets.UTF_8);
                for (String line : items) {
                    String[] item = line.split(this.getSep());
                    if (item.length == 3) {
                        CodeMessage codeMessage = new CodeMessage(item[0].trim(), item[1].trim(), item[2].trim());
                        if (codeMessages.contains(codeMessage)) {
                            throw new CodeMessageException("Load Source codeMessage list can't have" +
                                    " repeat code .");
                        }
                        codeMessages.add(codeMessage);
                    }
                }
                return codeMessages;
            } catch (IOException e) {
                throw new CodeMessageSourceException(e);
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.warn("LoadSource close stream exception .", e);
                }
            }
        }
        return codeMessages;
    }


    @Override
    public int order() {
        return Integer.MAX_VALUE - 2;
    }

}