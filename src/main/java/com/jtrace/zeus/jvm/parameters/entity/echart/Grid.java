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
public class Grid {
    private String left;
    private String right;
    private String bottom;
    private boolean containLabel;

    public void setLeft(String left) {
        this.left = left;
    }

    public String getLeft() {
        return left;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getRight() {
        return right;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getBottom() {
        return bottom;
    }

    public void setContainLabel(boolean containLabel) {
        this.containLabel = containLabel;
    }
    public boolean getContainLabel() {
        return containLabel;
    }

}