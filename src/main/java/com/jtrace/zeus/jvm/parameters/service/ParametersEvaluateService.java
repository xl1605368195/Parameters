package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.rules.Check;
import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;
import com.jtrace.zeus.jvm.parameters.rules.ParametersCollection;
import com.jtrace.zeus.jvm.parameters.utils.ParseParameterUtils;
import com.jtrace.zeus.jvm.parameters.utils.Units;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @author xule05
 * @date 2020/1/30 下午11:50
 */
@Service
public class ParametersEvaluateService {

    /**
     * step1:参数分解与过滤
     */
    public HashMap<String, String> splitParameters(String parameters) {
        String[] array = parameters.split(" ");
        HashMap<String, String> maps = new HashMap<>();
        for (String tmp : array) {
            if (tmp.startsWith("-XX")) {
                ParseParameterUtils.parseAdvancedOptions(maps, tmp);
            } else if (tmp.startsWith("-X")) {
                ParseParameterUtils.parseNonStandardOptions(maps, tmp);
            } else if (tmp.startsWith("-") & !tmp.startsWith("-D")) {
                ParseParameterUtils.parseStandardOptions(maps, tmp);
            } else {
                // ignore.... 未知参数
            }
        }
        return maps;
    }

    /**
     * step2:参数检查
     */
    public List<CheckoutResult> checkParameters(HashMap<String, String> parametersValuesMap, String version, int totalMem) {
        List<CheckoutResult> results = new LinkedList<>();
        checkHeapParameters(parametersValuesMap, totalMem, version, results);
        return results;
    }

    /**
     * 堆参数检查
     *
     * @param parametersValuesMap 解析之后的字符串
     * @param totalMem            实际物理内存
     */
    public void checkHeapParameters(HashMap<String, String> parametersValuesMap, int totalMem, String version, List<CheckoutResult> list) {
        CheckoutResult checkoutMaxHeap = checkoutMaxHeapSize(parametersValuesMap, totalMem);
        CheckoutResult checkoutMaxMetaSpaceSize = null;
        if (version.equals("jdk7")) {
            // todo 永久带
            checkoutMaxMetaSpaceSize = checkoutMaxMetaSpaceSize(parametersValuesMap);
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

    //最大堆参数检查
    private CheckoutResult checkoutMaxHeapSize(HashMap<String, String> parametersValuesMap, int totalMem) {
        // -Xmx 或者 -XX:MaxHeapSize
        String maxHeapSize1 = parametersValuesMap.get("-Xmx");
        String maxHeapSize2 = parametersValuesMap.get("MaxHeapSize");
        // 没有发现 -Xmx 或者 -XX:MaxHeapSize
        if (StringUtils.isBlank(maxHeapSize1) && StringUtils.isBlank(maxHeapSize2)) {
            return new CheckoutResult(Level.ERROR, "未设置最大堆内存", "启动参数中使用 -Xmx=xxxg（或者-XX:MaxHeapSize=xxxg)来指定最大堆大小", "推荐将最大堆大小设置为物理内存的50%~80%");
        } else if (StringUtils.isNoneBlank(maxHeapSize1) || StringUtils.isBlank(maxHeapSize2)) {
            // 发现 -Xmx
            return getCheckout(maxHeapSize1, totalMem, "-Xmx");
        } else if (StringUtils.isNoneBlank(maxHeapSize2) || StringUtils.isBlank(maxHeapSize1)) {
            // -XX:MaxHeapSize
            return getCheckout(maxHeapSize2, totalMem, "-XX:MaxHeapSize=");
        } else {
            // 错误配置参数
            return new CheckoutResult(Level.ERROR, "设置了-Xmx,又设置了-XX:MaxHeapSize，重复了");
        }
    }

    // param=-Xmx 或者 -XX:MaxHeapSize
    private CheckoutResult getCheckout(String maxHeapSizeStr, int totalMem, String param) {
        double maxHeap1 = Units.getGNumber(maxHeapSizeStr);
        if (maxHeap1 < totalMem / 2) {
            return new CheckoutResult(
                    Level.WARN,
                    "最大堆大小设置小于物理内存1/2",
                    "如果服务运行正常，最大堆大小设置过小",
                    "建议值 " + param + (totalMem / 2) + "g"
            );
        }

        if (maxHeap1 <= totalMem * 4 / 5 && (totalMem - maxHeap1) < 1) {
            return new CheckoutResult(
                    Level.WARN,
                    "最大堆大小设置过大,堆外内存小于1g",
                    "最大堆大小设置过大，应预留一部分给堆外内存",
                    "建议值 " + param + totalMem * 3 / 4 + "g"
            );
        }

        if (maxHeap1 > totalMem * 4 / 5 && (totalMem - maxHeap1) >= 1) {
            return new CheckoutResult(
                    Level.WARN,
                    "最大堆大小设置大于物理内存4/5",
                    "最大堆大小设置过大，应预留一部分给堆外内存",
                    "建议值 " + param + totalMem * 3 / 4 + "g"
            );
        }
        return CheckoutResult.ok("最大堆参数设置正确");
    }

    // MaxMetaSpaceSize
    private CheckoutResult checkoutMaxMetaSpaceSize(HashMap<String, String> parametersValuesMap) {
        String maxMetaspaceSizeStr = parametersValuesMap.get("MaxMetaspaceSize");
        if (StringUtils.isBlank(maxMetaspaceSizeStr)) {
            // 不存在该值
            return new CheckoutResult(
                    Level.ERROR,
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
                        "MaxMetaspaceSize小于128m",
                        "MaxMetaspaceSize过小会导致频繁Full GC）",
                        "建议值 -XX:MaxMetaspaceSize=256m 或者 -XX:MaxMetaspaceSize=512m "
                );
            }
            return CheckoutResult.ok("MaxMetaspaceSize参数设置正确");
        }
    }

    // checkoutHeapDumpPath
    private CheckoutResult checkoutHeapDumpPath(HashMap<String, String> parametersValuesMap) {
        String HeapDumpPath = parametersValuesMap.get("HeapDumpPath");
        if (StringUtils.isBlank(HeapDumpPath)) {
            // 不存在该值
            return new CheckoutResult(Level.WARN, "不存在HeapDumpPath参数",
                    "未启用堆区溢出时的日志路径，请在启动参数中添加 -XX:HeapDumpPath=your_path/xxx.heap.log", "");
        } else {
            return new CheckoutResult(Level.OK, "-XX:HeapDumpPath设置正确");
        }
    }

    // HeapDumpOnOutOfMemoryError
    private CheckoutResult checkoutHeapDumpOnOutOfMemoryError(HashMap<String, String> parametersValuesMap) {
        String HeapDumpOnOutOfMemoryError = parametersValuesMap.get("HeapDumpOnOutOfMemoryError");
        if (StringUtils.isBlank(HeapDumpOnOutOfMemoryError)) {
            return new CheckoutResult(
                    Level.ERROR,
                    "不存在+HeapDumpOnOutOfMemoryError参数",
                    "未启用堆区溢出时的错误处理机制，请在启动参数中添加 -XX:+HeapDumpOnOutOfMemoryError 开启",
                    ""
            );
        } else {
            return new CheckoutResult(
                    Level.OK,
                    "-XX:+HeapDumpOnOutOfMemoryError参数设置正确"
            );
        }
    }

    // AlwaysPreTouch
    private CheckoutResult checkoutAlwaysPreTouch(HashMap<String, String> parametersValuesMap) {
        double maxHeap = 0;
        String AlwaysPreTouch = parametersValuesMap.get("AlwaysPreTouch");
        String maxHeapSize1 = parametersValuesMap.get("-Xmx");
        String maxHeapSize2 = parametersValuesMap.get("MaxHeapSize");
        if (StringUtils.isNoneBlank(maxHeapSize1)) {
            maxHeap = Units.getGNumber(maxHeapSize1);
        }
        if (StringUtils.isNoneBlank(maxHeapSize2)) {
            maxHeap = Units.getGNumber(maxHeapSize2);
        }
        if (StringUtils.isBlank(AlwaysPreTouch) && maxHeap > 4) {
            // 不存在该值并且最大堆内存大于4 g
            return new CheckoutResult(Level.ERROR, "最大堆超过4g，如果堆内存较大，请添加 -XX:+AlwaysPreTouch 参数强制 JVM 在启动时分配内存，可使得后续运行更顺畅（副作用：启动速度变慢）");
        } else {
            return new CheckoutResult(Level.OK, "-XX:+AlwaysPreTouch 设置正确");
        }
    }
}
