package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.rules.Check;
import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.ParametersCollection;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @author xule05
 * @date 2020/1/30 下午11:50
 */
@Service
public class ParametersEvaluateService {

    /**
     * step1:参数分解与过滤
     */
    public HashMap<String, String> splitParameters(String parameters) {
        String[] array = parameters.split(" ");
        HashMap<String, String> maps = new HashMap<>();
        for (String tmp : array) {
            if (tmp.startsWith("-XX")) {
                parseAdvancedOptions(maps, tmp);
            } else if (tmp.startsWith("-X")) {
                parseNonStandardOptions(maps, tmp);
            } else if (tmp.startsWith("-") & !tmp.startsWith("-D")) {
                parseStandardOptions(maps, tmp);
            } else {
                // ignore.... 未知参数
            }
        }
        return maps;
    }

    /**
     * step2:参数检查
     */
    public List<CheckoutResult> checkParameters(HashMap<String, String> parametersValuesMap) {
        List<CheckoutResult> results = new LinkedList<>();
        Map<String, Class<? extends Check>> parametersClassMap = ParametersCollection.getMap();
        try {
            for (Map.Entry<String, String> entry : parametersValuesMap.entrySet()) {
                String valueStr = entry.getValue();
                Class<? extends Check> clazz = parametersClassMap.get(entry.getKey());
                if (clazz != null) {
                    Constructor<? extends Check> constructor = clazz.getConstructor(String.class);
                    Check parameter = constructor.newInstance(valueStr);
                    CheckoutResult checkout = parameter.checkout();
                    results.add(checkout);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 创建堆参数
     *
     * @param parameters 原始字符串
     * @param result
     */
    public void createHeapParameters(String parameters, HashSet<Check> result) {
        //
        if (parameters.contains("-Xmn")) {

        }

        // Heap的最大值
        if (parameters.contains("-Xmx") || parameters.contains("-XX:MaxHeapSize")) {

        }
    }

    // -agentlib
    private void parseStandardOptions(HashMap<String, String> maps, String tmp) {
        if (tmp.startsWith("-verbose")) {
            // -verbose:class  -verbose:gc  -verbose:jni
            maps.put(tmp, "true");
        } else if (tmp.contains(":")) {
            String[] keyAndValue = tmp.split(":");
            if (keyAndValue.length == 2) {
                //  -version:release -splash:imgname -javaagent:jarpath[=options]
                //  -agentlib:libname[=options]  -agentpath:pathname[=options]
                String name = keyAndValue[0];
                String value = keyAndValue[1];
                maps.put(name, value);
            }
        } else {
            // -client -jar -server
            // -jre-restrict-search  -no-jre-restrict-search
            maps.put(tmp, "true");
        }
    }

    private void parseNonStandardOptions(HashMap<String, String> maps, String tmp) {
        if (tmp.contains(":")) {
            //    -Xbootclasspath:path -Xbootclasspath/a:path -Xbootclasspath/p:path
            //    -Xcheck:jni  -Xloggc:filename -Xshare:mode
            //    -XshowSettings:category -Xverify:mode
            String[] keyAndValue = tmp.split(":");
            if (keyAndValue.length == 2) {
                String name = keyAndValue[0];
                String value = keyAndValue[1];
                maps.put(name, value);
            }
        } else if (tmp.contains("=")) {
            //    -Xmaxjitcodesize=size
            String[] keyAndValue = tmp.split("=");
            if (keyAndValue.length == 2) {
                String name = keyAndValue[0];
                String value = keyAndValue[1];
                maps.put(name, value);
            }
        } else if (tmp.startsWith("-Xms") || tmp.startsWith("-Xmx") || tmp.startsWith("-Xss")) {
            String name = tmp.substring(0, 4);
            String value = tmp.substring(4);
            maps.put(name, value);
        } else {
            //  -Xbatch -Xcomp -Xdebug -Xdiag -Xfuture -Xint -Xinternalversion
            //  -Xmixed -Xnoclassgc -Xprof -Xrs
            String name = tmp;
            String value = "true";
            maps.put(name, value);
        }
    }

    private void parseAdvancedOptions(HashMap<String, String> maps, String tmp) {
        if (tmp.contains("=")) {
            // -XX:ParallelCMSThreads=2
            String[] keyAndValue = tmp.split("=");
            if (keyAndValue.length == 2) {
                String name = keyAndValue[0].substring(4);
                // value 有可能带有单位
                String value = keyAndValue[1];
                maps.put(name, value);
            }
        } else {
            // -XX:+UseConcMarkSweepGC
            if (tmp.contains("+")) {
                String[] keyAndValue = tmp.split("\\+");
                if (keyAndValue.length == 2) {
                    String name = keyAndValue[1];
                    maps.put(name, "true");
                }
            } else if (tmp.contains("-")) {
                String[] keyAndValue = tmp.split("-");
                if (keyAndValue.length == 2) {
                    String name = keyAndValue[1];
                    maps.put(name, "false");
                }
            } else {
                // ignore.... 未知参数
            }
        }
    }
}
