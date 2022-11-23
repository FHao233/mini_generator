package ${packageName}.mapper;

import ${packageName}.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper层接口
 * ${tableInfo.classComment}Mapper
 * @author ${authorName}
 * @date ${.now?string('yyyy/MM/dd')}
 */
@Mapper
public interface ${tableInfo.className}Mapper {

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
    int batchDelete(List<Object> list);

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
    List<${tableInfo.className}> selectPage (@Param("${tableInfo.beanName}") ${tableInfo.className} ${tableInfo.beanName}, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(${tableInfo.className} ${tableInfo.beanName});
}
