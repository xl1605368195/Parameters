package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.rules.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author xule05
 * @date 2020/1/30 下午11:50
 */
@Service
public class ParametersEvaluateService {

    /**
     * step1:参数分解与过滤
     */
    public String splitParameters(String parameters) {
        // String s = "java -server -Xms2g -Xmx2g -Xss1m -XX:+UseConcMarkSweepGC -XX:MaxMetaspaceSize=256M -XX:NewRatio=4 -Xloggc:/usr/local/tools/software/parameters/jvm-parameters.20200129.gc.log -XX:ErrorFile=/usr/local/tools/software/parameters/jvm-parameters.20200129.vmerr.log -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:+AlwaysPreTouch -XX:CMSFullGCsBeforeCompaction=5 -XX:+UseCMSCompactAtFullCollection -XX:+PrintGCApplicationStoppedTime -XX:+PrintTenuringDistribution -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintClassHistogram -jar parameters-0.0.1-SNAPSHOT.jar";
        // String s0="/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/jre/bin/java -Djava.io.tmpdir=/var/folders/z3/tvwybsv11lxbj9fn4_3ktj_80000gn/T/ -Djetty.home=/usr/local/jetty-distribution-9.4.19.v20190610 -Djetty.base=/usr/local/jetty-distribution-9.4.19.v20190610 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8084 -Xmx2000m -Xmn512m -XX:+UseConcMarkSweepGC -XX:ParallelCMSThreads=2 -XX:+CMSClassUnloadingEnabled -cp /usr/local/jetty-distribution-9.4.19.v20190610/lib/mail/javax.mail.glassfish-1.4.1.v201005082020.jar:/usr/local/jetty-distribution-9.4.19.v20190610/resources:/usr/local/jetty-distribution-9.4.19.v20190610/lib/servlet-api-3.1.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-schemas-3.1.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-http-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-server-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-xml-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-util-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-io-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-jndi-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-security-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/transactions/javax.transaction-api-1.3.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-servlet-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-webapp-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-plus-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-annotations-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/annotations/asm-7.1.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/annotations/asm-analysis-7.1.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/annotations/asm-commons-7.1.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/annotations/asm-tree-7.1.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/annotations/javax.annotation-api-1.3.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/apache-jsp/org.eclipse.jdt.ecj-3.17.0.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/apache-jsp/org.eclipse.jetty.apache-jsp-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/apache-jsp/org.mortbay.jasper.apache-el-8.5.40.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/apache-jsp/org.mortbay.jasper.apache-jsp-8.5.40.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/apache-jstl/org.apache.taglibs.taglibs-standard-impl-1.2.5.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/apache-jstl/org.apache.taglibs.taglibs-standard-spec-1.2.5.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-client-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/jetty-deploy-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/websocket/javax.websocket-api-1.0.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/websocket/javax-websocket-client-impl-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/websocket/javax-websocket-server-impl-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/websocket/websocket-api-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/websocket/websocket-client-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/websocket/websocket-common-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/websocket/websocket-server-9.4.19.v20190610.jar:/usr/local/jetty-distribution-9.4.19.v20190610/lib/websocket/websocket-servlet-9.4.19.v20190610.jar org.eclipse.jetty.xml.XmlConfiguration /var/folders/z3/tvwybsv11lxbj9fn4_3ktj_80000gn/T/start_2748467983909975633.properties /usr/local/jetty-distribution-9.4.19.v20190610/etc/jetty-bytebufferpool.xml /usr/local/jetty-distribution-9.4.19.v20190610/etc/jetty-threadpool.xml /usr/local/jetty-distribution-9.4.19.v20190610/etc/jetty.xml /usr/local/jetty-distribution-9.4.19.v20190610/etc/jetty-webapp.xml /usr/local/jetty-distribution-9.4.19.v20190610/etc/jetty-plus.xml /usr/local/jetty-distribution-9.4.19.v20190610/etc/jetty-annotations.xml /usr/local/jetty-distribution-9.4.19.v20190610/etc/jetty-deploy.xml /usr/local/jetty-distribution-9.4.19.v20190610/etc/home-base-warning.xml /usr/local/jetty-distribution-9.4.19.v20190610/etc/jetty-http.xml";
        HashSet<String> keyAndValueSet = new HashSet<>();
        String[] array = parameters.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String tmp : array) {
            if (!tmp.startsWith("-") || tmp.startsWith("-D")) {
                continue;
            } else {
                stringBuilder.append(tmp);
            }
        }
        return stringBuilder.toString();
    }

    public HashSet<Parameter> createParameters(String parameters) {
        String[] array = parameters.split(" ");
        HashSet<Parameter> sets = new HashSet<>();
        HashMap<String, String> maps = new HashMap<>();
        // 堆参数检测
        // -XX,-X,-
        for (String tmp : array) {
            if (tmp.startsWith("-XX")) {
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
                        String[] keyAndValue = tmp.split("\\-");
                        if (keyAndValue.length == 2) {
                            String name = keyAndValue[1];
                            maps.put(name, "false");
                        }
                    } else {
                        System.out.println("参数不符合预期：" + tmp);
                    }
                }
            } else if (tmp.startsWith("-X")) {
                // -Xmn512m
                // -Xmx2000m

            } else if (tmp.startsWith("-")) {
                // -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8084

            } else {
                System.out.println("参数不符合预期：" + tmp);
            }
        }
        return null;
    }

    /**
     * 创建堆参数
     *
     * @param parameters 原始字符串
     * @param result
     */
    public void createHeapParameters(String parameters, HashSet<Parameter> result) {
        //
        if (parameters.contains("-Xmn")) {

        }

        // Heap的最大值
        if (parameters.contains("-Xmx") || parameters.contains("-XX:MaxHeapSize")) {

        }
    }

    public Map<String, String> parseFlags(String flags) {
        Map<String, String> result = new HashMap<>(256);
        String[] parameters = flags.split(" ");
        for (String parameter : parameters) {
            String[] strings = parameter.split(":");
            if (strings.length == 2) {
                if (StringUtils.isNotBlank(strings[0]) && strings[0].startsWith("-XX")) {
                    String[] pairs = strings[1].split("=");
                    if (pairs.length == 2) {
                        result.put(pairs[0], pairs[1]);
                    } else if (pairs.length == 1) {
                        result.put(pairs[0], String.valueOf(true));
                    }
                } else {
                    result.put(parameter.trim(), String.valueOf(true));
                }
            } else {
                result.put(parameter.trim(), String.valueOf(true));
            }
        }
        return result;
    }
}
