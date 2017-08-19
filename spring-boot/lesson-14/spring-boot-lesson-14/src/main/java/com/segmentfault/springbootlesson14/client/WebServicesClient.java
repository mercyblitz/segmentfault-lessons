package com.segmentfault.springbootlesson14.client;

import com.segmentfault.springbootlesson14.domain.User;
import com.segmentfault.springbootlesson14.domain.UserIdRequest;
import com.segmentfault.springbootlesson14.domain.UserResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.Instant;

/**
 * Web Services 客户端（Spring 实现）
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.19
 */
public class WebServicesClient {

    public static void main(String[] args) {

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

        jaxb2Marshaller.setClassesToBeBound(UserIdRequest.class, UserResponse.class, User.class);

        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);

        //构造 SOAP 请求
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1L);
        userIdRequest.setTimestamp(Instant.now().toEpochMilli());

        UserResponse userResponse = (UserResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8080/web-services/user", userIdRequest);

        System.out.println(userResponse);

    }

}
