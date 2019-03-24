package org.example.repository.config;

import javax.annotation.Resource;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.example.repository.user.UserService;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class RestConfig {
    @Resource
    private UserService userService;

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean(destroyMethod = "destroy")
    @DependsOn("cxf")
    public Server jaxRsServer() {
        final JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setServiceBeanObjects(userService);
        factory.setProvider(new JacksonJsonProvider());
        factory.setBus(cxf());
        factory.setAddress("/rest");
        return factory.create();
    }

    @Bean
    public ServletRegistrationBean cxfServlet() {
        final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/api/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
}
