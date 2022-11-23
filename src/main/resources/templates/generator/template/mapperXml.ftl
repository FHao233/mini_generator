<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${tableInfo.className}Mapper">

    <resultMap id="BaseResultMap" type="${packageName}.entity.${tableInfo.className}" >
    <#if tableInfo.fieldList?exists && tableInfo.fieldList?size gt 0>
    <#list tableInfo.fieldList as fieldItem >
        <result column="${fieldItem.fieldName}" property="${fieldItem.paramName}" />
    </#list>
    </#if>
    </resultMap>

    <sql id="Base_Column_List">
        <#if tableInfo.fieldList?exists && tableInfo.fieldList?size gt 0>
        <#list tableInfo.fieldList as fieldItem >
        `${fieldItem.fieldName}`<#if fieldItem_has_next>,</#if>
        </#list>
        </#if>
    </sql>

    <!-- 插入数据 -->
    <insert id="insert" parameterType="${packageName}.entity.${tableInfo.className}">
        INSERT INTO ${tableInfo.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#if tableInfo.fieldList?exists && tableInfo.fieldList?size gt 0>
            <#list tableInfo.fieldList as fieldItem >
            <#if fieldItem.paramName != "id_" >
            ${r"<if test ='null != "}${fieldItem.paramName}${r"'>"}
                `${fieldItem.fieldName}`<#if fieldItem_has_next>,</#if>
            ${r"</if>"}
            </#if>
            </#list>
            </#if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#if tableInfo.fieldList?exists && tableInfo.fieldList?size gt 0>
            <#list tableInfo.fieldList as fieldItem >
            ${r"<if test ='null != "}${fieldItem.paramName}${r"'>"}
                ${r"#{"}${fieldItem.paramName}${r"}"}<#if fieldItem_has_next>,</#if>
            ${r"</if>"}
            </#list>
            </#if>
        </trim>
    </insert>

    <!-- 批量插入数据 -->
    <insert id="batchInsert" parameterType="java.util.List">
        INSERT INTO ${tableInfo.tableName} ( <include refid="Base_Column_List" /> ) VALUES
        <foreach collection="list" item="curr" index="index" separator=",">
            (
            <#list tableInfo.fieldList as fieldItem >
                ${r"#{"}curr.${fieldItem.paramName}${r"}"}<#if fieldItem_has_next>,</#if>
            </#list>
            )
        </foreach>
    </insert>

    <!-- 更新 -->
    <update id="update" parameterType="${packageName}.entity.${tableInfo.className}">
        UPDATE ${tableInfo.tableName}
        <set>
        <#if tableInfo.fieldList?exists && tableInfo.fieldList?size gt 0>
        <#list tableInfo.fieldList as fieldItem >
        <#if fieldItem.paramName != tableInfo.primaryKey.paramName>
            ${r"<if test ='null != "}${fieldItem.paramName}${r"'>"}`${fieldItem.fieldName}` = ${r"#{"}${fieldItem.paramName}${r"}"}<#if fieldItem_has_next>,</#if>${r"</if>"}
        </#if>
        </#list>
        </#if>
        </set>
        WHERE `${tableInfo.primaryKey.paramName}` = ${r"#{"}${tableInfo.primaryKey.paramName}${r"}"}
    </update>

    <!-- 删除 -->
    <delete id="delete">
        DELETE FROM ${tableInfo.tableName}
        WHERE `${tableInfo.primaryKey.paramName}` = ${r"#{"}key${r"}"}
    </delete>

    <!-- 批量删除 -->
    <delete id="batchDelete" parameterType = "java.util.List">
        DELETE FROM ${tableInfo.tableName} WHERE ${tableInfo.primaryKey.paramName} IN
        <foreach collection="list"  item="item" open="(" separator="," close=")"  >
            ${r"#{"}item${r"}"}
        </foreach>
    </delete>

    <!-- 主键查询 -->
    <select id="selectByKey" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${tableInfo.tableName}
        WHERE `${tableInfo.primaryKey.paramName}` = ${r"#{"}key${r"}"}
    </select>

    <!-- 条件查询 -->
    <select id="selectList" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${tableInfo.tableName}
        <where>
            <#if tableInfo.fieldList?exists && tableInfo.fieldList?size gt 0>
            <#list tableInfo.fieldList as fieldItem >
            ${r"<if test ='null != "}${fieldItem.paramName}${r"'>"}
                and `${fieldItem.paramName}` = ${r"#{"}${fieldItem.paramName}${r"}"}
            ${r"</if>"}
            </#list>
            </#if>
        </where>
    </select>

    <!-- 分页条件查询 -->
    <select id="selectPage" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${tableInfo.tableName}
        <where>
            <#if tableInfo.fieldList?exists && tableInfo.fieldList?size gt 0>
            <#list tableInfo.fieldList as fieldItem>
            ${r"<if test ='null != "}${tableInfo.beanName}.${fieldItem.paramName}${r"'>"}
                and `${fieldItem.paramName}` = ${r"#{"}${tableInfo.beanName}.${fieldItem.paramName}${r"}"}
            ${r"</if>"}
            </#list>
            </#if>
        </where>
        limit ${r"#{"}page,jdbcType=INTEGER${r"}"}, ${r"#{"}pageSize,jdbcType=INTEGER${r"}"}
    </select>

    <!-- 总量查询 -->
    <select id="total" resultType="java.lang.Integer">
        SELECT count(*) FROM ${tableInfo.tableName}
        <where>
        <#if tableInfo.fieldList?exists && tableInfo.fieldList?size gt 0>
        <#list tableInfo.fieldList as fieldItem>
            ${r"<if test ='null != "}${fieldItem.paramName}${r"'>"}
                and `${fieldItem.paramName}` = ${r"#{"}${fieldItem.paramName}${r"}"}
            ${r"</if>"}
        </#list>
        </#if>
        </where>
    </select>
</mapper>