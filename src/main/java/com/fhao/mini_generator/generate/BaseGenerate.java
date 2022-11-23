package com.fhao.mini_generator.generate;

import com.fhao.mini_generator.bean.TableInfo;

/**
 * author: FHao
 * create time: 2022-11-21 18:49
 * description:
 */
public interface BaseGenerate {
    /**
     * 执行器
     */
    void execute();
    /**
     * 生成控制层
     */
    void generateController(TableInfo tableInfo);
    /**
     * 生成业务层(
     */
    void generateService(TableInfo tableInfo);
    /**
     * 生成实体
     */
    void generateEntity(TableInfo tableInfo);
    /**
     * 生成持久层类
     */
    void generateMapper(TableInfo tableInfo);
    /**
     * 生成持久层xml
     */
    void generateMapperXml(TableInfo tableInfo);
    /**
     * 生成配置文件
     */
    void generateConfig();
    /**
     * 生成工具类等
     */
    void generateDefault();

}
