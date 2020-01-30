package com.jtrace.zeus.jvm.parameters.parameters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xule05
 * @date 2020/1/30 下午12:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParametersRankEntity {

    /**
     * 参数命令或者完整的模式
     **/
    private String name;

    /**
     * 参数使用次数：线下
     **/
    private int useOffLine;

    /**
     * 参数使用次数：线上
     **/
    private int useOnLine;

    public ParametersRankEntity(String name) {
        this.name = name;
    }
}
