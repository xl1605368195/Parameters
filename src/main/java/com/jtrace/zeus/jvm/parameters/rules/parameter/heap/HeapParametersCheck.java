package com.jtrace.zeus.jvm.parameters.rules.parameter.heap;

import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;

import com.jtrace.zeus.jvm.parameters.utils.Units;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author xule05
 * @date 2020/2/2 下午7:53
 */
public class HeapParametersCheck {

    private HashMap<String, String> parametersValuesMap;

    private String jdkVersion;

    private int totalMem;
    /**
     * 0代表-Xmx 简写模式; 1代表MaxHeapSize
     */
    private int maxHeapType;
    private double maxHeap;


    public HeapParametersCheck(HashMap<String, String> parametersValuesMap, int totalMem, String jdkVersion) {
        this.totalMem = totalMem;
        this.jdkVersion = jdkVersion;
        this.parametersValuesMap = parametersValuesMap;

        String maxHeapSize1 = parametersValuesMap.get("-Xmx");
        String maxHeapSize2 = parametersValuesMap.get("MaxHeapSize");
        if (StringUtils.isNoneBlank(maxHeapSize1)) {
            this.maxHeapType = 0;
            this.maxHeap = Units.getGNumber(maxHeapSize1);
        } else if (StringUtils.isNoneBlank(maxHeapSize2)) {
            this.maxHeapType = 1;
            this.maxHeap = Units.getGNumber(maxHeapSize2);
        }
    }

    public void checkout(List<CheckoutResult> list) {
        CheckoutResult checkoutMaxHeap = checkoutMaxHeapSize(parametersValuesMap, totalMem);
        CheckoutResult checkoutMaxMetaSpaceSize;
        if (jdkVersion.equals("jdk7")) {
            checkoutMaxMetaSpaceSize = checkoutMaxPermSpaceSize(parametersValuesMap);
        } else {
            checkoutMaxMetaSpaceSize = checkoutMaxMetaSpaceSize(parametersValuesMap);
        }
        CheckoutResult checkoutHeapDumpPath = checkoutHeapDumpPath(parametersValuesMap);
        CheckoutResult checkoutHeapDumpOnOutOfMemoryError = checkoutHeapDumpOnOutOfMemoryError(parametersValuesMap);
        CheckoutResult checkoutAlwaysPreTouch = checkoutAlwaysPreTouch(parametersValuesMap);
        list.add(checkoutMaxHeap);
        list.add(checkoutMaxMetaSpaceSize);
        list.add(checkoutHeapDumpPath);
        list.add(checkoutHeapDumpOnOutOfMemoryError);
        list.add(checkoutAlwaysPreTouch);
    }


    // 堆震荡检查
//    private CheckoutResult checkMaxAndMin(){
//        if (Math.abs(initialHeapSize - maxHeapSize) > 0.000001) {
//            diagnoseResults.add(new DiagnoseResult("jvmParameter", "heap", SeverityEnum.CRITICAL,
//                    "初始堆大小应和最大堆大小保持一致，避免堆震荡，请在启动参数中使用 -Xmx -Xms 分别指定",
//                    Constant.DEFAULT_IDENTITY, serialNumber));
//        }
//    }

    //最大堆参数检查
    private CheckoutResult checkoutMaxHeapSize(HashMap<String, String> parametersValuesMap, int totalMem) {
        // -Xmx 或者 -XX:MaxHeapSize
        String maxHeapSize1 = parametersValuesMap.get("-Xmx");
        String maxHeapSize2 = parametersValuesMap.get("MaxHeapSize");
        // 没有发现 -Xmx 或者 -XX:MaxHeapSize
        if (StringUtils.isBlank(maxHeapSize1) && StringUtils.isBlank(maxHeapSize2)) {
            return new CheckoutResult(Level.ERROR, "heap", "未设置最大堆内存", "启动参数中使用 -Xmx=xxxg（或者-XX:MaxHeapSize=xxxg)来指定最大堆大小", "推荐将最大堆大小设置为物理内存的50%~80%");
        } else if (StringUtils.isNoneBlank(maxHeapSize1) || StringUtils.isBlank(maxHeapSize2)) {
            // 发现 -Xmx
            return getCheckout(maxHeapSize1, totalMem, "-Xmx");
        } else if (StringUtils.isNoneBlank(maxHeapSize2) || StringUtils.isBlank(maxHeapSize1)) {
            // -XX:MaxHeapSize
            return getCheckout(maxHeapSize2, totalMem, "-XX:MaxHeapSize=");
        } else {
            // 错误配置参数
            return new CheckoutResult(Level.ERROR, "设置了-Xmx,又设置了-XX:MaxHeapSize，重复了", "heap");
        }
    }

    // param=-Xmx 或者 -XX:MaxHeapSize
    private CheckoutResult getCheckout(String maxHeapSizeStr, int totalMem, String param) {
        double maxHeap1 = Units.getGNumber(maxHeapSizeStr);
        if (maxHeap1 < totalMem / 2) {
            return new CheckoutResult(
                    Level.WARN,
                    "heap",
                    "最大堆大小设置小于物理内存1/2",
                    "如果服务运行正常，最大堆大小设置过小",
                    "建议值 " + param + (totalMem / 2) + "g"
            );
        }

        if (maxHeap1 <= totalMem * 4 / 5 && (totalMem - maxHeap1) < 1) {
            return new CheckoutResult(
                    Level.WARN,
                    "heap",
                    "最大堆大小设置过大,堆外内存小于1g",
                    "最大堆大小设置过大，应预留一部分给堆外内存",
                    "建议值 " + param + totalMem * 3 / 4 + "g"
            );
        }

        if (maxHeap1 > totalMem * 4 / 5 && (totalMem - maxHeap1) >= 1) {
            return new CheckoutResult(
                    Level.WARN,
                    "heap",
                    "最大堆大小设置大于物理内存4/5",
                    "最大堆大小设置过大，应预留一部分给堆外内存",
                    "建议值 " + param + totalMem * 3 / 4 + "g"
            );
        }
        return CheckoutResult.ok("最大堆参数设置正确", "heap");
    }

    // MaxMetaSpaceSize
    private CheckoutResult checkoutMaxMetaSpaceSize(HashMap<String, String> parametersValuesMap) {
        String maxMetaspaceSizeStr = parametersValuesMap.get("MaxMetaspaceSize");
        String MaxPermSizeSizeStr = parametersValuesMap.get("MaxPermSize");
        // 在 jdk8中配置 Perm
        if (StringUtils.isNotBlank(MaxPermSizeSizeStr)) {
            return new CheckoutResult(
                    Level.ERROR,
                    "heap",
                    "MaxPermSize在jdk8中已经废弃",
                    "在jdk8中使用-XX:MaxMetaspaceSize=xxx设置元空间大小",
                    ""
            );
        }
        if (StringUtils.isBlank(maxMetaspaceSizeStr)) {
            // 不存在该值
            return new CheckoutResult(
                    Level.ERROR,
                    "heap",
                    "不存在MaxMetaspaceSize参数",
                    "未设置元空间的上限，存在系统内存OOM的风险，请在启动参数中添加 -XX:MaxMetaspaceSize=xxx 设置（过小会导致频繁Full GC）",
                    "MetaspaceSize和MaxMetaspaceSize设置一样大;具体设置多大，建议稳定运行一段时间后通过`jstat -gc pid`确认且这个值大一些，对于大部分项目256m即可"
            );
        } else {
            // 存在该值
            double value = Units.getMNumber(maxMetaspaceSizeStr);
            if (value < 128) {
                return new CheckoutResult(
                        Level.WARN,
                        "heap",
                        "MaxMetaspaceSize小于128m",
                        "MaxMetaspaceSize过小会导致频繁Full GC）",
                        "建议值 -XX:MaxMetaspaceSize=256m 或者 -XX:MaxMetaspaceSize=512m "
                );
            }
            return CheckoutResult.ok("MaxMetaspaceSize参数设置正确", "heap");
        }
    }

    // MaxPermSize,仅限jdk7
    private CheckoutResult checkoutMaxPermSpaceSize(HashMap<String, String> parametersValuesMap) {
        String maxMetaspaceSizeStr = parametersValuesMap.get("MaxPermSize");
        if (StringUtils.isBlank(maxMetaspaceSizeStr)) {
            // 不存在该值
            return new CheckoutResult(
                    Level.ERROR,
                    "heap",
                    "不存在MaxPermSize参数",
                    "未设置元空间的上限，存在系统内存OOM的风险，请在启动参数中添加 -XX:MaxPermSize=xxx 设置（过小会导致频繁Full GC）",
                    "MaxPermSize和PermSize设置一样大;具体设置多大，建议稳定运行一段时间后通过`jstat -gc pid`确认且这个值大一些，对于大部分项目256m即可"
            );
        } else {
            // 存在该值
            double value = Units.getMNumber(maxMetaspaceSizeStr);
            if (value < 128) {
                return new CheckoutResult(
                        Level.WARN,
                        "heap",
                        "MaxPermSize小于128m",
                        "MaxPermSize过小会导致频繁Full GC）",
                        "建议值 -XX:MaxPermSize=256m 或者 -XX:MaxPermSize=512m "
                );
            }
            return CheckoutResult.ok("MaxPermSize参数设置正确", "heap");
        }
    }

    // checkoutHeapDumpPath
    private CheckoutResult checkoutHeapDumpPath(HashMap<String, String> parametersValuesMap) {
        String heapDumpPath = parametersValuesMap.get("HeapDumpPath");
        if (StringUtils.isBlank(heapDumpPath)) {
            // 不存在该值
            return new CheckoutResult(Level.WARN, "heap", "不存在HeapDumpPath参数",
                    "未启用堆区溢出时的日志路径，请在启动参数中添加 -XX:HeapDumpPath=your_path/xxx.heap.log", "");
        } else {
            return new CheckoutResult(Level.OK, "-XX:HeapDumpPath设置正确", "heap");
        }
    }

    // HeapDumpOnOutOfMemoryError
    private CheckoutResult checkoutHeapDumpOnOutOfMemoryError(HashMap<String, String> parametersValuesMap) {
        String heapDumpOnOutOfMemoryError = parametersValuesMap.get("HeapDumpOnOutOfMemoryError");
        if (StringUtils.isBlank(heapDumpOnOutOfMemoryError)) {
            return new CheckoutResult(
                    Level.ERROR,
                    "heap",
                    "不存在+HeapDumpOnOutOfMemoryError参数",
                    "未启用堆区溢出时的错误处理机制，请在启动参数中添加 -XX:+HeapDumpOnOutOfMemoryError 开启",
                    ""
            );
        } else {
            return new CheckoutResult(
                    Level.OK, "-XX:+HeapDumpOnOutOfMemoryError参数设置正确", "heap"
            );
        }
    }

    // AlwaysPreTouch
    private CheckoutResult checkoutAlwaysPreTouch(HashMap<String, String> parametersValuesMap) {
        String alwaysPreTouch = parametersValuesMap.get("AlwaysPreTouch");
        if (StringUtils.isBlank(alwaysPreTouch) && maxHeap > 4) {
            // 不存在该值并且最大堆内存大于4 g
            return new CheckoutResult(Level.ERROR, "最大堆超过4g，如果堆内存较大，请添加 -XX:+AlwaysPreTouch 参数强制 JVM 在启动时分配内存，可使得后续运行更顺畅（副作用：启动速度变慢）", "heap");
        } else {
            return new CheckoutResult(Level.OK, "-XX:+AlwaysPreTouch 设置正确", "heap");
        }
    }
}
