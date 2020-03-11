/**
 * Copyright 2019 bejson.com
 */
package com.jtrace.zeus.jvm.parameters.entity.echart;

import java.util.List;

/**
 * Auto-generated: 2019-08-02 14:0:23
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Series {

    private String name;
    private String type;
    private List<Integer> data;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public List<Integer> getData() {
        return data;
    }

}