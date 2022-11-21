package com.fhao.mini_generator.bean;

import com.fhao.mini_generator.factory.PropertiesFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * author: FHao
 * create time: 2022-11-21 16:05
 * description:
 */
public class GlobalConfiguration {
    private volatile static ConfigInfo CONFIGINFO = null;
    public static ConfigInfo getConfigInfo(){
        if(CONFIGINFO == null){
            synchronized (GlobalConfiguration.class){
                if(CONFIGINFO == null){
                    try {
                        PropertiesFactory.loadProperties();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return CONFIGINFO;
    }

    public static void setCONFIGINFO(ConfigInfo CONFIGINFO) {
        GlobalConfiguration.CONFIGINFO = CONFIGINFO;
    }

    private GlobalConfiguration() {
    }
}
