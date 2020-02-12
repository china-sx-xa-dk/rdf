package com.sxgokit.rdf.config.interf.provide;

import com.sxgokit.rdf.config.entity.Asserts;
import com.sxgokit.rdf.config.entity.CodeMessage;
import com.sxgokit.rdf.config.exception.CodeMessageSourceException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: ZhangXiao
 * Created: 2017/7/6
 */
public class XmlCodeMessageSource extends BaseFileCodeMessageSource {

    private static final String XML_DEFAULT_NAME = "code_message.xml";

    private String filename = XML_DEFAULT_NAME;

    public XmlCodeMessageSource() {
    }

    public XmlCodeMessageSource(String filename) {
        Asserts.notNull(filename, "Construct parameter must be not null.");
        this.filename = filename;
    }

    @Override
    public List<CodeMessage> loadSource() {
        List<CodeMessage> codeMessages = new ArrayList<>();
        InputStream stream = handler(filename);
        if (stream != null) {
            codeMessages = parse(stream);
        }
        return codeMessages;
    }


    @Override
    public int order() {
        return Integer.MAX_VALUE;
    }

    private List<CodeMessage> parse(InputStream stream) {
        try {
            JAXBContext context = JAXBContext.newInstance(CodeMessageSet.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CodeMessageSet codeMessageSet = (CodeMessageSet) unmarshaller.unmarshal(stream);
            return codeMessageSet.getCodeMessageList();
        } catch (JAXBException e) {
            throw new CodeMessageSourceException(e);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                logger.warn("LoadSource close stream exception .", e);
            }
        }
    }
}
