package com.sxgokit.rdf.config.interf.provide;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.sxgokit.rdf.config.entity.Asserts;
import com.sxgokit.rdf.config.entity.CodeMessage;
import com.sxgokit.rdf.config.exception.CodeMessageSourceException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Example:
 * [
 * {
 * "id": "Invalid_Parameter",
 * "code": "4000",
 * "message": "无效参数"
 * },
 * {
 * "id": "Server_Inner_Exception",
 * "code": "5000",
 * "message": "服务内部错误"
 * }
 * ]
 * <p>
 * Author: ZhangXiao
 * Created: 2017/7/6
 */
public class JsonCodeMessageSource extends BaseFileCodeMessageSource {

    private static final String JSON_DEFAULT_NAME = "code_message.json";

    private String filename = JSON_DEFAULT_NAME;

    private Gson gson = new Gson();

    public JsonCodeMessageSource() {
    }

    public JsonCodeMessageSource(String filename) {
        Asserts.notNull(filename, "Construct parameter must be not null.");
        this.filename = filename;
    }

    @Override
    public List<CodeMessage> loadSource() {
        List<CodeMessage> codeMessages = new ArrayList<>();
        InputStream stream = handler(filename);
        if (stream != null) {
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8); JsonReader jsonReader = new JsonReader
                    (reader)) {
                codeMessages = gson.fromJson(jsonReader, new TypeToken<List<CodeMessage>>() {
                }.getType());
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
        return Integer.MAX_VALUE - 1;
    }

}
