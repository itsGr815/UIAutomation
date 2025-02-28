package com.dFarm.qa.dFarm.util.datahandler;

import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class YamlData implements HandleTestData{

    private static final Logger logger = LoggerFactory.getLogger(YamlData.class);


    @Override
    public Map<String, Object> readTestData() {
        return readYAML();
    }

    public Map<String, Object> readYAML(){

        Map<String, Object> yamlPasrser;
        Map<String, Object> yamlData = new LinkedHashMap<>();

        try {
            Yaml yaml = new Yaml();
            for (Entry<String, InputStream> is : getAllYANLFile().entrySet()){
                InputStreamReader isr = new InputStreamReader(is.getValue(), StandardCharsets.UTF_8);
                yamlPasrser = (Map<String, Object>) yaml.load(isr);
                yamlData.putAll(yamlPasrser);
            }
        }catch (Exception e){
            logger.error("Unable to Parse Data from Yaml to Map: "+ e);
        }
        System.out.println(yamlData);
        return yamlData;
    }

    public Map<String, InputStream> getAllYANLFile(){
        Map<String, InputStream> filenames = new LinkedHashMap<>();
        Resource[] resources;
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            if (AllDataHolder.getYamlPath().isEmpty() || AllDataHolder.getYamlPath() == null) {
                resources = resolver.getResources("classpath:yamldata/*.yaml");

            } else if (AllDataHolder.getYamlPath().equalsIgnoreCase("NA")) {
                resources = null;
            } else {
                resources = resolver.getResources("classpath:yamldata/" + AllDataHolder.getYamlPath());
            }
            for (Resource resource : resources) {
                filenames.put(resource.getFilename(), resource.getInputStream());
            }
        } catch (Exception e) {
            logger.error("Unable to find Yaml files from folder" + e);
        }
        return filenames;
    }

}
