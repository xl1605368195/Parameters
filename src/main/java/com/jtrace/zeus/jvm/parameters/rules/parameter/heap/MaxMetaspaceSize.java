package com.jtrace.zeus.jvm.parameters.rules.parameter.heap;

import com.jtrace.zeus.jvm.parameters.rules.Check;
import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;
import com.jtrace.zeus.jvm.parameters.rules.ScanParameter;
import com.jtrace.zeus.jvm.parameters.utils.Units;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;


/**
 * @author xule05
 * @date 2020/1/30 下午8:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ScanParameter
@Deprecated
public class MaxMetaspaceSize implements Check {

    // 是否存在
    private boolean isExist;

    // 实际值，单位m
    private double value;

    // 字符串值：128m
    private String valueStr;

    @Override
    public CheckoutResult checkout() {
        if (!isExist) {
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
            if (value < 128) {
                return new CheckoutResult(
                        Level.WARN,
                        "heap",
                        "MaxMetaspaceSize小于128m",
                        "MaxMetaspaceSize过小会导致频繁Full GC）",
                        "建议值 -XX:MaxMetaspaceSize=256m 或者 -XX:MaxMetaspaceSize=512m "
                );
            }
            return CheckoutResult.ok("MaxMetaspaceSize参数设置正确","heap");
        }
    }

    public MaxMetaspaceSize(String valueStr) {
        if (StringUtils.isBlank(valueStr)) {
            this.isExist = false;
            this.value = 0;
            this.valueStr = valueStr;
        } else {
            this.isExist = true;
            this.valueStr = valueStr;
            // 转换为m
            this.value = Units.getMNumber(valueStr);
        }
    }
}
