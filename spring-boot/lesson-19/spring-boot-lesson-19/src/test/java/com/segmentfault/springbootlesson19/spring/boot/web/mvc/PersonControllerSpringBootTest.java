package com.segmentfault.springbootlesson19.spring.boot.web.mvc;

import com.segmentfault.springbootlesson19.configuration.PersonConfiguration;
import com.segmentfault.springbootlesson19.controller.PersonController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.13
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@Import(PersonConfiguration.class)
public class PersonControllerSpringBootTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void testPerson() throws Exception {

        mvc.perform(get("/").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());

    }

}
