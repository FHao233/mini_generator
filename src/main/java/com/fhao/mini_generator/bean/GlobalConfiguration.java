package com.fhao.mini_generator.bean;

import com.fhao.mini_generator.factory.PropertiesFactory;

/**
 * author: FHao
 * create time: 2022-11-21 16:05
 * description:
 */
public class GlobalConfiguration {
    private volatile static ConfigInfo CONFIGINFO = null;
    public ConfigInfo getConfigInfo(){
        if(CONFIGINFO == null){
            synchronized (GlobalConfiguration.class){
                if(CONFIGINFO == null){
//                    PropertiesFactory.loadProperties();
                }
            }
        }
        return null;
    }
}
