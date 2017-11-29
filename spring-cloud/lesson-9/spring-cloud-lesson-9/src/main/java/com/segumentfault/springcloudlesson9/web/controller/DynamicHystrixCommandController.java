package com.segumentfault.springcloudlesson9.web.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 动态配置 {@link HystrixCommand} 控制器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 */
@RestController
@RefreshScope
public class DynamicHystrixCommandController {

    /**
     * 随机对象
     */
    private static final Random random = new Random();

    @Autowired
    private Environment environment;

    /**
     * 动态切换 HystrixCommand 属性配置
     */
    @GetMapping("")
    public String index() {
        int timeout = environment.getProperty("dynamic.hystrix.command.timeout", int.class, 100);
        return new DynamicHystrixCommand(timeout).execute();
    }

    private static class DynamicHystrixCommand extends HystrixCommand<String> {

        protected DynamicHystrixCommand(int timeout) {
            super(HystrixCommandGroupKey.Factory.asKey("DynamicHystrixCommand"),
                    HystrixThreadPoolKey.Factory.asKey("DynamicHystrixCommand-ThreadPool-" + timeout),
                    timeout);
        }

        @Override
        protected String run() throws Exception {

            long executeTime = random.nextInt(200);

            // 通过休眠来模拟执行时间
            System.out.println("Execute Time : " + executeTime + " ms");

            Thread.sleep(executeTime);

            return "Hello,World";
        }

        protected String getFallback() {
            return "Fallback";
        }
    }

}
