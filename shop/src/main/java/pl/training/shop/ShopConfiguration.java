package pl.training.shop;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.commons.FastMoneyMapper;
import pl.training.shop.commons.streotype.RestAdapter;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.OAS_30;

@Configuration
public class ShopConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(OAS_30)
                .select()
                .apis(withClassAnnotation(RestAdapter.class))
                .build();
    }

    @Bean
    public FastMoneyMapper fastMoneyMapper() {
        return Mappers.getMapper(FastMoneyMapper.class);
    }

}
