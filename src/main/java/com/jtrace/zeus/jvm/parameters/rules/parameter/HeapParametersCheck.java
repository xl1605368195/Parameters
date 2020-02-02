package com.jtrace.zeus.jvm.parameters.rules.parameter;

import java.util.HashMap;

/**
 * @author xule05
 * @date 2020/2/2 下午7:53
 */
public class HeapParametersCheck {


    /**
     *   jdk 版本
     *   jdk7，jdk8，jdk9，jdk10
     */
    private String jdkVersion;

    /**
     * 物理内存
     */
    private int maxMem;

    /**
     *   堆参数的设置方式
     *   方式1：-Xmx2g
     *   方式2：-XX:MaxHeapSize=2g
      */
    private String heapModel;

    /**
     * 堆的最大值
     */
    private int maxHeapSize;

    /**
     * 堆的最小值
     */
    private int minHeapSize;

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

    public HeapParametersCheck(HashMap<String, String> parametersValuesMap, String version, int totalMem){

    }
}
