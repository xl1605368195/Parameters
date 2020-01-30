package com.jtrace.zeus.jvm.parameters.data;


import com.jtrace.zeus.jvm.parameters.parameters.JvmParameterEntity;

import java.util.HashSet;


/**
 * @author xule05
 * @date 2020/1/27 下午8:13
 *  阿里云主机到期后，不需要进行数据恢复
 */
public class ParametersRegister {

    public static HashSet<JvmParameterEntity> set = new HashSet<>();

    // https://blog.csdn.net/unix21/article/details/79849485
    static {
        // Standard Options
        // These are the most commonly used options that are supported by all implementations of the JVM.

        // agentlib
        set.add(new JvmParameterEntity(
                "agentlib",
                new String[]{"all"},
                new String[]{"-agentlib:hprof=cpu=samples,interval=20,depth=3", "-agentlib:jdwp=transport=dt_socket,server=y,address=8000"},
                "Standard_Options",
                "all",
                "Loads the specified native agent library. After the library name, a comma-separated list of options specific to the library can be used.",
                "输出虚拟机中GC日志",
                "If the option -agentlib:foo is specified, then the JVM attempts to load the library named libfoo.so in the location specified by the LD_LIBRARY_PATH system variable (on OS X this variable is DYLD_LIBRARY_PATH).",
                "不开启",
                "boolean",
                "-verbose:gc的VM等价参数是-XX:+PrintGC "
        ));

        // agentpath
        set.add(new JvmParameterEntity(
                "agentpath",
                new String[]{"all"},
                new String[]{},
                "Standard_Options",
                "all",
                "Loads the native agent library specified by the absolute path name. This option is equivalent to -agentlib but uses the full path and file name of the library.",
                "",
                "",
                "",
                "",
                ""
        ));

        // client
        set.add(new JvmParameterEntity(
                "client",
                new String[]{"all"},
                new String[]{"-client"},
                "Standard_Options",
                "all",
                "Selects the Java HotSpot Client VM. The 64-bit version of the Java SE Development Kit (JDK) currently ignores this option and instead uses the Server JVM.",
                "",
                "",
                "",
                "",
                "java -version 可以查看启动模式"
        ));

        // server
        set.add(new JvmParameterEntity(
                "server",
                new String[]{"all"},
                new String[]{"-server"},
                "Standard_Options",
                "all",
                "Selects the Java HotSpot Server VM. The 64-bit version of the JDK supports only the Server VM, so in that case the option is implicit.",
                "在多个CPU时性能佳",
                "",
                "",
                "",
                "在具有64位能力的jdk环境下默认启动该模式，忽略配置的-client参数。'java -version'可以查看进程的启动模式"
        ));

        // verbose:gc
        set.add(new JvmParameterEntity(
                "verbose:gc",
                new String[]{"all"},
                new String[]{"-verbose:gc"},
                "Standard_Options",
                "all",
                "Displays information about each garbage collection (GC) event.",
                "输出虚拟机中GC的详细情况",
                "-verbose:gc 输出虚拟机中GC日志，例如:[Full GC 168K->97K(1984K)， 0.0253873 secs],箭头前后的数据168K和97K分别表示垃圾收集GC前后所有存活对象使用的内存容量，说明有168K-97K=71K的对象容量被回收，括号内的数据1984K为堆内存的总容量，收集所需要的时间是0.0253873秒（这个时间在每次执行的时候会有所不同），因此打印的日志不是十分详细，比如GC的时间点就不会打印",
                "false",
                "boolean",
                "-verbose:gc的VM等价参数是-XX:+PrintGC "
        ));

        // -verbose:class
        set.add(new JvmParameterEntity(
                "verbose:class",
                new String[]{"all"},
                new String[]{"-verbose:class"},
                "Standard_Options",
                "all",
                "Displays information about each loaded or unloaded class.",
                "-",
                "-",
                "false",
                "boolean",
                "-verbose:class的VM等价参数是-XX:+TraceClassLoading -XX:+TraceClassUnloading"
        ));

        // -verbose:jni
        set.add(new JvmParameterEntity(
                "verbose:jni",
                new String[]{"all"},
                new String[]{"-verbose:jni"},
                "Standard_Options",
                "all",
                "Displays information about each garbage collection (GC) event.",
                "输出native方法调用的相关情况，一般用于诊断jni调用错误信息。",
                "",
                "false",
                "boolean",
                "-verbose:jni的VM等价参数是-XX:+PrintJNIResolving"
        ));

        // -version
        set.add(new JvmParameterEntity(
                "version",
                new String[]{"all"},
                new String[]{"-version"},
                "Standard_Options",
                "all",
                "Displays version information and then exits. This option is equivalent to the -showversion option except that the latter does not instruct the JVM to exit after displaying version information.",
                "",
                "-",
                "false",
                "boolean",
                ""
        ));

        // -version:release
        set.add(new JvmParameterEntity(
                "version:release",
                new String[]{"all"},
                new String[]{"-version:\"1.6.0_13 1.6* & 1.6.0_10+\""},
                "Standard_Options",
                "all",
                "Specifies the release version to be used for running the application. If the version of the java command called does not meet this specification and an appropriate implementation is found on the system, then the appropriate implementation will be used.",
                "",
                "",
                "false",
                "boolean",
                ""
        ));

        // Non-Standard Options
        // These options are general purpose options that are specific to the Java HotSpot Virtual Machine.

        // -X

        // -Xbatch

        // -Xbootclasspath:path
        set.add(new JvmParameterEntity(
                "Xbootclasspath",
                new String[]{""},
                new String[]{""},
                "Non_Standard_Options",
                "all",
                "",
                "",
                "",
                "",
                "",
                ""
        ));
        // -Xbootclasspath/a:path
        // -Xbootclasspath/p:path
        // -Xcheck:jni
        // -Xcomp
        // -Xdebug
        set.add(new JvmParameterEntity(
                "Xdebug",
                new String[]{"?"},
                new String[]{"-Xdebug"},
                "Non_Standard_Options",
                "all",
                "Does nothing. Provided for backward compatibility.",
                "通知JVM工作在DEBUG模式下",
                "",
                "-",
                "-",
                "-"
        ));
        // -Xdiag
        // -Xfuture
        // -Xint
        // -Xinternalversion

        // -Xloggc:filename
        set.add(new JvmParameterEntity(
                "Xloggc",
                new String[]{"?"},
                new String[]{"-Xloggc:/opt/logs/ipu-learning-report/app.gc.log.201912191644", "-Xloggc:/opt/logs/es_logs/elasticsearch.gc.log"},
                "Non_Standard_Options",
                "all",
                "Sets the file to which verbose GC events information should be redirected for logging. ",
                "将详细GC事件信息重定向指定的日志文件。",
                "",
                "-",
                "String",
                "-Xloggc选项会覆盖-verbose:gc(如果两者同时使用)"
        ));
        // -Xmaxjitcodesize=size

        // -Xmixed

        // -Xmn

        // -Xmssize
        set.add(new JvmParameterEntity(
                "Xms",
                new String[]{"?"},
                new String[]{"-Xms6291456", "-Xms6144k", "-Xms6m", "-Xms4g"},
                "Non_Standard_Options",
                "all",
                "Sets the initial size (in bytes) of the heap. ",
                "设置Heap初始化的大小",
                "If you do not set this option, then the initial size will be set as the sum of the sizes allocated for the old generation and the young generation",
                "-",
                "unsigned int",
                "-Xms的VM等价参数是-XX:InitialHeapSize"
        ));

        // -Xmxsize
        set.add(new JvmParameterEntity(
                "Xmx",
                new String[]{"?"},
                new String[]{"-Xmx83886080", "-Xmx81920k", "-Xmx80m", "-Xmx4g"},
                "Non_Standard_Options",
                "all",
                "Specifies the maximum size (in bytes) of the memory allocation pool in bytes.",
                "Heap的最大值",
                "",
                "-",
                "unsigned int",
                "-Xmx的VM等价参数是-XX:MaxHeapSize."
        ));
        // -Xnoclassgc
        // -Xprof
        // -Xrs
        // -Xshare:mode
        // -XshowSettings:category
        // -Xsssize
        // -Xverify:mode

        // -XX:-DisableExplicitGC
        set.add(new JvmParameterEntity(
                "DisableExplicitGC",
                new String[]{"jdk7,jdk8"},
                new String[]{"-XX:-DisableExplicitGC"},
                "-",
                "all",
                "",
                "默认情况下，此选项处于禁用状态，这意味着忽略来自System.gc()方法触发的GC。",
                "",
                "-",
                "",
                ""
        ));

        // UseConcMarkSweepGC
        set.add(new JvmParameterEntity(
                "UseConcMarkSweepGC",
                new String[]{"-"},
                new String[]{"-XX:-UseConcMarkSweepGC"},
                "-",
                "-",
                "Use concurrent mark-sweep collection for the old generation",
                "允许对老年代使用CMS垃圾收集器",
                "-",
                "不开启",
                "boolean",
                "Oracle建议在不能满足应用程序延迟需求时使用CMS垃圾收集器,当启用此选项时,会自动设置-XX:+UseParNewGC选项，不应该禁用(-XX:-UseParNewGC)。在并且jdk8中已经禁用了以下选项组合:-XX:+UseConcMarkSweepGC -XX:-UseParNewGC"
        ));

        // UseParNewGC
        set.add(new JvmParameterEntity(
                "UseParNewGC",
                new String[]{"-"},
                new String[]{"-XX:+UseParNewGC"},
                "-",
                "-",
                "Enables the use of parallel threads for collection in the young generation.By default, this option is disabled. It is automatically enabled when you set the -XX:+UseConcMarkSweepGC option. Using the -XX:+UseParNewGC option without the -XX:+UseConcMarkSweepGC option was deprecated in JDK 8.",
                "允许对新生代使用parallel垃圾收集器",
                "-",
                "不开启",
                "boolean",
                "当开启-XX:+UseConcMarkSweepGC选项时，-XX:+UseParNewGC将自动启用"
        ));

        // -XX:MaxPermSize
        set.add(new JvmParameterEntity(
                "MaxPermSize",
                new String[]{"jdk7"},
                new String[]{"-XX:MaxPermSize=256M"},
                "-",
                "-",
                "Sets the maximum permanent generation space size (in bytes). This option was deprecated in JDK 8, and superseded by the -XX:MaxMetaspaceSize option.",
                "设置永久带的最大值",
                "-",
                "-",
                "unsigned int",
                "此选项在jdk8中被弃用，并被-XX:MaxMetaspaceSize选项取代。"
        ));

        // -XX:PermSize
        set.add(new JvmParameterEntity(
                "PermSize",
                new String[]{"jdk7"},
                new String[]{"-XX:PermSize=256M"},
                "-",
                "-",
                "Sets the space (in bytes) allocated to the permanent generation that triggers a garbage collection if it is exceeded. This option was deprecated un JDK 8, and superseded by the -XX:MetaspaceSize option.",
                "分配给永久代的空间，如果超过该值，将触发垃圾回收。",
                "-",
                "-",
                "unsigned int",
                "这个选项在jdk8中被弃用，取而代之的是-XX:MetaspaceSize选项。"
        ));

        // -XX:-PrintGC
        set.add(new JvmParameterEntity(
                "PrintGC",
                new String[]{"jdk7"},
                new String[]{"-XX:-PrintGC"},
                "-",
                "-",
                "Enables printing of messages at every GC.",
                "-XX:+PrintGC开启GC日志打印",
                "-",
                "不开启",
                "boolean",
                "-XX:+PrintGC的VM等价参数是-verbose:gc"
        ));

        // 2020.1.30
        // -XX:+UseG1GC
        set.add(new JvmParameterEntity(
                "UseG1GC",
                new String[]{"jdk7"},
                new String[]{"-XX:-UseG1GC"},
                "-",
                "-",
                "Enables printing of messages at every GC.",
                "-XX:+PrintGC开启GC日志打印",
                "-",
                "不开启",
                "boolean",
                "-XX:+PrintGC的VM等价参数是-verbose:gc"
        ));

        // -XX:+HeapDumpOnOutOfMemoryError
        set.add(new JvmParameterEntity(
                "HeapDumpOnOutOfMemoryError",
                new String[]{"all"},
                new String[]{"-XX:+HeapDumpOnOutOfMemoryError"},
                "-",
                "-",
                "Enables printing of messages at every GC.",
                "开启选项后，当JVM发生java.lang.OutOfMemoryError时，自动生成DUMP文件",
                "-",
                "不开启",
                "boolean",
                ""
        ));

        // -XX:+TraceClassLoading
        set.add(new JvmParameterEntity(
                "TraceClassLoading",
                new String[]{"all"},
                new String[]{"-XX:-TraceClassLoading"},
                "-",
                "-",
                "Enables tracing of classes as they are loaded. By default, this option is disabled and classes are not traced.",
                "开启选项后，跟踪类的加载信息",
                "-",
                "不开启",
                "boolean",
                ""
        ));


        // -XX:+TraceClassUnloading
        set.add(new JvmParameterEntity(
                "TraceClassUnloading",
                new String[]{"all"},
                new String[]{"-XX:-TraceClassUnloading"},
                "-",
                "-",
                "Enables tracing of classes as they are unloaded. By default, this option is disabled and classes are not traced.",
                "开启选项后，跟踪类的卸载信息",
                "-",
                "不开启",
                "boolean",
                ""
        ));

        // -XX:ErrorFile
        set.add(new JvmParameterEntity(
                "ErrorFile",
                new String[]{"@since 1.6+"},
                new String[]{"-XX:ErrorFile=filename"},
                "-",
                "-",
                "If an error occurs, save the error data to this file. (Introduced in 6.)",
                "",
                "-",
                "./hs_err_pid<pid>.log",
                "String",
                "-XX:ErrorFile=targetDir/hs_err_pid_%p.log, 可以自定义输出目录"
        ));

        // -Xnoagent
        set.add(new JvmParameterEntity(
                "Xnoagent",
                new String[]{"jdk5"},
                new String[]{"-Xnoagent"},
                "-",
                "-",
                "This is a deprecated option which is accepted and ignored. It need not be specified.wiki：https://www.oracle.com/technetwork/java/javase/tech/faqs-jsp-142584.html#QC4\n" ,
                "jvm启动参数中加入-Xnoagent这个参数不影响agent功能",
                "-",
                "",
                "",
                "-Xnoagent参数主要是JDK早期用于禁止JPDA远程调试用，现在已经弃用了"
        ));

        // https://www.jianshu.com/p/b448c21d2e71
        // -XX:MaxMetaspaceSize
        set.add(new JvmParameterEntity(
                "MaxMetaspaceSize",
                new String[]{"@since1.8+"},
                new String[]{"-XX:MaxMetaspaceSize=256m"},
                "-",
                "-",
                "" ,
                "MaxMetaspaceSize用于设置metaspace区域的最大值",
                "-",
                "-1",
                "",
                ""
        ));

        // -XX:MetaspaceSize
        set.add(new JvmParameterEntity(
                "MetaspaceSize",
                new String[]{"@since1.8+"},
                new String[]{"-XX:MetaspaceSize=256m"},
                "-",
                "-",
                "" ,
                "",
                "-",
                "20M",
                "unsigned int",
                "metaspace的大小表示首次使用不够而触发FGC的阈值，只对触发起作用"
        ));
    }
}