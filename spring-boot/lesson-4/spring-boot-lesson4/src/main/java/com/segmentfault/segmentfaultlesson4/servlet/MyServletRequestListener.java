package com.segmentfault.segmentfaultlesson4.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;


@WebListener
public class MyServletRequestListener implements ServletRequestListener {


    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        ServletContext servletContext = request.getServletContext();

        servletContext.log("request was initialized!");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        ServletContext servletContext = request.getServletContext();

        servletContext.log("request was destroyed!");


    }
}
