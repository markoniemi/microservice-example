package example.configserver;

import java.util.Collections;

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

import example.configserver.config.ApplicationConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigServerApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextHierarchy(@ContextConfiguration(classes = ApplicationConfig.class))
@ActiveProfiles({ "local", "native" })
public class ConfigServerApplicationIT {
    private String url = "http://localhost:8081";

    @Test
    public void getConfig() {
        String body = get(url + "/user-repository/default", MediaType.APPLICATION_JSON);
        Assert.assertTrue(body.contains("Remote"));
        Assert.assertTrue(body.contains("user.role"));
    }

    private String get(String url, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        if (mediaType != null) {
            headers.setAccept(Collections.singletonList(mediaType));
        }
        ResponseEntity<String> response = new TestRestTemplate().exchange(url, HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        return response.getBody();
    }
}
