package org.example.repository;

import org.example.config.IntegrationTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import example.user.UserApplication;
import lombok.extern.log4j.Log4j2;

/**
 * Base class for integration tests, enables running multiple tests
 * with @SpringBootTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextHierarchy(@ContextConfiguration(classes = IntegrationTestConfig.class))
//@ActiveProfiles("local")
@Log4j2
public class AbstractIntegrationTestBase {
    @Test
    public void dummy() {
        Assert.assertTrue(true);
    }
}
