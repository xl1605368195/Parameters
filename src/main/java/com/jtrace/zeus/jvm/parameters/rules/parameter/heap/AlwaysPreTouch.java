package com.jtrace.zeus.jvm.parameters.rules.parameter.heap;

import com.jtrace.zeus.jvm.parameters.rules.Check;
import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;
import com.jtrace.zeus.jvm.parameters.utils.Units;

/**
 * @author xule05
 * @date 2020/1/31 下午6:25
 */
@Deprecated
public class AlwaysPreTouch implements Check {

    // 是否存在
    private boolean isExist;

    // 实际值
    private boolean value;


    private double maxHeap;

    @Override
    public CheckoutResult checkout() {
        if (!isExist && maxHeap >= 4) {
            // 不存在该值
            return new CheckoutResult(
                    Level.ERROR,
                    "最大堆超过4G，如果堆内存较大，请添加 -XX:+AlwaysPreTouch 参数强制 JVM 在启动时分配内存，可使得后续运行更顺畅（副作用：启动速度变慢）",
                    "",
                    ""
            );
        }
        return new CheckoutResult(
                Level.OK,
                "AlwaysPreTouch 设置正确",
                "",
                ""
        );
    }

    public AlwaysPreTouch(String maxHeapSize) {
        this.maxHeap = Units.getGNumber(maxHeapSize);
    }
}
