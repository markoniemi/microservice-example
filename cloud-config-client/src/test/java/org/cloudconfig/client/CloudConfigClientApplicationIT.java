package org.cloudconfig.client;

import java.util.Collections;

import javax.annotation.Resource;

import org.cloudconfig.client.config.ApplicationConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
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

@RunWith(SpringRunner.class)
 @SpringBootTest(classes = CloudConfigClientApplication.class, webEnvironment
 = WebEnvironment.DEFINED_PORT)
 @ContextHierarchy(@ContextConfiguration(classes = ApplicationConfig.class))
 @ActiveProfiles("local")
public class CloudConfigClientApplicationIT {
    @Resource
    private String url;
//    private String url="http://localhost:8082/cloud-config-client/hello/test";

//    @Before
//    public void before() {
//        SpringApplicationBuilder server = new SpringApplicationBuilder(CloudConfigServerApplication.class)
//                .properties("server.port=8081", "spring.cloud.config.server.native.search-locations=classpath:config");
//        server.run();
//        SpringApplicationBuilder client = new SpringApplicationBuilder(CloudConfigClientApplication.class)
//                .profiles("remote").properties("server.port=8081");
//        client.run();
//    }

    @Test
    public void hello() {
        HttpHeaders headers = createHeaders();
        ResponseEntity<String> response = new TestRestTemplate().exchange(url + "/hello/test", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Hello"));
        Assert.assertTrue(response.getBody().contains("test"));
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        return headers;
    }
}
