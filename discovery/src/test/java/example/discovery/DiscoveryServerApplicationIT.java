package example.discovery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import example.discovery.config.ApplicationConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DiscoveryServerApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextHierarchy(@ContextConfiguration(classes = ApplicationConfig.class))
public class DiscoveryServerApplicationIT {
    private String url = "http://localhost:8761";

    @Test
    public void getConfig() {
        String body = get(url + "/eureka/apps", MediaType.APPLICATION_XML);
        System.out.println(body);
        assertTrue(body.contains("applications"));
    }

    private String get(String url, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        if (mediaType != null) {
            headers.setAccept(Collections.singletonList(mediaType));
        }
        ResponseEntity<String> response = new TestRestTemplate().exchange(url, HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        return response.getBody();
    }
}
