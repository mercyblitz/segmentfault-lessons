package com.segmentfault.springbootlesson16.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.26
 */
@RestController
public class LogbackController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/logback")
    public String log(@RequestParam String message) {

        String log = "Logback : " + message;

        logger.info(log);

        return log;
    }

}
