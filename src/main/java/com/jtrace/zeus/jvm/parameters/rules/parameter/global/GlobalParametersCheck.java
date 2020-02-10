package com.jtrace.zeus.jvm.parameters.rules.parameter.global;

import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author xule05
 * @date 2020/2/10 下午10:03
 */
public class GlobalParametersCheck {
    /**
     * 检查全局参数
     *
     * @param parametersValuesMap
     * @param list
     */
    public void checkGlobalParameters(HashMap<String, String> parametersValuesMap, List<CheckoutResult> list) {
        String printFlagsFinal = parametersValuesMap.get("PrintFlagsFinal");
        if (StringUtils.isBlank(printFlagsFinal) || printFlagsFinal.equals("false")) {
            CheckoutResult checkoutResult = new CheckoutResult(Level.WARN, "global", "未开启-XX:+PrintFlagsFinal", "启动时添加 -XX:+PrintFlagsFinal 参数，可以在 JVM 启动时输出所有参数值，便于检查参数是否被覆盖", "");
            list.add(checkoutResult);
        }
        String useBiasedLocking = parametersValuesMap.get("UseBiasedLocking");
        if (StringUtils.isBlank(useBiasedLocking) || useBiasedLocking.equals("true")) {
            CheckoutResult checkoutResult = new CheckoutResult(Level.WARN, "global", "默认开启了偏向锁", "如果服务存在明显的锁竞争，请使用 -XX:-UseBiasedLocking 参数取消偏向锁", "");
            list.add(checkoutResult);
        }
    }
}
