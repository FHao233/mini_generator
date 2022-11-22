package com.fhao.mini_generator.generate;

import com.fhao.mini_generator.bean.TableInfo;
import com.fhao.mini_generator.factory.TableInfoFactory;

import java.util.List;

public abstract class AbstractGenerate implements BaseGenerate{

    @Override
    public void execute() {
        List<TableInfo> tableInfos = TableInfoFactory.getClassInfoList();
    }

}
