package pl.training.shop;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import pl.training.shop.commons.FastMoneyMapper;
import pl.training.shop.commons.rest.RestTemplateAuthorizationInterceptor;
import pl.training.shop.commons.streotype.RestAdapter;
import springfox.documentation.spring.web.plugins.Docket;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Properties;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.OAS_30;

@EnableFeignClients
@EnableTransactionManagement
@EnableAspectJAutoProxy
@Configuration
class ShopConfiguration {

    private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://localhost:8080";
    private static final String CLIENT_EJB_CONTEXT = "jboss.naming.client.ejb.context";

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

    @Bean
    RestTemplate restTemplate(RestTemplateAuthorizationInterceptor authorizationInterceptor) {
        return new RestTemplateBuilder()
                .additionalInterceptors(authorizationInterceptor)
                .build();
    }

    @Bean
    ConnectionFactory jndiConnectionFactory(JndiTemplate jndiTemplate) throws NamingException {
        return jndiTemplate.lookup("jms/RemoteConnectionFactory", ConnectionFactory.class);
    }

    @Bean
    Topic trainingTopic(JndiTemplate jndiTemplate) throws NamingException {
        return jndiTemplate.lookup("jms/topic/Training", Topic.class);
    }

    @Bean
    Queue mailQueue(JndiTemplate jndiTemplate) throws NamingException {
        return jndiTemplate.lookup("jms/queue/Mail", Queue.class);
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory jndiConnectionFactory) {
        var cachingConnectionFactory = new CachingConnectionFactory(jndiConnectionFactory);
        return new JmsTemplate(cachingConnectionFactory);
    }

   /* @Bean
    DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(ConnectionFactory jndiConnectionFactory) {
        var factoryBean = new DefaultJmsListenerContainerFactory();
        factoryBean.setConnectionFactory(jndiConnectionFactory);
        factoryBean.setConcurrency("5-10");
        return factoryBean;
    }*/

    @Bean
    JmsListenerContainerFactory<?> topicContainerFactory(ConnectionFactory jndiConnectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        var container = new DefaultJmsListenerContainerFactory();
        configurer.configure(container, jndiConnectionFactory);
        container.setPubSubDomain(true);
        container.setConcurrency("5-10");
        return container;
    }

    @Bean
    JmsListenerContainerFactory<?> queueContainerFactory(ConnectionFactory jndiConnectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        var container = new DefaultJmsListenerContainerFactory();
        configurer.configure(container, jndiConnectionFactory);
        container.setPubSubDomain(false);
        container.setConcurrency("5-10");
        return container;
    }

    @Bean
    JndiTemplate jndiTemplate(Properties jndiProperties) {
        return new JndiTemplate(jndiProperties);
    }

    @Bean
    Properties jndiProperties() {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        properties.put(Context.PROVIDER_URL, PROVIDER_URL);
        properties.put(CLIENT_EJB_CONTEXT, true);
        return properties;
    }

}
