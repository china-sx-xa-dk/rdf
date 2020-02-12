package com.sxgokit.rdf.config.interf.provide;

import com.sxgokit.rdf.config.interf.CodeMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: ZhangXiao
 * Created: 2017/7/7
 */
abstract class BaseFileCodeMessageSource implements CodeMessageSource {

    final Logger logger = LoggerFactory.getLogger(BaseFileCodeMessageSource.class);

    BaseFileCodeMessageSource() {
    }

    final InputStream handler(String codeMessageFilename) {
        InputStream inputStream = null;
        File file = new File(codeMessageFilename);
        if (file.exists() && file.isFile()) {
            try {
                inputStream = new FileInputStream(file);
                logger.warn("CodeMessage define on filesystem {} file used.", file);
            } catch (FileNotFoundException e) {
                logger.warn("CodeMessage define on filesystem {} file not found.", file);
            }
        } else {
            inputStream = this.processFile(codeMessageFilename);
            if (inputStream != null) {
                logger.info("CodeMessage define on classpath {} file used .", codeMessageFilename);
            } else {
                logger.info("CodeMessage define on classpath {} file not found.", codeMessageFilename);
            }
        }
        return inputStream;
    }

    private InputStream processFile(String filepath) {
        ClassLoader classLoader = getDefaultClassLoader();
        if (classLoader != null) {
            return classLoader.getResourceAsStream(filepath);
        } else {
            return ClassLoader.getSystemResourceAsStream(filepath);
        }
    }

    static List<String> readLines(final InputStream input, final Charset encoding) throws IOException {
        final InputStreamReader reader = new InputStreamReader(input, encoding);
        return readLines(reader);
    }

    private static List<String> readLines(final Reader input) throws IOException {
        final BufferedReader reader = toBufferedReader(input);
        final List<String> list = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            list.add(line);
            line = reader.readLine();
        }
        return list;
    }

    private static BufferedReader toBufferedReader(final Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    private static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = BaseFileCodeMessageSource.class.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }
}
