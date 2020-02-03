package com.jtrace.zeus.jvm.parameters.rules.parameter.heap;

import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author xule05
 * @date 2020/2/2 下午7:53
 */
public class HeapParametersCheck {


    /**
     * jdk 版本
     * jdk7，jdk8，jdk9，jdk10
     */
    private String jdkVersion;

    /**
     * 物理内存
     */
    private int totalMem;

    /**
     * 堆的最大值:-Xmx2g
     */
    private int xmx;

    /**
     * 堆的最小值:-Xms2g
     */
    private int xms;

    /**
     * 堆的最大值:-XX:MaxHeapSize=2g
     */
    private int maxHeapSize;

    /**
     * 堆的最小值:-XX:initialHeapSize=2g
     */
    private int initialHeapSize;

    /**
     * 元空间的最大值
     */
    private int maxMetaspaceSize;

    /**
     * 元空间的最小值
     */
    private int metaspaceSize;

    /**
     * heapdumppath
     */
    private String heapDumpPath;

    /**
     * 是否开启 heapDumpOnOutOfMemory
     */
    private String heapDumpOnOutOfMemoryError;

    /**
     * 强制JVM 在启动时分配内存
     */
    private String alwaysPreTouch;

    public HeapParametersCheck(HashMap<String, String> parametersValuesMap, String version, int totalMem) {
        this.jdkVersion = version;
        this.totalMem = totalMem;
    }

    public List<CheckoutResult> checkout() {
        return null;
    }

    private String getMaxHeapSize(HashMap<String, String> parametersValuesMap) {
        // -Xmx 或者 -XX:MaxHeapSize
        String maxHeapSize1 = parametersValuesMap.get("-Xmx");
        String maxHeapSize2 = parametersValuesMap.get("MaxHeapSize");
        // 没有发现 -Xmx 或者 -XX:MaxHeapSize
        return "";
    }


}
