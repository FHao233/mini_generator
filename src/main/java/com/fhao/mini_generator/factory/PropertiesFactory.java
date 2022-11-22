package com.fhao.mini_generator.factory;

import com.fhao.mini_generator.bean.ConfigInfo;
import com.fhao.mini_generator.bean.GlobalConfiguration;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


/**
 * author: FHao
 * create time: 2022-11-21 16:26
 * description:
 */
public class PropertiesFactory {
    private static Properties props = new Properties();
    private static Map<String, String> PROPER_MAP = new ConcurrentHashMap<>();

    public static void loadProperties() throws IOException, InvocationTargetException, IllegalAccessException {
        InputStream inputStream;
        inputStream = PropertiesFactory.class.getClassLoader().getResourceAsStream("application.properties");
        props.load(inputStream);
        for (Object o : props.keySet()) {
            String key = (String) o;
            PROPER_MAP.put(key, props.getProperty(key));
        }
        ConfigInfo configInfo = new ConfigInfo();
        BeanUtils.populate(configInfo, PROPER_MAP);
        configInfo.setIncludeMap(parseInclude(configInfo.getInclude()));
        configInfo.setCustomHandleIncludeMap(parseInclude(configInfo.getCustomHandleInclude()));
        configInfo.setIgnoreDbPrefix(PROPER_MAP.get("ignore.db.prefix").equals("1"));
        String projectPath = configInfo.getRootPath() + File.separator + configInfo.getProjectName();
        configInfo.setProjectPath(projectPath);
        GlobalConfiguration.setCONFIGINFO(configInfo);
        logger.info("属性加载完成, 属性: " + configInfo);
    }

    private static Map<String, String> parseInclude(String include) {
        Map<String, String> result = new HashMap<>();
        if (StringUtils.isBlank(include)) {
            return result;
        }
        String[] strings = include.split(";");
        for (String key : strings) {
            result.put(key, key);
        }
        return result;
    }

    private static Logger logger = LoggerFactory.getLogger(PropertiesFactory.class);

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        loadProperties();
        System.out.println(PROPER_MAP.get("ignore.db.prefix"));
    }
}
