package example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.client.ServiceInstance;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConfigIT extends AbstractIntegrationTestBase {
    private String url = "http://localhost:8083";

    @Test
    public void localConfig() throws ClientProtocolException, IOException {
        assumeFalse(isCloudConfigEnabled());
        String body = get(url + "/actuator/env", null);
        assertTrue(body.contains("runtime.environment"));
        assertTrue(body.contains("local"));
    }
    @Test
    public void remoteConfig() throws ClientProtocolException, IOException {
        assumeTrue(isCloudConfigEnabled());
        String body = get(url + "/actuator/env", null);
        assertTrue(body.contains("runtime.environment"));
        assertTrue(body.contains("remote"));
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
