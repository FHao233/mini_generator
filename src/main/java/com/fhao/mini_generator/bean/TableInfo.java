package com.fhao.mini_generator.bean;

import lombok.Data;

import java.util.List;

@Data
public class TableInfo {
    /***
     * 表名
     */
    private String tableName;
    /**
     * class类名
     */
    private String className;
    /**
     * bean名称
     */
    private String beanName;
    /**
     * bean参数名称
     */
    private String beanParamName;
    /**
     * 表注释
     */
    private String classComment;

    private List<FieldInfo> fieldList;
}
