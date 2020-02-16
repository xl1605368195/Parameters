package com.jtrace.zeus.jvm.parameters.rules.parameter.gc;

import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.jtrace.zeus.jvm.parameters.utils.Utils.getMaxHeapMemory;

/**
 * @author xule05
 * @date 2020/2/2 下午10:27
 */
public class GcStragetyCheck {

    private GcCollectorEnum youngCollector;

    private GcCollectorEnum oldCollector;

    private HashMap<String, String> parametersValuesMap;

    private double maxMemorySize;

    private double maxHeapSize;

    public GcStragetyCheck(HashMap<String, String> parametersValuesMap, int maxMemorySize) {
        this.parametersValuesMap = parametersValuesMap;
        this.maxMemorySize = maxMemorySize;
        this.maxHeapSize = getMaxHeapMemory(parametersValuesMap);
        setGcType(parametersValuesMap);
    }

    private void setGcType(HashMap<String, String> parametersValuesMap) {
        // 顺序不要更改
        String useParNewGc = parametersValuesMap.get("UseParNewGC");
        if (StringUtils.isNotBlank(useParNewGc) && StringUtils.equals(useParNewGc, "true")) {
            youngCollector = GcCollectorEnum.PAR_NEW;
            oldCollector = GcCollectorEnum.SERIAL_OLD;
        }

        String useConcMarkSweepGc = parametersValuesMap.get("UseConcMarkSweepGC");
        if (StringUtils.isNotBlank(useConcMarkSweepGc) && StringUtils.equals(useConcMarkSweepGc, "true")) {
            oldCollector = GcCollectorEnum.CMS;
        }

        String useParallelGc = parametersValuesMap.get("UseParallelGC");
        if (StringUtils.isNotBlank(useParallelGc) && StringUtils.equals(useParallelGc, "true")) {
            youngCollector = GcCollectorEnum.PARALLEL_SCAVENGE;
            oldCollector = GcCollectorEnum.SERIAL_OLD;
        }

        String useParallelOldGc = parametersValuesMap.get("UseParallelOldGC");
        if (StringUtils.isNotBlank(useParallelOldGc) && StringUtils.equals(useParallelOldGc, "true")) {
            youngCollector = GcCollectorEnum.PARALLEL_SCAVENGE;
            oldCollector = GcCollectorEnum.PARALLEL_OLD;
        }

        String useSerialGc = parametersValuesMap.get("UseSerialGC");
        if (StringUtils.isNotBlank(useSerialGc) && StringUtils.equals(useSerialGc, "true")) {
            youngCollector = GcCollectorEnum.SERIAL;
            oldCollector = GcCollectorEnum.SERIAL_OLD;
        }

        String useG1Gc = parametersValuesMap.get("UseG1GC");
        if (StringUtils.isNotBlank(useG1Gc) && StringUtils.equals(useG1Gc, "true")) {
            youngCollector = GcCollectorEnum.G1;
            oldCollector = GcCollectorEnum.G1;
        }
    }

    public void checkout(List<CheckoutResult> list) {
        CheckoutResult checkoutDisableExplicitGc = checkoutDisableExplicitGc();
        if (checkoutDisableExplicitGc != null) {
            list.add(checkoutDisableExplicitGc);
        }
        CheckoutResult isSerialGc = checkoutIsSerialGc();
        if (isSerialGc != null) {
            list.add(isSerialGc);
        }
        checkoutGcType(list);
    }

    private CheckoutResult checkoutDisableExplicitGc() {
        String disableExplicitGc = parametersValuesMap.get("DisableExplicitGC");
        if ("true".equals(disableExplicitGc)) {
            return new CheckoutResult(
                    Level.WARN,
                    "gc",
                    "忽略手动调用GC",
                    "System.gc()的调用就会变成一个空调用，完全不触发GC，存在堆外内存OOM的风险",
                    "请在启动参数中删除 -XX:+DisableExplicitGC 参数"
            );
        }
        return null;
    }

    private CheckoutResult checkoutIsSerialGc() {
        if (maxMemorySize != 0 && maxMemorySize >= 4 * 1024 && (oldCollector == GcCollectorEnum.SERIAL_OLD
                || youngCollector == GcCollectorEnum.SERIAL)) {
            return new CheckoutResult(
                    Level.WARN,
                    "gc",
                    "新生代或老年代使用了单线程垃圾谁回收器",
                    "单线程垃圾回收效率不高，建议使用多线程版本（除 Serial XX 外其他类型）",
                    "请在启动参数中删除 -XX:+DisableExplicitGC 参数"
            );
        }
        return null;
    }

    private void checkoutGcType(List<CheckoutResult> list) {
        if (GcCollectorEnum.G1.equals(oldCollector)) {
            // G1 不应指定新生代大小
            String newSize = parametersValuesMap.get("NewSize");
            if (StringUtils.isNotBlank(newSize)) {
                list.add(new CheckoutResult(
                        Level.ERROR,
                        "heap",
                        "错误配置了NewSize",
                        "G1 垃圾回收器里不应该配置新生代的大小，请不要配置 -XX:NewSize=n 参数",
                        ""
                ));
            }
            if (maxMemorySize != 0 && maxMemorySize < 6) {
                list.add(new CheckoutResult(
                        Level.WARN,
                        "heap",
                        "不建议使用G1",
                        "较小的内存（< 6G）不建议使用 G1 垃圾回收器，可使用 CMS 或 Parallel 系列 垃圾回收器",
                        ""
                ));
            }
        } else {
            // CMS 垃圾回收器
            if (GcCollectorEnum.CMS.equals(oldCollector)) {
                String cmsInitiatingOccupancyFraction = parametersValuesMap.get("CMSInitiatingOccupancyFraction");
                String useCmsInitiatingOccupancyOnly = parametersValuesMap.get("UseCMSInitiatingOccupancyOnly");
                if (StringUtils.isNotBlank(cmsInitiatingOccupancyFraction) && !"true".equals(useCmsInitiatingOccupancyOnly)) {
                    list.add(new CheckoutResult(
                            Level.ERROR,
                            "heap",
                            "未设置 -XX:+UseCMSInitiatingOccupancyOnly 参数",
                            "未设置 -XX:+UseCMSInitiatingOccupancyOnly 参数，CMS 回收的阈值只在第一次生效",
                            ""
                    ));
                }
                // Full GC 时老年代使用cms
                if (StringUtils.isNotBlank(cmsInitiatingOccupancyFraction) && !"true".equals(useCmsInitiatingOccupancyOnly)) {
                    list.add(new CheckoutResult(
                            Level.ERROR,
                            "heap",
                            "未设置 -XX:+UseCMSInitiatingOccupancyOnly 参数",
                            "未设置 -XX:+UseCMSInitiatingOccupancyOnly 参数，CMS 回收的阈值只在第一次生效",
                            ""
                    ));
                }
                String disableExplicitGc = parametersValuesMap.get("DisableExplicitGC");
                String explicitGcInvokesConcurrent = parametersValuesMap.get("ExplicitGCInvokesConcurrent");
                if (!"true".equals(disableExplicitGc) && !"true".equals(explicitGcInvokesConcurrent)) {
                    list.add(new CheckoutResult(
                            Level.WARN,
                            "heap",
                            "老年代如果使用CMS，设置 -XX:+ExplicitGCInvokesConcurrent 参数可以降低 Full GC 时 STW 的时间",
                            "",
                            ""
                    ));
                }
            }

            // 使用了CMS垃圾回收器，但是没有启用垃圾回收器
            List<String> cmsParameters = parametersValuesMap.keySet().stream().filter(x -> x.contains("CMS")).collect(Collectors.toList());
            if (!cmsParameters.isEmpty() && !GcCollectorEnum.CMS.equals(oldCollector)) {
                list.add(new CheckoutResult(
                        Level.ERROR,
                        "heap",
                        "配置了 CMS 垃圾回收启动参数，但并没有启用，实际使用的老年代垃圾回收器为:" + oldCollector.getName(),
                        "未设置 -XX:+UseCMSInitiatingOccupancyOnly 参数，CMS 回收的阈值只在第一次生效",
                        ""
                ));
            }
        }
    }
}
