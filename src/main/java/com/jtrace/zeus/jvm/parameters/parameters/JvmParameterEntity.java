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
@NoArgsConstructor
@ToString
public class JvmParameterEntity {

    public static String hostname="http://zeus.yitrace.com:8080";
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
     * 参数类型: 对系统的影响分类
     **/
    private String type;

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
     *  参数值的类型
     **/
    private String valueType;

    /**
     * 备注
     **/
    private String extend;

    /**
     *  链接
     **/
    private String url;

    public JvmParameterEntity(String name) {
        this.name = name;
    }

    public JvmParameterEntity(String name, String[] versions, String[] examples, String type, String os, String meaning, String hanyi, String use, String defaultValue, String valueType, String extend) {
        this.name = name;
        this.versions = versions;
        this.examples = examples;
        this.type = type;
        this.os = os;
        this.meaning = meaning;
        this.hanyi = hanyi;
        this.use = use;
        this.defaultValue = defaultValue;
        this.valueType = valueType;
        this.extend = extend;
    }

    public JvmParameterEntity(String name, String[] versions, String[] examples, String type, String os, String meaning, String hanyi, String use, String defaultValue, String valueType, String extend, String url) {
        this(name,versions,examples,type,os,meaning,hanyi,use,defaultValue,valueType,extend);
        this.url = hostname+url;
    }
}
