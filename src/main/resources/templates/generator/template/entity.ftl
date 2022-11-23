package ${packageName}.entity;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * ${tableInfo.classComment}
 * @author ${authorName} ${.now?string('yyyy-MM-dd')}
 */
@Data
public class ${tableInfo.className} implements Serializable {

    private static final long serialVersionUID = 1L;
<#if tableInfo.fieldList?exists && tableInfo.fieldList?size gt 0>
<#list tableInfo.fieldList as fieldItem >

    /**
     * ${fieldItem.fieldName}  ${fieldItem.fieldComment}
     */
    private ${fieldItem.javaType} ${fieldItem.paramName};
</#list>
</#if>
}