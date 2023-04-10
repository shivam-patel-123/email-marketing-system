package com.ems.configuration.buisness;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConfigurationFromJSONTest {
    private static  Configuration configuration;

    @BeforeAll
    public static void  init(){
        configuration=ConfigurationFromJSON.getInstance();
    }

    @Test
    public void getEnvironmentFromConfigurationTest(){
        String env=configuration.getEnvironmentFromConfiguration();
        assertNotNull(env);
    }
    @Test
    public void getConfigurationByKey(){
        String value=configuration.getConfigurationByKey("dev","mysqlUrl");
        assertNotNull(value);
    }
}
