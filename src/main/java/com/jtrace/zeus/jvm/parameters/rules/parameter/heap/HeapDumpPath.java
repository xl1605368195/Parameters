package com.jtrace.zeus.jvm.parameters.rules.parameter.heap;

import com.jtrace.zeus.jvm.parameters.rules.Check;
import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.Level;

/**
 * @author xule05
 * @date 2020/1/31 下午6:36
 */
@Deprecated
public class HeapDumpPath implements Check {

    private boolean isExist;

    @Override
    public CheckoutResult checkout() {
        if (!isExist) {
            return new CheckoutResult(
                    Level.WARN,
                    "heap",
                    "不存在HeapDumpPath参数",
                    "未启用堆区溢出时的日志路径，请在启动参数中添加 -XX:HeapDumpPath=your_path/xxx.heap.log",
                    ""
            );
        }
        return new CheckoutResult(
                Level.OK,
                "heap",
                "-XX:HeapDumpPath设置正确",
                "",
                ""
        );
    }

    public HeapDumpPath(boolean isExist) {
        this.isExist = isExist;
    }
}
