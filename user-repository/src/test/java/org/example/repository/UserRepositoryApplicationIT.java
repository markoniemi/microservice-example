package org.example.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.client.ClientProtocolException;
import org.example.repository.config.ApplicationConfig;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRepositoryApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextHierarchy(@ContextConfiguration(classes = ApplicationConfig.class))
//@ActiveProfiles("local")
@Log4j2
public class UserRepositoryApplicationIT {
    private String url="http://localhost:8082";
    @Autowired
    private Environment environment;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void helloLocal() {
        Assume.assumeFalse(isCloudConfigEnabled());
        String body = get(url + "/hello/test", MediaType.TEXT_PLAIN);
        Assert.assertTrue(body.contains("Hello"));
        Assert.assertTrue(body.contains("localRole"));
    }

    @Test
    public void helloRemote() throws InterruptedException {
        Assume.assumeTrue(isCloudConfigEnabled());
        waitForServiceRegistration();
        String body = get(getUrl() + "/hello/test", MediaType.TEXT_PLAIN);
        Assert.assertTrue(body.contains("Hello"));
        Assert.assertTrue(body.contains("Remote"));
    }

    @Test
    public void discoveryServer() throws InterruptedException {
        Assume.assumeTrue(isCloudConfigEnabled());
        waitForServiceRegistration();
        String body = get("http://localhost:8761/eureka/apps", MediaType.APPLICATION_XML);
        Assert.assertTrue(body.contains("user-repository"));
    }

    @Test
    public void envActuatorLocal() throws ClientProtocolException, IOException {
        Assume.assumeFalse(isCloudConfigEnabled());
        String body = get(url + "/actuator/env", null);
        Assert.assertTrue(body.contains("user.role"));
        Assert.assertTrue(body.contains("localRole"));
    }

    private String get(String url, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        if (mediaType != null) {
            headers.setAccept(Collections.singletonList(mediaType));
        }
        ResponseEntity<String> response = new TestRestTemplate().exchange(url, HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        log.debug(response.getBody());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        return response.getBody();
    }

    private boolean isCloudConfigEnabled() {
        return !Arrays.asList(environment.getActiveProfiles()).contains("local");
    }

    private void waitForServiceRegistration() throws InterruptedException {
        while (discoveryClient.getInstances("user-repository").isEmpty()) {
            Thread.sleep(1000);
            log.info(discoveryClient.getServices());
        }
    }

    private String getUrl() {
        if (isCloudConfigEnabled()) {
            List<ServiceInstance> instances = discoveryClient.getInstances("user-repository");
            if (CollectionUtils.isNotEmpty(instances)) {
                log.info(instances.get(0).getUri());
                return instances.get(0).getUri().toString();
            }
        }
        return null;
    }
}
