package com.fhao.mini_generator.utils;

import com.fhao.mini_generator.bean.GlobalConfiguration;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * author: FHao
 * create time: 2022-11-22 14:59
 * description:
 */
public class NameConvertUtil {
    private final static String SQL_KEY_WORDS = "ADD;ALL;ALTER;ANALYZE;AND;AS;ASC;ASENSITIVE;BEFORE;BETWEEN;BIGINT;BINARY;BLOB;BOTH;BY;CALL;CASCADE;CASE;CHANGE;CHAR;CHARACTER;CHECK;COLLATE;COLUMN;CONDITION;CONNECTION;CONSTRAINT;CONTINUE;CONVERT;CREATE;CROSS;CURRENT_DATE;CURRENT_TIME;CURRENT_TIMESTAMP;CURRENT_USER;CURSOR;DATABASE;DATABASES;DAY_HOUR;DAY_MICROSECOND;DAY_MINUTE;DAY_SECOND;DEC;DECIMAL;DECLARE;DEFAULT;DELAYED;DELETE;DESC;DESCRIBE;DETERMINISTIC;DISTINCT;DISTINCTROW;DIV;DOUBLE;DROP;DUAL;EACH;ELSE;ELSEIF;ENCLOSED;ESCAPED;EXISTS;EXIT;EXPLAIN;FALSE;FETCH;FLOAT;FLOAT4;FLOAT8;FOR;FORCE;FOREIGN;FROM;FULLTEXT;GOTO;GRANT;GROUP;HAVING;HIGH_PRIORITY;HOUR_MICROSECOND;HOUR_MINUTE;HOUR_SECOND;IF;IGNORE;IN;INDEX;INFILE;INNER;INOUT;INSENSITIVE;INSERT;INT;INT1;INT2;INT3;INT4;INT8;INTEGER;INTERVAL;INTO;IS;ITERATE;JOIN;KEY;KEYS;KILL;LABEL;LEADING;LEAVE;LEFT;LIKE;LIMIT;LINEAR;LINES;LOAD;LOCALTIME;LOCALTIMESTAMP;LOCK;LONG;LONGBLOB;LONGTEXT;LOOP;LOW_PRIORITY;MATCH;MEDIUMBLOB;MEDIUMINT;MEDIUMTEXT;MIDDLEINT;MINUTE_MICROSECOND;MINUTE_SECOND;MOD;MODIFIES;NATURAL;NOT;NO_WRITE_TO_BINLOG;NULL;NUMERIC;ON;OPTIMIZE;OPTION;OPTIONALLY;OR;ORDER;OUT;OUTER;OUTFILE;PRECISION;PRIMARY;PROCEDURE;PURGE;RAID0;RANGE;READ;READS;REAL;REFERENCES;REGEXP;RELEASE;RENAME;REPEAT;REPLACE;REQUIRE;RESTRICT;RETURN;REVOKE;RIGHT;RLIKE;SCHEMA;SCHEMAS;SECOND_MICROSECOND;SELECT;SENSITIVE;SEPARATOR;SET;SHOW;SMALLINT;SPATIAL;SPECIFIC;SQL;SQLEXCEPTION;SQLSTATE;SQLWARNING;SQL_BIG_RESULT;SQL_CALC_FOUND_ROWS;SQL_SMALL_RESULT;SSL;STARTING;STRAIGHT_JOIN;TABLE;TERMINATED;THEN;TINYBLOB;TINYINT;TINYTEXT;TO;TRAILING;TRIGGER;TRUE;UNDO;UNION;UNIQUE;UNLOCK;UNSIGNED;UPDATE;USAGE;USE;USING;UTC_DATE;UTC_TIME;UTC_TIMESTAMP;VALUES;VARBINARY;VARCHAR;VARCHARACTER;VARYING;WHEN;WHERE;WHILE;WITH;WRITE;X509;XOR;YEAR_MONTH;ZEROFILL";
    private final static Map<String, String> SQL_KEY_MAP = new HashMap<>(256);

    private static void wordsToMap() {
        String[] keyWords = SQL_KEY_WORDS.split(";");
        for (String keyWord : keyWords) {
            SQL_KEY_MAP.put(keyWord.toUpperCase(), keyWord);
        }
    }

    public static String lowerCaseAtFirst(String str) {
        return (str != null && str.length() > 1) ? str.substring(0, 1).toLowerCase() + str.substring(1) : "";
    }

    public static String convertToJavaName(String name) {
        if (SQL_KEY_MAP.size() == 0) {
            synchronized (NameConvertUtil.class) {
                if (SQL_KEY_MAP.size() == 0) {
                    wordsToMap();
                }
            }
        }
        if (StringUtils.isBlank(name)) {
            return name;
        }

        String[] split = name.split("_");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        String javaName = sb.toString();
        if (SQL_KEY_MAP.containsKey(javaName.toUpperCase())) {
            return javaName + "key";
        }
        return javaName;
    }

    public static void main(String[] args) {
        System.out.println(convertToJavaName("db_ums_detail"));
    }
}
