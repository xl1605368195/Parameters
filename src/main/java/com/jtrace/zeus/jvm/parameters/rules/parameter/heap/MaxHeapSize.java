package com.jtrace.zeus.jvm.parameters.rules.parameter.heap;

import com.jtrace.zeus.jvm.parameters.rules.Check;
import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;
import com.jtrace.zeus.jvm.parameters.utils.Units;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xule05
 * @date 2020/1/31 下午5:06
 */
@Deprecated
// 最大堆大小
public class MaxHeapSize implements Check {

    // 是否存在
    private boolean isExist;

    // 实际值，单位m
    private double value;

    // 机器实际物理内存,单位G
    private double totalMem;

    // 字符串值
    private String valueStr;

    @Override
    public CheckoutResult checkout() {
        if (!isExist) {
            // 不存在该值
            return new CheckoutResult(
                    Level.ERROR,
                    "heap",
                    "未设置最大堆内存",
                    "",
                    ""
            );
        } else {
            // 存在该值
            if (value < totalMem / 2) {
                return new CheckoutResult(
                        Level.WARN,
                        "heap",
                        "最大堆大小设置小于物理内存1/2",
                        "如果服务运行正常，最大堆大小设置过小，请降低机器配置，或在启动参数中使用 -Xmx=" + totalMem / 2 + "g 指定最大堆大小",
                        "建议值 -Xmx=" + totalMem / 2 + "g  "
                );
            }

            if (value > totalMem * 4 / 5 && (totalMem - value) >= 1) {
                return new CheckoutResult(
                        Level.WARN,
                        "heap",
                        "最大堆大小设置大于物理内存4/5",
                        "最大堆大小设置过大，应预留一部分给堆外内存",
                        "建议值 -Xmx=" + totalMem * 3 / 4 + "g"
                );
            }

            if (value <= totalMem * 4 / 5 && (totalMem - value) < 1) {
                return new CheckoutResult(
                        Level.WARN,
                        "heap",
                        "最大堆大小设置过大,堆外内存小于1g",
                        "最大堆大小设置过大，应预留一部分给堆外内存",
                        "建议值 -Xmx=" + totalMem * 3 / 4 + "g"
                );
            }

            return CheckoutResult.ok("MaxMetaspaceSize参数设置正确","heap");
        }
    }

    public MaxHeapSize(String valueStr, double totalMem) {
        if (StringUtils.isBlank(valueStr)) {
            this.isExist = false;
            this.value = 0;
            this.valueStr = valueStr;
        } else {
            this.isExist = true;
            this.valueStr = valueStr;
            // 转换为g
            this.value = Units.getGNumber(valueStr);
        }
        this.totalMem = totalMem;
    }
}
