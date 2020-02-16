package com.jtrace.zeus.jvm.parameters.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author xule05
 * @date 2020/1/27 下午8:34
 */
public class Utils {

    public static ArrayList<String> toArrayByFileReader(String name) {
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回数组
        return arrayList;
    }

    public static double getMaxHeapMemory(HashMap<String, String> parametersValuesMap) {
        double maxHeapSize = 0;
        String maxHeapSize1 = parametersValuesMap.get("-Xmx");
        String maxHeapSize2 = parametersValuesMap.get("MaxHeapSize");
        if (StringUtils.isNoneBlank(maxHeapSize1)) {
            maxHeapSize = Units.getGNumber(maxHeapSize1);
        } else if (StringUtils.isNoneBlank(maxHeapSize2)) {
            maxHeapSize = Units.getGNumber(maxHeapSize2);
        }
        return maxHeapSize;
    }
}
