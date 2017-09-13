package com.segmentfault.springbootlesson19;

import org.junit.Test;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletContext;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.13
 */
public class MockServletAPITest {

    /**
     * {@link ServletContext}
     */
    @Test
    public void testMockServletContext() {
        // 不支持 Servlet 3.0 的注册 API
        MockServletContext servletContext = new MockServletContext();

        servletContext.setInitParameter("abc", "def");


    }
}
