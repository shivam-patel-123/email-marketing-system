package com.ems.configuration.buisness;

import java.util.Map;

public abstract class Configuration {
    protected static Map<String,Map<String,Object>> configs;
    protected static String env;
    public abstract String  getEnvironmentFromConfiguration();
    public abstract String getConfigurationByKey(String env,String key);
}
