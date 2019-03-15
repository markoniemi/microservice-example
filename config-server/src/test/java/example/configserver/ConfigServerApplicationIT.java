package example.configserver;

import java.util.Collections;

import javax.annotation.Resource;

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

import example.configserver.ConfigServerApplication;
import example.configserver.config.ApplicationConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigServerApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextHierarchy(@ContextConfiguration(classes = ApplicationConfig.class))
@ActiveProfiles("native")
public class ConfigServerApplicationIT {
    @Resource
    private String url;

	@Test
	public void getConfig() {
        HttpHeaders headers = createHeaders();
        ResponseEntity<String> response = new TestRestTemplate().exchange(url + "/user-repository/default", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Developer"));
        Assert.assertTrue(response.getBody().contains("user.role"));
	}

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}

