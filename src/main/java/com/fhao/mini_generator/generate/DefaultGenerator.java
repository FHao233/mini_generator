package com.fhao.mini_generator.generate;

import com.fhao.mini_generator.bean.ConfigInfo;
import com.fhao.mini_generator.bean.GlobalConfiguration;
import com.fhao.mini_generator.bean.TableInfo;
import com.fhao.mini_generator.utils.NameConvertUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class DefaultGenerator extends AbstractGenerate {
    private static final String SPACER = File.separator;
    private static final String JAVA_STRUCT = SPACER + "src" + SPACER + "main" + SPACER + "java" + SPACER;
    private static final String RESOURCE_STRUCT = SPACER + "src" + SPACER + "main" + SPACER + "resources" + SPACER;
    private static final Logger logger = LoggerFactory.getLogger(DefaultGenerator.class);
    private ConfigInfo config;
    private Configuration freemarkerConfig;

    public DefaultGenerator() {
        this.config = GlobalConfiguration.getConfigInfo();
        this.freemarkerConfig = getFreemarkerConfig();
    }

    private void generateClass(TableInfo tableInfo, String templateName, String parentPackage, String classSuffix) {
        String path = config.getPackageName().replace(".", SPACER);
        String filePath = config.getProjectPath() + JAVA_STRUCT + path + SPACER + parentPackage.replace(".", SPACER)
                + SPACER + tableInfo.getClassName() + classSuffix;
        build(tableInfo, templateName, filePath);
    }

    private void generateClass(String className, String templateName, String parentPackage, String classSuffix) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setClassName(className);
        generateClass(tableInfo, templateName, parentPackage, classSuffix);
    }

    /**
     * 类构建方法
     *
     * @param tableInfo
     * @param templateName
     * @param filePath
     */
    private void build(TableInfo tableInfo, String templateName, String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            Writer writer = new FileWriter(filePath);
            Template template = freemarkerConfig.getTemplate(templateName);
            Map<String, Object> params = new HashMap<>();
            params.put("tableInfo", tableInfo);
            params.put("authorName", config.getAuthorName());
            params.put("packageName", config.getPackageName());
            params.put("projectName", config.getProjectName());
            params.put("configInfo", config);
            template.process(params, writer);
            writer.flush();
            writer.close();
            logger.info("{}文件已构建完成,文件路径：{}", tableInfo.getClassName(), filePath);
        } catch (IOException | TemplateException e) {
            logger.error("模板构建错误：", e);
        }
    }

    private void build(String className, String templateName, String filePath) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setClassName(className);
        build(tableInfo, templateName, filePath);
    }

    @Override
    public void generateController(TableInfo tableInfo) {
        generateClass(tableInfo, "generator/template/controller.ftl", "controller", "Controller.java");

    }

    @Override
    public void generateService(TableInfo tableInfo) {
        generateClass(tableInfo, "generator/template/service.ftl", "service", "Service.java");
        generateClass(tableInfo, "generator/template/serviceImp.ftl", "service.impl", "ServiceImpl.java");

    }

    @Override
    public void generateEntity(TableInfo tableInfo) {
        generateClass(tableInfo, "generator/template/entity.ftl", "entity", ".java");

    }

    @Override
    public void generateMapper(TableInfo tableInfo) {
        generateClass(tableInfo, "generator/template/mapper.ftl", "mapper", "Mapper.java");

    }

    @Override
    public void generateMapperXml(TableInfo tableInfo) {
        String rootPath = config.getRootPath() + SPACER + config.getProjectName();
        String filePath = rootPath + RESOURCE_STRUCT + SPACER + "mapper" + SPACER
                + tableInfo.getClassName() + "Mapper.xml";
        build(tableInfo, "generator/template/mapperXml.ftl", filePath);
    }

    @Override
    public void generateConfig() {
        String rootPath = config.getRootPath() + SPACER + config.getProjectName();
        String filePath = rootPath + RESOURCE_STRUCT;
        build("pom", "generator/common/pom.ftl", rootPath + SPACER + "pom.xml");
        build("logback-spring", "generator/common/logback-spring.ftl", filePath + "logback-spring.xml");
        build("application", "generator/common/applicationConfig.ftl", filePath + "generator.properties");
    }

    @Override
    public void generateDefault() {
        generateClass("Result", "generator/common/Result.ftl", "common", ".java");
        generateClass("PageList", "generator/common/PageList.ftl", "common", ".java");
        String projectName = NameConvertUtil.convertToJavaName(config.getProjectName());
        generateClass(projectName + "Application", "generator/common/Application.ftl", "", ".java");
        generateClass("ResultCode", "generator/common/ResultCode.ftl", "common", ".java");

    }
}
