package com.jtrace.zeus.jvm.parameters.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xule05
 * @date 2020/3/12 上午12:34
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupByDate {
    private Date day;
    private int count;

    public String getDay() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(day);
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getCount() {
        return count;
    }
}
