package com.jtrace.zeus.jvm.parameters.utils;

import java.util.HashMap;

/**
 * @author xule05
 * @date 2020/1/31 下午10:15
 */
public class ParseParameterUtils {
    // -agentlib
    public static void parseStandardOptions(HashMap<String, String> maps, String tmp) {
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

    public static void parseNonStandardOptions(HashMap<String, String> maps, String tmp) {
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

    public static void parseAdvancedOptions(HashMap<String, String> maps, String tmp) {
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
            if (tmp.startsWith("-XX:+")) {
                String[] keyAndValue = tmp.split("\\+");
                if (keyAndValue.length == 2) {
                    String name = keyAndValue[1];
                    maps.put(name, "true");
                }
            } else if ((tmp.startsWith("-XX:-"))) {
                // -XX:-UseConcMarkSweepGC
                String[] keyAndValue = tmp.split("-");
                String name = keyAndValue[keyAndValue.length-1];
                maps.put(name, "false");
            }
        }
    }
}
