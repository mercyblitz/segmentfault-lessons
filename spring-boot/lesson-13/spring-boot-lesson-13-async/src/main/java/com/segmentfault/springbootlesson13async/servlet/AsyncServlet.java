package com.segmentfault.springbootlesson13async.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.16
 */
@WebServlet(asyncSupported = true, urlPatterns = "/async")
public class AsyncServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AsyncContext asyncContext = request.startAsync();

        response.setContentType("text/html;charset=UTF-8");

        final ServletContext servletContext = request.getServletContext();

        PrintWriter writer = response.getWriter();

        writer.write(threadInfo(request));

        asyncContext.addListener(new AsyncListener() {

            @Override
            public void onComplete(AsyncEvent event) throws IOException {

                HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();

                PrintWriter writer = response.getWriter();

                writer.write("onComplete : " + threadInfo((HttpServletRequest) event.getSuppliedRequest()));

            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {

                servletContext.log("onTimeout : " + threadInfo((HttpServletRequest) event.getSuppliedRequest()));

            }

            @Override
            public void onError(AsyncEvent event) throws IOException {

                servletContext.log("onError : " + threadInfo((HttpServletRequest) event.getSuppliedRequest()));

            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {

                servletContext.log("onStartAsync : " + threadInfo((HttpServletRequest) event.getSuppliedRequest()));

            }
        });

        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
                try {
                    response.getWriter().write(threadInfo(request));
                    asyncContext.complete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private static String threadInfo(HttpServletRequest request) {
        return "Current Thread [" + Thread.currentThread().getName() + "] on request URI[" + request.getRequestURI() + "] was executed! <br />";
    }

}
