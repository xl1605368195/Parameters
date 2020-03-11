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
public class AxisPointer {

    private String type;
    private Label label;
    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setLabel(Label label) {
         this.label = label;
     }
     public Label getLabel() {
         return label;
     }

}