package com.jtrace.zeus.jvm.parameters.rules;

/**
 * @author xule05
 * @date 2020/1/30 下午9:16
 */
public enum Level {
    /**
     * 正常,符合预期，无需修改
     */
    OK,

    /**
     * 告警,有风险,可以暂缓修改
     */
    WARN,

    /**
     * 错误,必须修改
     */
    ERROR
}
