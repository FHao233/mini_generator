package ${packageName}.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ${packageName}.entity.*;
import ${packageName}.common.Result;
import ${packageName}.common.PageList;
import ${packageName}.common.ResultCode;
import ${packageName}.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 控制层
 * ${tableInfo.classComment}Controller
 * @author ${authorName}
 * @date ${.now?string('yyyy/MM/dd')}
 */
@Controller
@RequestMapping(value = "/${tableInfo.beanName}")
public class ${tableInfo.className}Controller {

    @Autowired
    ${tableInfo.className}Service ${tableInfo.className?uncap_first}service;

    /**
     * 具体字段请根据实际情况处理
     * 参数请求报文:
     *
     * {
     *   "paramOne": 1,
     *   "paramTwo": "xxx"
     * }
     */
    @RequestMapping(value = "/insert")
    @ResponseBody
    public Result insert (@RequestBody ${tableInfo.className} ${tableInfo.beanName}) {
        int affectRows = ${tableInfo.className?uncap_first}service.insert(${tableInfo.beanName});
        return new Result(ResultCode.success.getCode(), affectRows, ResultCode.success.getDescr());
    }

    /**
     * 参数请求报文:
     *
     * [
     *     {
     *       "paramOne": 1,
     *       "paramTwo": "xxx"
     *     },
     *     {
     *       "paramOne": 1,
     *       "paramTwo": "xxx"
     *     }
     * ]
     */
    @RequestMapping(value = "/batchInsert")
    @ResponseBody
    public Result batchInsert (@RequestBody List<${tableInfo.className}> list) {
        int affectRows = ${tableInfo.className?uncap_first}service.batchInsert(list);
        return new Result(ResultCode.success.getCode(), affectRows, ResultCode.success.getDescr());
    }

    /**
     * 参数请求报文:
     *
     * {
     *   "paramOne": 1,
     *   "paramTwo": "xxx"
     * }
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result update (@RequestBody ${tableInfo.className} ${tableInfo.beanName}) {
        int affectRows = ${tableInfo.className?uncap_first}service.update(${tableInfo.beanName});
        return new Result(ResultCode.success.getCode(), affectRows, ResultCode.success.getDescr());
    }

    /**
     * 参数请求报文:
     *
     * {
     *   "key":1
     * }
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result delete (@RequestBody Object key) {
        int affectRows = ${tableInfo.className?uncap_first}service.delete(key);
        return new Result(ResultCode.success.getCode(), affectRows, ResultCode.success.getDescr());
    }

    /**
     * 参数请求报文:
     *
     * [
     *     9,
     *     11
     * ]
     */
    @RequestMapping(value = "/batchDelete")
    @ResponseBody
    public Result batchDelete (@RequestBody List<Object> keys) {
        int affectRows = ${tableInfo.className?uncap_first}service.batchDelete(keys);
        return new Result(ResultCode.success.getCode(), affectRows, ResultCode.success.getDescr());
    }

    /**
     * 参数请求报文:
     *
     * {
     *   "key":1
     * }
     */
    @RequestMapping(value = "/selectByKey")
    @ResponseBody
    public Result selectByKey (@RequestBody Object key) {
        ${tableInfo.className} ${tableInfo.beanName} = ${tableInfo.className?uncap_first}service.selectByKey(key);
        return new Result(ResultCode.success.getCode(), ${tableInfo.beanName}, ResultCode.success.getDescr());
    }

    /***
    * 参数请求报文:
    *
    * {
    *   "paramOne": 1,
    *   "paramTwo": "xxx"
    * }
    */
    @RequestMapping(value = "/selectList")
    @ResponseBody
    public Result selectList (@RequestBody ${tableInfo.className} ${tableInfo.beanName}) {
        List<${tableInfo.className}> result = ${tableInfo.className?uncap_first}service.selectList(${tableInfo.beanName});
        return new Result(ResultCode.success.getCode(), result, ResultCode.success.getDescr());
    }

    /***
     * 参数请求报文:
     *
     * {
     *   "paramOne": 1,
     *   "paramTwo": "xxx"
     * }
     */
    @RequestMapping(value = "/selectPage")
    @ResponseBody
    public Result selectPage (@RequestBody JSONObject object) {
        Integer page     = (Integer) object.getOrDefault("page"    , 1);
        Integer pageSize = (Integer) object.getOrDefault("pageSize", 15);

        // 剔除page, pageSize参数
        object.remove("page");
        object.remove("pageSize");

        ${tableInfo.className} ${tableInfo.beanName} = object.toJavaObject(${tableInfo.className}.class);
        PageList<${tableInfo.className}> pageList = ${tableInfo.className?uncap_first}service.selectPage(${tableInfo.beanName}, page, pageSize);
        return new Result(ResultCode.success.getCode(), pageList, ResultCode.success.getDescr());
    }

    /***
     * 表单查询请求
     * @param searchParams Bean对象JSON字符串
     * @param page         页码
     * @param limit        每页显示数量
     */
    @RequestMapping(value = "/formPage")
    @ResponseBody
    public String formPage (@RequestParam(value = "searchParams", required = false) String  searchParams,
                            @RequestParam(value = "page"        , required = false, defaultValue = "1") Integer page,
                            @RequestParam(value = "limit"       , required = false, defaultValue = "15") Integer limit) {
        ${tableInfo.className} query = new ${tableInfo.className}();
        if (StringUtils.isNotBlank(searchParams)) {
            JSONObject object = JSON.parseObject(searchParams);
            query = object.toJavaObject(${tableInfo.className}.class);
        }

        PageList<${tableInfo.className}> pageList = ${tableInfo.className?uncap_first}service.selectPage(query, page, limit);
        JSONObject response = new JSONObject();
        response.put("code" , 0);
        response.put("msg"  , "");
        response.put("data" , null != pageList.getList() ? pageList.getList() : new JSONArray());
        response.put("count", pageList.getTotalCount());
        return response.toString();
    }

    /***
     * 表单查询
     */
    @RequestMapping(value = "/formSelectByKey")
    @ResponseBody
    public ${tableInfo.className} formSelectByKey(@RequestParam(value = "key", required = false) String  key) {
        return ${tableInfo.className?uncap_first}service.selectByKey(key);
    }

    /***
     * 表单插入
     * @param params Bean对象JSON字符串
     */
    @RequestMapping(value = "/formInsert")
    @ResponseBody
    public String formInsert(@RequestParam(value = "params", required = false) String  params) {
        ${tableInfo.className} insert = null;
        if (StringUtils.isNotBlank(params)) {
            JSONObject object = JSON.parseObject(params);
            insert = object.toJavaObject(${tableInfo.className}.class);
        }

        int rows = ${tableInfo.className?uncap_first}service.insert(insert);

        JSONObject response = new JSONObject();
        response.put("code" , rows);
        response.put("msg"  , rows > 0 ? "添加成功" : "添加失败");
        return response.toString();
    }

    /***
     * 表单修改
     * @param params Bean对象JSON字符串
     */
    @RequestMapping(value = "/formUpdate")
    @ResponseBody
    public String formUpdate(@RequestParam(value = "params", required = false) String  params) {
        ${tableInfo.className} update = null;
        if (StringUtils.isNotBlank(params)) {
            JSONObject object = JSON.parseObject(params);
            update = object.toJavaObject(${tableInfo.className}.class);
        }

        int rows = ${tableInfo.className?uncap_first}service.update(update);

        JSONObject response = new JSONObject();
        response.put("code" , rows);
        response.put("msg"  , rows > 0 ? "修改成功" : "修改失败");
        return response.toString();
    }

    /***
     * 表单删除
     */
    @RequestMapping(value = "/formDelete")
    @ResponseBody
    public int formDelete(@RequestParam(value = "key", required = false) String  key) {
        return ${tableInfo.className?uncap_first}service.delete(key);
    }
}
