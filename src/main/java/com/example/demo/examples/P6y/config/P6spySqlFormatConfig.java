package com.example.demo.examples.P6y.config;

import com.example.demo.utils.DateUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author: liming522
 * @description:
 * @date: 2021/12/31 2:14 下午
 * @hope: The newly created file will not have a bug
 */
public class P6spySqlFormatConfig implements MessageFormattingStrategy{

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", new Date())
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : "";
    }
}
