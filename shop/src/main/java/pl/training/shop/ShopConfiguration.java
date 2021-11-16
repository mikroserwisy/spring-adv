package pl.training.shop;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.training.shop.commons.FastMoneyMapper;
import pl.training.shop.commons.streotype.RestAdapter;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.OAS_30;

@EnableTransactionManagement
@EnableAspectJAutoProxy
@Configuration
class ShopConfiguration {

    @Bean
    Docket docket() {
        return new Docket(OAS_30)
                .select()
                .apis(withClassAnnotation(RestAdapter.class))
                .build();
    }

    @Bean
    FastMoneyMapper fastMoneyMapper() {
        return Mappers.getMapper(FastMoneyMapper.class);
    }

}
