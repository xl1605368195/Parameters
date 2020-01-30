package com.jtrace.zeus.jvm.parameters.rules.heap;

import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;
import com.jtrace.zeus.jvm.parameters.rules.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xule05
 * @date 2020/1/30 下午8:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MaxMetaspaceSize implements Parameter {

    // 是否存在
    private boolean isExist;

    // 规则
    private int value = -1;

    @Override
    public CheckoutResult checkout() {
        if (!isExist) {
            // 不存在该值
            return new CheckoutResult(
                    Level.ERROR,
                    "不存在MaxMetaspaceSize参数",
                    "未设置元空间的上限，存在系统内存OOM的风险，请在启动参数中添加`-XX:MaxMetaspaceSize=xxx`设置（过小会导致频繁Full GC）",
                    "MetaspaceSize和MaxMetaspaceSize设置一样大;具体设置多大，建议稳定运行一段时间后通过`jstat -gc pid`确认且这个值大一些，对于大部分项目256m即可"
            );
        } else {
            // 存在该值
            if (value < 128) {
                return new CheckoutResult(
                        Level.WARN,
                        "MaxMetaspaceSize小于128m",
                        "MaxMetaspaceSize过小会导致频繁Full GC）",
                        ""
                );
            }
            return CheckoutResult.ok("MaxMetaspaceSize参数设置正确");
        }
    }
}
