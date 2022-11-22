package com.fhao.mini_generator.utils;

import com.fhao.mini_generator.bean.FieldInfo;
import com.fhao.mini_generator.bean.GlobalConfiguration;
import com.fhao.mini_generator.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: FHao
 * create time: 2022-11-21 19:10
 * description:
 */
public class DBUtil {
    /***
     * 获取所有Tables SQL
     */
    private static String SQL_SHOW_TABLE_STATUS = "SHOW TABLE STATUS;";

    private static Map<String, String> TYPE_MAPPING = new HashMap<>();

    private static Logger logger = LoggerFactory.getLogger(DBUtil.class);

    static {
        TYPE_MAPPING.put("int", "Integer");
        TYPE_MAPPING.put("char", "String");
        TYPE_MAPPING.put("varchar", "String");
        TYPE_MAPPING.put("datetime", "Date");
        TYPE_MAPPING.put("timestamp", "Date");
        TYPE_MAPPING.put("bit", "Integer");
        TYPE_MAPPING.put("tinyint", "Integer");
        TYPE_MAPPING.put("smallint", "Integer");
        TYPE_MAPPING.put("year", "Date");
        TYPE_MAPPING.put("date", "Date");
        TYPE_MAPPING.put("bigint", "Long");
        TYPE_MAPPING.put("decimal", "BigDecimal");
        TYPE_MAPPING.put("double", "Double");
        TYPE_MAPPING.put("float", "Float");
        TYPE_MAPPING.put("numeric", "Integer");
        TYPE_MAPPING.put("text", "String");
        TYPE_MAPPING.put("mediumtext", "String");
        TYPE_MAPPING.put("longtext", "String");
        TYPE_MAPPING.put("time", "Date");
    }

    /***
     * TableInfo SQL
     * @param tableName tableName
     */
    private static String getTableInfoSql(String tableName) {
        return MessageFormat.format("select column_name,data_type,column_comment,numeric_precision," +
                "numeric_scale,character_maximum_length,is_nullable nullable,extra,column_key from information_schema.columns " +
                "where table_name = \"{0}\" and table_schema = \"{1}\"", tableName, GlobalConfiguration.getConfigInfo().getDataBase());
    }

    /***
     * 根据指定库获取单表相关参数
     * @param
     */
    public static List<TableInfo> getTablesInfo() throws SQLException {
        Statement statement = DBConnectUtil.getConnection().createStatement();
        ResultSet tableResult = statement.executeQuery(SQL_SHOW_TABLE_STATUS);
        List<TableInfo> tableInfoList = new ArrayList<>();
        while (tableResult.next()) {

            String tableName = tableResult.getString(1);
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableName(tableName);
            String tableInfoSql = getTableInfoSql(tableName);
            if (GlobalConfiguration.getConfigInfo().isIgnoreDbPrefix()) {
                tableName = tableName.substring(tableName.indexOf("_") + 1);
            }
            String className = NameConvertUtil.convertToJavaName(tableName);
            tableInfo.setClassName(className);
            tableInfo.setBeanName(NameConvertUtil.lowerCaseAtFirst(className));
            tableInfo.setClassComment(tableResult.getString("Comment"));
            List<FieldInfo> fieldList = getFieldsInfo(tableInfoSql);
            tableInfo.setFieldList(fieldList);
            for (FieldInfo fieldInfo : fieldList) {
                if (fieldInfo.isPrimaryKey()) {
                    tableInfo.setPrimaryKey(fieldInfo);
                    break;
                }
            }
            tableInfoList.add(tableInfo);
        }
        logger.info("数据库信息:{}",JsonUtils.convertObj2Json(tableInfoList));
        return tableInfoList;
    }

    private static List<FieldInfo> getFieldsInfo(String tableInfoSql) throws SQLException {
        List<FieldInfo> fieldList = new ArrayList<>();
        Statement statement = DBConnectUtil.getConnection().createStatement();
        ResultSet fieldResult = statement.executeQuery(tableInfoSql);
        while (fieldResult.next()) {
            FieldInfo fieldInfo = new FieldInfo();
            String fieldName = fieldResult.getString(1);
            fieldInfo.setFieldName(fieldName);
            fieldInfo.setJavaType(TYPE_MAPPING.get(fieldResult.getString(2)));
            String paraName = NameConvertUtil.convertToJavaName(fieldName);
            fieldInfo.setParamName(NameConvertUtil.lowerCaseAtFirst(paraName));
            fieldInfo.setSqlType(fieldResult.getString(2));
            fieldInfo.setFieldComment(fieldResult.getString(3));
            fieldInfo.setNullAble(fieldResult.getString(7));
            fieldInfo.setAutoIncrement(fieldResult.getString(8).equalsIgnoreCase("auto_increment"));
            fieldInfo.setPrimaryKey(fieldResult.getString(9).equalsIgnoreCase("PRI"));
            fieldList.add(fieldInfo);
        }
        fieldResult.close();
        statement.close();
        return fieldList;
    }

    public static void main(String[] args) throws SQLException {
//        for (TableInfo tableInfo : getTablesInfo()) {
//            System.out.println(tableInfo);
//        }
        getTablesInfo();
    }
}
