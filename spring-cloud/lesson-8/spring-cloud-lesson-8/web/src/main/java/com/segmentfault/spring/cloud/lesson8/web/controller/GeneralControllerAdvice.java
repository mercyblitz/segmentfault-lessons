package com.segmentfault.spring.cloud.lesson8.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 */
@ControllerAdvice
public class GeneralControllerAdvice {

    /**
     * 处理 404
     *
     * @param throwable
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public String handle404(Throwable throwable) {
        return "Page was not found!";
    }
}
