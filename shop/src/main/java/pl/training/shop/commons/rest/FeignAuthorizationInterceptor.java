package pl.training.shop.commons.rest;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class FeignAuthorizationInterceptor implements RequestInterceptor {

    @Value("${api.token}")
    @Setter
    private String token;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(AUTHORIZATION, "Bearer " + token);
    }

}
