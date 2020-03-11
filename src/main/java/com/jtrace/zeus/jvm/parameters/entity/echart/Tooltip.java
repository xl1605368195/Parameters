/**
 * Copyright 2019 bejson.com
 */
package com.jtrace.zeus.jvm.parameters.entity.echart;

import lombok.AllArgsConstructor;

/**
 * Auto-generated: 2019-08-02 14:0:23
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@AllArgsConstructor
public class Tooltip {

    private String trigger;
    private AxisPointer axisPointer;

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setAxisPointer(AxisPointer axisPointer) {
        this.axisPointer = axisPointer;
    }

    public AxisPointer getAxisPointer() {
        return axisPointer;
    }

}