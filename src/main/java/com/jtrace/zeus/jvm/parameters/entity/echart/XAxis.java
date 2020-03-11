/**
 * Copyright 2019 bejson.com
 */
package com.jtrace.zeus.jvm.parameters.entity.echart;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Auto-generated: 2019-08-02 14:0:23
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@AllArgsConstructor
public class XAxis {

    private String type;
    private boolean boundaryGap;
    private List<String> data;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setBoundaryGap(boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }

    public boolean getBoundaryGap() {
        return boundaryGap;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

}