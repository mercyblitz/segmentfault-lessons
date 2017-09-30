package com.segmentfault.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.19
 */
@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {


    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        Optional<String> stringOptional = Optional.empty();

        PrintWriter writer = response.getWriter();

        writer.println("线程[" + Thread.currentThread().getName() + "] 异步开始了...");

        //异步开始了
        AsyncContext asyncContext = request.startAsync(request, response);

        asyncContext.addListener(new AsyncListener() {

            @Override
            public void onComplete(AsyncEvent event) throws IOException {

                writer.println("线程[" + Thread.currentThread().getName() + "] 请求完成");

            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {

                System.err.println("发生超时了...");

            }

            @Override
            public void onError(AsyncEvent event) throws IOException {

                System.err.println("发生错误了...");

            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {

            }

        });

        asyncContext.start(new Runnable() {
            @Override
            public void run() {

                writer.println("线程[" + Thread.currentThread().getName() + "] asyncContext.start() 开始了...");

                // 加载数据库
                // loadFromDB(); // 10s
                // 刷新缓存
                // updateCache(); // 20s


            }
        });

        //
        Map<String, Object> data = new HashMap<>();

        data.forEach((key, value) -> {

        });



        String[] values = {};

        Stream.of(values).forEach((value)->{

        });

        asyncContext.complete();

        writer.println("异步结束了！");

    }

}
