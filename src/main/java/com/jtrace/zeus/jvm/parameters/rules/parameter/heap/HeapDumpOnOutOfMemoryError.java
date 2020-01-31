package com.jtrace.zeus.jvm.parameters.rules.parameter.heap;

import com.jtrace.zeus.jvm.parameters.rules.Check;
import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;

/**
 * @author xule05
 * @date 2020/1/31 下午6:35
 */
@Deprecated
public class HeapDumpOnOutOfMemoryError implements Check {

    private boolean isExist;

    @Override
    public CheckoutResult checkout() {
        if (!isExist) {
            return new CheckoutResult(
                    Level.ERROR,
                    "不存在+HeapDumpOnOutOfMemoryError参数",
                    "未启用堆区溢出时的错误处理机制，请在启动参数中添加 -XX:+HeapDumpOnOutOfMemoryError 开启",
                    ""
            );
        }
        return new CheckoutResult(
                Level.OK,
                "+HeapDumpOnOutOfMemoryError参数设置正确",
                "",
                ""
        );
    }

    public HeapDumpOnOutOfMemoryError(boolean isExist) {
        this.isExist = isExist;
    }

}
