package com.jtrace.zeus.jvm.parameters.rules;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xule05
 * @date 2020/1/31 上午2:31
 */
@Deprecated
public class ParametersCollection {
    private static final String path = "com.jtrace.zeus.jvm.parameters.rules.parameter";

    private static final Map<String, Class<? extends Check>> map = new HashMap<>();

    static {
        // wiki: https://www.programcreek.com/java-api-examples/index.php?api=org.reflections.scanners.MethodAnnotationsScanner
        Reflections f = new Reflections(path);
        Set<Class<?>> set = f.getTypesAnnotatedWith(ScanParameter.class);
        for (Class clazz : set) {
            map.put(clazz.getSimpleName(), clazz);
        }
    }

    public static Map<String, Class<? extends Check>> getMap() {
        return map;
    }
}