package example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class DiscoveryIT extends AbstractIntegrationTestBase {
    @Test
    public void discoveryServer() throws InterruptedException {
        assumeTrue(isCloudConfigEnabled());
        waitForServiceRegistration();
        String body = get("http://localhost:8761/eureka/apps", MediaType.APPLICATION_XML);
        assertTrue(body.contains("user-repository"));
    }
}
