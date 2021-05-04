package com.bookvault.bookvault;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = BookvaultApplicationTests.class)
class BookvaultApplicationTests {

    @Test
    void contextLoads() {
    }

}
