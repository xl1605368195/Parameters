package com.jtrace.zeus.jvm.parameters.parameters;


import java.util.HashSet;


/**
 * @author xule05
 * @date 2020/1/27 下午8:13
 */
public class ParametersRegister {

    public static HashSet<JvmParameterEntity> set = new HashSet<>();

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
                "输出虚拟机中GC的详细情况",
                "If the option -agentlib:foo is specified, then the JVM attempts to load the library named libfoo.so in the location specified by the LD_LIBRARY_PATH system variable (on OS X this variable is DYLD_LIBRARY_PATH).",
                "false",
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
                "",
                "",
                "",
                "",
                "java -version 可以查看启动模式"
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
                "-verbose:gc 输出虚拟机中GC的详细情况，例如:[Full GC 168K->97K(1984K)， 0.0253873 secs],箭头前后的数据168K和97K分别表示垃圾收集GC前后所有存活对象使用的内存容量，说明有168K-97K=71K的对象容量被回收，括号内的数据1984K为堆内存的总容量，收集所需要的时间是0.0253873秒（这个时间在每次执行的时候会有所不同），因此打印的日志不是十分详细，比如GC的时间点就不会打印",
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
    }
}