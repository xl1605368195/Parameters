package com.jtrace.zeus.jvm.parameters.parameters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xule05
 * @date 2020/1/27 下午8:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JvmParameterEntity {

    /**
     * 参数命令或者完整的模式
     **/
    private String name;

    /**
     * 参数版本: jdk1~12
     **/
    private String[] versions;

    /**
     * 参数例子
     **/
    private String[] examples;

    /**
     * 参数类型
     **/
    private Type type;

    /**
     * 适用的操作系统
     **/
    private String os;

    /**
     * 英文含义
     **/
    private String meaning;

    /**
     * 中文解释
     **/
    private String hanyi;

    /**
     * 使用
     **/
    private String use;

    /**
     * 默认值
     **/
    private String defaultValue;

    /**
     * 备注
     **/
    private String extend;


    public JvmParameterEntity(String name) {
        this.name = name;
    }
}
