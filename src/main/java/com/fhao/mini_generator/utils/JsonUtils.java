package com.fhao.mini_generator.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * author: FHao
 * create time: 2022-11-22 17:24
 * description:
 */
public class JsonUtils {
    public static String convertObj2Json(Object o){
        if(o == null){
            return null;
        }
        return JSON.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect);
    }
}
