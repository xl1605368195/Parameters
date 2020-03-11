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
public class EchartBean {

    private Title title;
    private Tooltip tooltip;
    private Legend legend;
    private Toolbox toolbox;
    private Grid grid;
    private List<XAxis> xAxis;
    private List<YAxis> yAxis;
    private List<Series> series;
    public void setTitle(Title title) {
         this.title = title;
     }
     public Title getTitle() {
         return title;
     }

    public void setTooltip(Tooltip tooltip) {
         this.tooltip = tooltip;
     }
     public Tooltip getTooltip() {
         return tooltip;
     }

    public void setLegend(Legend legend) {
         this.legend = legend;
     }
     public Legend getLegend() {
         return legend;
     }

    public void setToolbox(Toolbox toolbox) {
         this.toolbox = toolbox;
     }
     public Toolbox getToolbox() {
         return toolbox;
     }

    public void setGrid(Grid grid) {
         this.grid = grid;
     }
     public Grid getGrid() {
         return grid;
     }

    public void setXAxis(List<XAxis> xAxis) {
         this.xAxis = xAxis;
     }
     public List<XAxis> getXAxis() {
         return xAxis;
     }

    public void setYAxis(List<YAxis> yAxis) {
         this.yAxis = yAxis;
     }
     public List<YAxis> getYAxis() {
         return yAxis;
     }

    public void setSeries(List<Series> series) {
         this.series = series;
     }
     public List<Series> getSeries() {
         return series;
     }

}