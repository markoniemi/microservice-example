package org.example.repository;

import java.util.Arrays;
import java.util.Collections;

import javax.annotation.Resource;

import org.example.repository.config.ApplicationConfig;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
    @Resource
    private String url;
    @Autowired
    Environment environment;

    private boolean isCloudConfigEnabled() {
        return !Arrays.asList(environment.getActiveProfiles()).contains("local");
    }

    @Test
    public void helloLocal() {
        Assume.assumeFalse(isCloudConfigEnabled());
        HttpHeaders headers = createHeaders();
        ResponseEntity<String> response = new TestRestTemplate().exchange(url + "/hello/test", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        log.info("response: " + response.getBody());
        Assert.assertTrue(response.getBody().contains("Hello"));
        Assert.assertTrue(response.getBody().contains("localRole"));
    }
    @Test
    public void helloRemote() {
        Assume.assumeTrue(isCloudConfigEnabled());
        HttpHeaders headers = createHeaders();
        ResponseEntity<String> response = new TestRestTemplate().exchange(url + "/hello/test", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        log.info("response: " + response.getBody());
        Assert.assertTrue(response.getBody().contains("Hello"));
        Assert.assertTrue(response.getBody().contains("Developer"));
    }

    @Test
    public void discoveryServer() throws InterruptedException {
        Assume.assumeTrue(isCloudConfigEnabled());
        Thread.sleep(5000);
        HttpHeaders headers = createHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        log.debug("test discoveryServer");
        ResponseEntity<String> response = new TestRestTemplate().exchange(
                "http://localhost:8081/config-server/eureka/apps", HttpMethod.GET, new HttpEntity<>(headers),
                String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("user-repository"));
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        return headers;
    }
}
