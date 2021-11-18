package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
class PaymentsConfiguration {

    private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://localhost:8080";
    private static final String CLIENT_EJB_CONTEXT = "jboss.naming.client.ejb.context";
    private static final PaymentsFactory PAYMENTS_FACTORY = new DefaultPaymentsFactory();

    @Bean
    ProcessPaymentUseCase processPaymentUseCase(TimeProvider timeProvider, PaymentsWriter paymentsWriter) {
        return PAYMENTS_FACTORY.create(timeProvider, paymentsWriter);
    }

    @Bean
    GetPaymentUseCase getPaymentUseCase(PaymentsReader paymentsReader) {
        return PAYMENTS_FACTORY.create(paymentsReader);
    }

    @Bean
    TimeProvider timeProvider(JndiTemplate jndiTemplate) throws NamingException {
        return  jndiTemplate.lookup("java:/jee-1.0-SNAPSHOT/SystemTimeProvider!pl.training.shop.payments.TimeProvider", TimeProvider.class);
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
