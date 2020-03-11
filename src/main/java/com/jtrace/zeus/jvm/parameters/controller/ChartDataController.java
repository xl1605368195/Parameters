package com.jtrace.zeus.jvm.parameters.controller;

import com.alibaba.fastjson.JSONObject;

import com.jtrace.zeus.jvm.parameters.entity.echart.*;
import com.jtrace.zeus.jvm.parameters.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ChartDataController {

    @Autowired
    UserService userService;

    // 每日ip统计
    @GetMapping(path = "/ip/all")
    public String getAllData() {
        try {
            // 曲线名称
            List<String> legendData = Arrays.asList("每日访问量");
            // 数据
            List<Series> seriesList = new ArrayList<>();

            // X轴名称
            List<String> xAxisData = new ArrayList<>();
            List<HostAggregation> all = hostArrgegationService.getAll();
            Iterator<HostAggregation> iterator1 = all.iterator();
            while (iterator1.hasNext()) {
                HostAggregation next = iterator1.next();
                xAxisData.add(next.getCreateTime());
            }
            Map<Integer, List<Integer>> newData = new HashMap<>();
            newData.put(0, new ArrayList<>());
            newData.put(1, new ArrayList<>());
            newData.put(2, new ArrayList<>());
            newData.put(3, new ArrayList<>());
            newData.put(4, new ArrayList<>());
            newData.put(5, new ArrayList<>());
            newData.put(6, new ArrayList<>());
            for (int i1 = 0; i1 < all.size(); i1++) {
                Iterator<HostAggregation> iterator2 = all.iterator();
                while (iterator2.hasNext()) {
                    HostAggregation next = iterator2.next();
                    newData.get(0).add(next.getTotalHostCount());
                    newData.get(1).add(next.getGreyHostCount());
                    newData.get(2).add(next.getRaspHostCount());
                    newData.get(3).add(next.getJavaHostCount());
                    newData.get(4).add(next.getServerHostCount());
                    newData.get(5).add(next.getGreyWebCount());
                    newData.get(6).add(next.getSuccessInstrumentCount());
                }
            }
            for (int i = 0; i < legendData.size(); i++) {
                Series serie = new Series();
                serie.setName(legendData.get(i));
                serie.setType("line");
                serie.setData(newData.get(i));
                seriesList.add(serie);
            }

            List<XAxis> xAxis = new ArrayList<>();
            List<YAxis> yAxis = new ArrayList<>();
            EchartBean echartBean = new EchartBean();
            echartBean.setTitle(new Title("IP数量"));
            echartBean.setTooltip(new Tooltip("axis", new AxisPointer("cross", new Label("#6a7985"))));
            // 曲线名称
            echartBean.setLegend(new Legend(legendData));
            echartBean.setToolbox(new Toolbox(new Feature(new SaveAsImage())));
            echartBean.setGrid(new Grid("3%", "4%", "3%", true));
            xAxis.add(new XAxis("category", false, xAxisData));
            echartBean.setXAxis(xAxis);
            yAxis.add(new YAxis("value"));
            echartBean.setYAxis(yAxis);
            echartBean.setSeries(seriesList);
            String s = JSONObject.toJSONString(echartBean);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}