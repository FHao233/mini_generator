package ${packageName}.service.impl;

import ${packageName}.entity.*;
import ${packageName}.common.PageList;
import ${packageName}.mapper.*;
import ${packageName}.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * ${tableInfo.classComment}ServiceImpl
 * @author ${authorName}
 * @date ${.now?string('yyyy/MM/dd')}
 */
@Service
public class ${tableInfo.className}ServiceImpl implements ${tableInfo.className}Service {

    @Autowired
	${tableInfo.className}Mapper ${tableInfo.className?uncap_first}mapper;

    @Override
    public int insert(${tableInfo.className} ${tableInfo.beanName}) {
        return ${tableInfo.className?uncap_first}mapper.insert(${tableInfo.beanName});
    }

    @Override
    public int batchInsert(List<${tableInfo.className}> list) {
    	return ${tableInfo.className?uncap_first}mapper.batchInsert(list);
    }

    @Override
    public int update(${tableInfo.className} ${tableInfo.beanName}) {
    	return ${tableInfo.className?uncap_first}mapper.update(${tableInfo.beanName});
    }

    @Override
    public int delete(Object key) {
    	return ${tableInfo.className?uncap_first}mapper.delete(key);
    }

    @Override
    public int batchDelete(List<Object> keys) {
        return ${tableInfo.className?uncap_first}mapper.batchDelete(keys);
    }

	@Override
	public ${tableInfo.className} selectByKey(Object key) {
		return ${tableInfo.className?uncap_first}mapper.selectByKey(key);
	}

	@Override
	public List<${tableInfo.className}> selectList(${tableInfo.className} ${tableInfo.beanName}) {
		return ${tableInfo.className?uncap_first}mapper.selectList(${tableInfo.beanName});
	}

	@Override
	public PageList<${tableInfo.className}> selectPage(${tableInfo.className} ${tableInfo.beanName}, Integer offset, Integer pageSize) {
		PageList<${tableInfo.className}> pageList = new PageList<>();

		int total = this.total(${tableInfo.beanName});

		Integer totalPage;
		if (total % pageSize != 0) {
			totalPage = (total /pageSize) + 1;
		} else {
			totalPage = total /pageSize;
		}

		int page = (offset - 1) * pageSize;

		List<${tableInfo.className}> list = ${tableInfo.className?uncap_first}mapper.selectPage(${tableInfo.beanName}, page, pageSize);

		pageList.setList(list);
		pageList.setStartPageNo(offset);
		pageList.setPageSize(pageSize);
		pageList.setTotalCount(total);
		pageList.setTotalPageCount(totalPage);
		return pageList;
	}

	@Override
	public int total(${tableInfo.className} ${tableInfo.beanName}) {
		return ${tableInfo.className?uncap_first}mapper.total(${tableInfo.beanName});
	}
}