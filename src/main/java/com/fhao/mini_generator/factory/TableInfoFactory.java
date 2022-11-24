package com.fhao.mini_generator.factory;

import cn.hutool.core.collection.CollectionUtil;
import com.fhao.mini_generator.bean.ConfigInfo;
import com.fhao.mini_generator.bean.GlobalConfiguration;
import com.fhao.mini_generator.bean.TableInfo;
import com.fhao.mini_generator.bean.TableNameAndComment;
import com.fhao.mini_generator.utils.DBUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * author: FHao
 * create time: 2022-11-22 23:08
 * description:
 */
public class TableInfoFactory {

    private volatile static List<TableInfo> TABLE_INFO_LIST = new ArrayList<>();
    public static List<TableInfo> getClassInfoList() {
        if (CollectionUtil.isEmpty(TABLE_INFO_LIST)) {
            synchronized (TableInfoFactory.class) {
                if (CollectionUtil.isEmpty(TABLE_INFO_LIST)) {
                    try {
                        // 获取配置项
                        ConfigInfo config = GlobalConfiguration.getConfigInfo();
                        List<TableNameAndComment> tableNames = DBUtil.getTableNames();
                        for (TableNameAndComment nameAndComment : tableNames) {
                            // 仅加载 *; 配置项 或者 include包含项才进行处理
                            if("*;".equals(config.getInclude()) || config.getIncludeMap().containsKey(nameAndComment.getTableName())) {
                                TableInfo tableInfo = DBUtil.getTablesInfo(nameAndComment);
                                TABLE_INFO_LIST.add(tableInfo);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return TABLE_INFO_LIST;
    }
}
