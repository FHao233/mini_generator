package ${packageName}.common;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Api响应类
 * ApiResult.java
 * @author ${authorName}
 * @date ${.now?string('yyyy/MM/dd')}
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 数据
     */
    private T data;

    /**
     * 信息
     */
    private String msg;

    /***
     * 时间
     */
    private String time;

    public Result(Integer code, T data, String msg) {
    this.code = code;
    this.data = data;
    this.msg = msg;
    this.time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}