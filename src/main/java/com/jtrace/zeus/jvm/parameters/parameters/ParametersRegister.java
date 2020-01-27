package com.jtrace.zeus.jvm.parameters.parameters;


import java.util.HashSet;


/**
 * @author xule05
 * @date 2020/1/27 下午8:13
 */
public class ParametersRegister {

    public static HashSet<JvmParameterEntity> set = new HashSet<>();

    static {
        for (String s : JvmParametersInfo.infos.split("\\\n")) {
            set.add(new JvmParameterEntity(s));
        }
    }
}
