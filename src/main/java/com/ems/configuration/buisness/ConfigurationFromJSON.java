package com.ems.configuration.buisness;

import org.json.JSONObject;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class ConfigurationFromJSON extends Configuration {

    private ConfigurationFromJSON(){

        loadConfigurationToMap();
    }
    private static Configuration instance;

    //function gets environment from the configuration  file
    @Override
    public String getEnvironmentFromConfiguration() {
        return env;
    }

    //function returns the value of the key based on the environment requested
    @Override
    public String getConfigurationByKey(String env,String key) {
        return (String)configs.get(env).get(key);
    }

    public static Configuration getInstance()  {
        if (null == instance) {
            instance = new ConfigurationFromJSON();
            return instance;
        }
        return instance;
    }

    private static void loadConfigurationToMap(){
        String fileContent;
        String filePath="./src/main/resources/configuration/configuration.json";
        try  {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)),
                    StandardCharsets.UTF_8);
            JSONObject configurationJSon= new JSONObject(fileContent);
            env=(String)configurationJSon.get("env");
            Map<String,Map<String,Object>> configMap=new HashMap<>();
            configMap.put("prod",getEnvConfigurationJson("prod",configurationJSon));
            configMap.put("test",getEnvConfigurationJson("test",configurationJSon));
            configMap.put("dev",getEnvConfigurationJson("dev",configurationJSon));
            configs=configMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    private  static Map<String,Object> getEnvConfigurationJson(String env,JSONObject configurationJSon){
        JSONObject configurationJSonObject= (JSONObject) configurationJSon.get(env);
        return configurationJSonObject.toMap();
    }
}
