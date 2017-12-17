package com.segumentfault.spring.cloud.lesson14.user.service.client.bus;

import com.segumentfault.spring.cloud.lesson14.domain.User;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 用户{@link RemoteApplicationEvent}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public class UserRemoteApplicationEvent extends RemoteApplicationEvent {

    private UserRemoteApplicationEvent() {
    }

    public UserRemoteApplicationEvent(User user, String originService,
                                      String destinationService) {
        super(user, originService, destinationService);
    }

}
