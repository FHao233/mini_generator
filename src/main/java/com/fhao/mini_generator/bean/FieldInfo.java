package com.fhao.mini_generator.bean;

import lombok.Data;

@Data
public class FieldInfo {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * bean属性名
     */
    private String paramName;
    /**
     * sql类型
     */
    private String sqlType;
    /**
     * java类型
     */
    private String javaType;
    /**
     * 所属类
     */
    private String fieldClass;
    /**
     * 字段注释
     */
    private String fieldComment;
    /**
     * 是否自增
     */
    private boolean isAutoIncrement;
}
