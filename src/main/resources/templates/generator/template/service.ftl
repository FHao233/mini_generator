package ${packageName}.service;

import java.util.Map;
import java.util.List;
import ${packageName}.entity.*;
import ${packageName}.common.PageList;

/**
 * 业务层
 * ${tableInfo.classComment}Service
 * @author ${authorName}
 * @date ${.now?string('yyyy/MM/dd')}
 */
public interface ${tableInfo.className}Service {

    /**
     * [新增]
     **/
    int insert(${tableInfo.className} ${tableInfo.beanName});

    /**
     * [批量新增]
     **/
    int batchInsert(List<${tableInfo.className}> list);

    /**
     * [更新]
     **/
    int update(${tableInfo.className} ${tableInfo.beanName});

    /**
     * [删除]
     **/
    int delete(Object key);

    /**
     * [批量删除]
     **/
    int batchDelete(List<Object> keys);

    /**
     * [主键查询]
     **/
    ${tableInfo.className} selectByKey(Object key);

    /**
     * [条件查询]
     **/
    List<${tableInfo.className}> selectList (${tableInfo.className} ${tableInfo.beanName});

    /**
     * [分页条件查询]
     **/
    PageList<${tableInfo.className}> selectPage (${tableInfo.className} ${tableInfo.beanName}, Integer page, Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(${tableInfo.className} ${tableInfo.beanName});
}
