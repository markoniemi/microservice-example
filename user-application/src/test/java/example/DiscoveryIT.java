package example;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DiscoveryIT extends AbstractIntegrationTestBase {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void discoveryServer() throws InterruptedException {
        Assume.assumeTrue(isCloudConfigEnabled());
        waitForServiceRegistration();
        String body = get("http://localhost:8761/eureka/apps", MediaType.APPLICATION_XML);
        Assert.assertTrue(body.contains("user-repository"));
    }

    @SuppressWarnings("squid:S2925")
    protected void waitForServiceRegistration() throws InterruptedException {
        while (discoveryClient.getInstances("user-repository").isEmpty()) {
            Thread.sleep(1000);
            log.info(discoveryClient.getServices());
        }
    }
}
