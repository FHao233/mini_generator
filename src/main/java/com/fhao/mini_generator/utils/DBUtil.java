package com.fhao.mini_generator.utils;

import com.fhao.mini_generator.bean.GlobalConfiguration;
import com.fhao.mini_generator.bean.TableInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author: FHao
 * create time: 2022-11-21 19:10
 * description:
 */
public class DBUtil {

    /***
     * 获取所有Tables SQL
     */
    private static String getTables() {
        return MessageFormat.format("select table_name from information_schema.tables where table_schema=\"{0}\" and table_type=\"{1}\";",
                GlobalConfiguration.getConfigInfo().getDataBase(), "BASE TABLE");
    }
    /***
     * TableInfo SQL
     * @param tableName tableName
     */
    private static String getTableInfoSql(String tableName) {
        return MessageFormat.format("select column_name,data_type,column_comment,numeric_precision," +
                "numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns " +
                "where table_name = \"{0}\" and table_schema = \"{1}\"", tableName, GlobalConfiguration.getConfigInfo().getDataBase());
    }
    /***
     * 根据指定库获取所有表名
     */
    public static List<String> getTableNames () throws SQLException {
        // result
        List<String> result = new ArrayList<>();

        // sql
        String sql = getTables();

        Statement statement = DBConnectUtil.getConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }
    /***
     * 根据指定库获取单表相关参数
     * @param tableName   表名
     */
    public static TableInfo parseClassInfo(String tableName) {
        return null;
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(getTableNames());
    }
}
