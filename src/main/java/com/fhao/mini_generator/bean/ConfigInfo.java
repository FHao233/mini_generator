package com.fhao.mini_generator.bean;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

/**
 * author: FHao
 * create time: 2022-11-21 16:06
 * description:
 */
@Data
public class ConfigInfo {
    /**
     * 数据库连接地址
     */
    private String url;
    /**
     * 驱动
     */
    private String driver;

    /**
     * 数据库名
     */
    private String dataBase;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /***
     * 需要处理的表名 以 ; 划分
     */
    private String include;

    /***
     * 需要处理的表名Map
     */
    private Map<String, String> includeMap;

    /***
     * 需要处理的自定义Handle名 以 ; 划分
     */
    private String customHandleInclude;

    /***
     * 需要处理的自定义Handle名
     */
    private Map<String, String> customHandleIncludeMap;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 作者名
     */
    private String authorName;

    /**
     * 文件根目录
     */
    private String rootPath;

    /**
     * 项目根目录
     */
    private String projectPath;

    /**
     * 忽略数据库前缀
     */
    private boolean ignoreDbPrefix;


}
