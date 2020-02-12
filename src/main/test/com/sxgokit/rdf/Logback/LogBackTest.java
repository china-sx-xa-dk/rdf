package com.sxgokit.rdf.Logback;

import com.sxgokit.rdf.RdfApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackTest extends RdfApplicationTests {

    private Logger logger = LoggerFactory.getLogger("ERROR_ONLY_FILE");

    @Test
    public void testAll() {
        logger.info("====info");
        logger.warn("====warn");
        logger.error("====error");
    }
}
