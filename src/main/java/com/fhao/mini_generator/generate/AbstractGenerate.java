package com.fhao.mini_generator.generate;

import com.fhao.mini_generator.bean.GlobalConfiguration;
import com.fhao.mini_generator.bean.TableInfo;
import com.fhao.mini_generator.factory.TableInfoFactory;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

public abstract class AbstractGenerate implements BaseGenerate{
    private static Logger logger = LoggerFactory.getLogger(AbstractGenerate.class);
    private static Configuration freemarkerConfig;
    public static Configuration getFreemarkerConfig(){
        if(freemarkerConfig == null){
            freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
            freemarkerConfig.setTemplateLoader(new ClassTemplateLoader(AbstractGenerate.class,"/templates/"));
            freemarkerConfig.setNumberFormat("#");
            freemarkerConfig.setClassicCompatible(true);//设置空值处理
            freemarkerConfig.setDefaultEncoding("UTF-8");
            freemarkerConfig.setLocale(Locale.CHINA);
            freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        }
        return freemarkerConfig;
    }
    public static AbstractGenerate init() {
        return new DefaultGenerator();
    }
    @Override
    public void execute() {
        List<TableInfo> tableInfos = TableInfoFactory.getClassInfoList();
        for (TableInfo tableInfo : tableInfos) {
            generateDefault();
            generateEntity(tableInfo);
            generateController(tableInfo);
            generateService(tableInfo);
            generateMapper(tableInfo);
            generateMapperXml(tableInfo);
            generateConfig();
        }
        logger.info(GlobalConfiguration.getConfigInfo().getProjectName() + " 构建完成.");
    }


}
