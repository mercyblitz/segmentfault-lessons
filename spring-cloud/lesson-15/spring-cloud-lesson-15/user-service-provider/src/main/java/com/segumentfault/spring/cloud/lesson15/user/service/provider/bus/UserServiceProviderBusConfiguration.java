package com.segumentfault.spring.cloud.lesson15.user.service.provider.bus;

import com.segumentfault.spring.cloud.lesson15.event.UserRemoteApplicationEvent;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

/**
 * 用户服务提供方 Bus 配置
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@Configuration
@RemoteApplicationEventScan(basePackageClasses = UserRemoteApplicationEvent.class)
public class UserServiceProviderBusConfiguration {
}
