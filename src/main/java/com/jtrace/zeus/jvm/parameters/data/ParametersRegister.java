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
                new String[]{"all jdk"},
                new String[]{"-agentlib:jdwp=transport=dt_socket,server=y,address=8000"},
                "Standard_Options",
                "-",
                "Loads the specified native agent library. After the library name, a comma-separated list of options specific to the library can be used.",
                "通过相对路径加载一个由c/c++语言编写的agent包",
                "",
                "",
                "boolean",
                "与agentpath功能相同，只是加载native agent路径不同"
        ));

        // agentpath
        set.add(new JvmParameterEntity(
                "agentpath",
                new String[]{"all jdk"},
                new String[]{""},
                "Standard_Options",
                "all",
                "Loads a native agent library by full pathname",
                "通过全路径加载一个由c/c++语言编写的agent包",
                "",
                "",
                "boolean",
                "与agentlib功能相同，只是加载agent的路径不同"
        ));

        // client
        set.add(new JvmParameterEntity(
                "client",
                new String[]{"all jdk"},
                new String[]{"-client"},
                "Standard_Options",
                "all",
                "Selects the Java HotSpot Client VM. The 64-bit version of the Java SE Development Kit (JDK) currently ignores this option and instead uses the Server JVM.",
                "设置jvm使用client模式，特点是启动速度比较快，但运行时性能和内存管理效率不高,通常用于客户端应用程序或者PC应用开发和调试",
                "",
                "",
                "",
                "java -version 可以查看启动模式"
        ));

        // -D
        set.add(new JvmParameterEntity(
                "D",
                new String[]{"all jdk"},
                new String[]{"-Djetty.port=8888,设置jetty.port属性的值为8888"},
                "Standard_Options",
                "—",
                "Sets a system property value. The property variable is a string with no spaces that represents the name of the property. The value variable is a string that represents the value of the property. If value is a string with spaces, then enclose it in quotation marks .",
                "设置系统属性",
                "启动Java程序传递参数的正确姿势: -DkeyName=value,在程序中使用System.getProperties(keyName)获取系统属性值",
                "",
                "String",
                "如果系统属性值是一个包含空格的字符串，那么需要包在一对双引号中，例如：-Dfoo=\"some string\""
        ));

        // -jar
        set.add(new JvmParameterEntity(
                "jar",
                new String[]{"all jdk"},
                new String[]{""},
                "Standard_Options",
                "all",
                "",
                "以jar包的形式执行一个应用程序",
                "使用该参数的正确姿势：java -jar a.jar",
                "",
                "",
                ""
        ));

        // -javaagent:
        set.add(new JvmParameterEntity(
                "javaagent",
                new String[]{"all jdk"},
                new String[]{" -javaagent:xxx.jar=name=ty&age=30"},
                "Standard_Options",
                "all",
                "Loads the specified Java programming language agent.",
                "加载一个由Java语言编写的agent包",
                "使用该参数的正确姿势: -javaagent:jarpath[=options]",
                "",
                "",
                ""
        ));

        // server
        set.add(new JvmParameterEntity(
                "server",
                new String[]{"all jdk"},
                new String[]{"-server"},
                "Standard_Options",
                "all",
                "Selects the Java HotSpot Server VM. The 64-bit version of the JDK supports only the Server VM, so in that case the option is implicit.",
                "设置jvm处于server模式，特点是启动速度比较慢，但运行时性能和内存管理效率很高，适用于生产环境",
                "",
                "",
                "",
                "在64位的jdk中默认启动server模式，并忽略配置的-client参数"
        ));

        // verbose:gc
        set.add(new JvmParameterEntity(
                "verbose:gc",
                new String[]{"all jdk"},
                new String[]{""},
                "Standard_Options",
                "all",
                "Displays information about each garbage collection (GC) event.",
                "输出虚拟机中GC的日志",
                "开启该选项的正确姿势: -verbose:gc",
                "默认关闭",
                "boolean",
                "-verbose:gc的VM等价参数是-XX:+PrintGC"
        ));

        // -verbose:class
        set.add(new JvmParameterEntity(
                "verbose:class",
                new String[]{"all jdk"},
                new String[]{""},
                "Standard_Options",
                "all",
                "Displays information about each loaded or unloaded class.",
                "输出jvm类的加载与卸载信息",
                "开启这个选项可以查看某个类是否加载以及从哪个jar加载",
                "默认关闭",
                "boolean",
                "-verbose:class的VM等价参数是-XX:+TraceClassLoading -XX:+TraceClassUnloading"
        ));

        // -verbose:jni
        set.add(new JvmParameterEntity(
                "verbose:jni",
                new String[]{"all jdk"},
                new String[]{""},
                "Standard_Options",
                "all",
                "Displays information about each garbage collection (GC) event.",
                "输出native方法调用的相关情况，一般用于诊断jni调用错误信息。",
                "开启该选项的正确姿势: -verbose:jni",
                "默认关闭",
                "boolean",
                "-verbose:jni的VM等价参数是-XX:+PrintJNIResolving"
        ));

        // -version
        set.add(new JvmParameterEntity(
                "version",
                new String[]{"all jdk"},
                new String[]{""},
                "Standard_Options",
                "all",
                "Displays version information and then exits. This option is equivalent to the -showversion option except that the latter does not instruct the JVM to exit after displaying version information.",
                "",
                "",
                "默认关闭",
                "boolean",
                ""
        ));

        // -version:release
        set.add(new JvmParameterEntity(
                "version:release",
                new String[]{"all jdk"},
                new String[]{""},
                "Standard_Options",
                "all",
                "Specifies the release version to be used for running the application. If the version of the java command called does not meet this specification and an appropriate implementation is found on the system, then the appropriate implementation will be used.",
                "",
                "",
                "默认关闭",
                "boolean",
                ""
        ));

        // Non-Standard Options
        // These options are general purpose options that are specific to the Java HotSpot Virtual Machine.

        // -Xbootclasspath:path
        set.add(new JvmParameterEntity(
                "Xbootclasspath",
                new String[]{"all jdk"},
                new String[]{""},
                "Non_Standard_Options",
                "all",
                "",
                "完全取代核心的class搜索路径。不常用,否则要重写所有Java核心class",
                "",
                "",
                "",
                "禁用"
        ));
        // -Xbootclasspath/a:path
        set.add(new JvmParameterEntity(
                "Xbootclasspath/a",
                new String[]{"all jdk"},
                new String[]{""},
                "Non_Standard_Options",
                "all",
                "",
                "path指定的路径仅次于核心class搜索路径",
                "",
                "",
                "",
                "常用"
        ));
        // -Xbootclasspath/p:path
        set.add(new JvmParameterEntity(
                "Xbootclasspath/p",
                new String[]{"all jdk"},
                new String[]{"-Xbootclasspath/p:path"},
                "Non_Standard_Options",
                "all",
                "",
                "path指定的路径在核心class搜索路径前",
                "",
                "",
                "",
                "禁用"
        ));
        // -Xcheck:jni
        set.add(new JvmParameterEntity(
                "Xcheck",
                new String[]{"all jdk"},
                new String[]{"-Xcheck:jni"},
                "Non_Standard_Options",
                "all",
                "",
                "",
                "",
                "",
                "",
                ""
        ));
        // -Xcomp
        set.add(new JvmParameterEntity(
                "Xcomp",
                new String[]{"all jdk"},
                new String[]{"-Xcomp"},
                "Non_Standard_Options",
                "all",
                "",
                "",
                "",
                "",
                "",
                ""
        ));
        // -Xdebug
        set.add(new JvmParameterEntity(
                "Xdebug",
                new String[]{"all jdk"},
                new String[]{"-Xdebug"},
                "Non_Standard_Options",
                "all",
                "Does nothing. Provided for backward compatibility.",
                "通知JVM工作在DEBUG模式下",
                "",
                "",
                "",
                ""
        ));
        // -Xdiag
        // -Xfuture
        // -Xint
        // -Xinternalversion

        // todo
        // -Xloggc:filename
        set.add(new JvmParameterEntity(
                "Xloggc",
                new String[]{"all jdk"},
                new String[]{"-Xloggc:/app/logs/elasticsearch_%t.gc.log"},
                "Non_Standard_Options",
                "all",
                "Sets the file to which verbose GC events information should be redirected for logging. ",
                "将详细GC事件信息重定向指定的日志文件。",
                "",
                "",
                "String",
                "-Xloggc选项会覆盖-verbose:gc(如果两者同时使用); -Xloggc的文件名称使用`%t`可以附加文件创建的时间,偏于排查问题"
        ));
        // -Xmaxjitcodesize=size

        // -Xmixed

        // -Xmn
        set.add(new JvmParameterEntity(
                "Xmn",
                new String[]{"all jdk"},
                new String[]{"-Xmn256m"},
                "Non_Standard_Options",
                "all",
                "Sets the initial and maximum size of the heap for the young generation",
                "设置新生代的初始容量和最大容量",
                "使用此参数的正确姿势:-Xmn___",
                "",
                "unsigned int",
                "建议新生代的大小保持整个堆的大小的25%~50%之间;-Xmn的VM等价参数为 -XX:NewSize(新生代初始值) 和 -XX:MaxNewSize(新生代最大值)"
        ));

        // -Xms
        set.add(new JvmParameterEntity(
                "Xms",
                new String[]{"all jdk"},
                new String[]{"-Xms6291456", "-Xms6144k", "-Xms6m", "-Xms4g"},
                "Non_Standard_Options",
                "all",
                "Sets the initial size (in bytes) of the heap. ",
                "设置Heap初始化的大小",
                "使用此参数的正确姿势:-Xms___",
                "",
                "unsigned int",
                "-Xms的VM等价参数是-XX:InitialHeapSize"
        ));

        // -Xmx
        set.add(new JvmParameterEntity(
                "Xmx",
                new String[]{"all jdk"},
                new String[]{"-Xmx83886080", "-Xmx81920k", "-Xmx80m", "-Xmx4g"},
                "Non_Standard_Options",
                "-",
                "Specifies the maximum size (in bytes) of the memory allocation pool in bytes.",
                "设置Java堆的最大值",
                "使用此参数的正确姿势:-Xmx___",
                "",
                "unsigned int",
                "-Xmx的VM等价参数是-XX:MaxHeapSize."
        ));

        // -Xnoclassgc
        set.add(new JvmParameterEntity(
                "Xnoclassgc",
                new String[]{"all jdk"},
                new String[]{"-Xnoclassgc"},
                "-",
                "-",
                "Disables garbage collection (GC) of classes. This can save some GC time, which shortens interruptions during the application run.",
                "禁用Class对象的垃圾收集（GC）",
                "",
                "默认关闭",
                "",
                "开启-Xnoclassgc时，应用程序中的Class对象将在GC期间保持不变，并且将始终被视为活动的。这会导致更多的内存被永久占用，如果不小心使用，将引发内存不足异常"
        ));

        // Xprof
        // -Xrs
        // -Xshare:mode
        // -XshowSettings:category

        // -Xss
        set.add(new JvmParameterEntity(
                "Xss",
                new String[]{"all jdk"},
                new String[]{"-Xss1024k","-Xss1m"},
                "-",
                "-",
                "Sets the thread stack size",
                "设置Java线程栈的大小",
                "使用此参数的正确姿势:-Xss___",
                "默认值为1m",
                "",
                "-Xss的VM等价参数是-XX:ThreadStackSize"
        ));
        // Xusealtsigs

        // -Xverify:mode

//        Advanced Runtime Options
//        These options control the runtime behavior of the Java HotSpot VM.

        // -XX:+CheckEndorsedAndExtDirs

        // -XX:+DisableAttachMechanism
        set.add(new JvmParameterEntity(
                "DisableAttachMechanism",
                new String[]{"@since 1.6+"},
                new String[]{"-XX:+DisableAttachMechanism"},
                "-",
                "-",
                "Enables the option that disables the mechanism that lets tools attach to the JVM. By default, this option is disabled, meaning that the attach mechanism is enabled and you can use tools such as jcmd, jstack, jmap, and jinfo.",
                "开启此选项将禁止attach到目标JVM，默认这个选项是关闭的",
                "你可以通过-XX:+DisableAttachMechanism禁止attach，或者-XX:-DisableAttachMechanism允许attach",
                "默认关闭",
                "boolean",
                "默认情况下，允许attach连接到目标JVM，所以你可以使用jcmd、jstack、jmap和jinfo等命令"
        ));

        // -XX:ErrorFile
        set.add(new JvmParameterEntity(
                "ErrorFile",
                new String[]{"@since 1.6+"},
                new String[]{"-XX:ErrorFile=./jvm-parameters.20200129.vmerr.log"},
                "-",
                "-",
                "If an error occurs, save the error data to this file.",
                "输出jvm错误信息到指定文件",
                "使用该参数的正确姿势是-XX:ErrorFile=___",
                "默认值 ./hs_err_pid<pid>.log",
                "String",
                "-XX:ErrorFile=targetDir/hs_err_pid_%p.log, 可以自定义输出目录;如果这个文件不能被创建在指定的目录下的话（因为空间不足、权限问题、或者其他问题）。这个文件就会创建在临时目录，临时目录的值通过TMP环境变量指定，如果这个环境变量没有定义，那么就是用TEMP环境变量 "
        ));

        // -XX:+FailOverToOldVerifier
        set.add(new JvmParameterEntity(
                "FailOverToOldVerifier",
                new String[]{"@since 1.6+"},
                new String[]{""},
                "-",
                "-",
                "Enables automatic failover to the old verifier when the new type checker fails. By default, this option is disabled and it is ignored (that is, treated as disabled) for classes with a recent bytecode version. You can enable it for classes with older versions of the bytecode",
                "开启时如果新的Class校验器检查失败，则使用老的校验器",
                "你可以通过-XX:+FailOverToOldVerifier开启，或者-XX:-FailOverToOldVerifier关闭",
                "默认关闭",
                "boolean",
                "因为JDK6最高向下兼容到JDK1.2，而JDK1.2的class info与JDK6的info存在较大的差异，所以新校验器可能会出现校验失败的情况"
        ));

        // -XX:+FlightRecorder
        set.add(new JvmParameterEntity(
                "FlightRecorder",
                new String[]{"@since 1.7+"},
                new String[]{""},
                "-",
                "-",
                "",
                "开启JFR功能，Java Flight Recorder是JVM内置的记录引擎，能收集JVM和Java应用数据，帮助开发者诊断分析Java应用",
                "你可以通过-XX:+FlightRecorder开启，或者-XX:-FlightRecorder关闭",
                "默认关闭",
                "boolean",
                "生成环境开启需要授权，与-XX:+UnlockCommercialFeature一起使用"
        ));

        // -XX:LargePageSizeInByte
        set.add(new JvmParameterEntity(
                "LargePageSizeInByte",
                new String[]{"all jdk"},
                new String[]{"-XX:LargePageSizeInByte=4m"},
                "-",
                "-",
                "",
                "设置堆的内存页大小",
                "使用此参数的正确姿势:-XX:LargePageSizeInByte=____",
                "默认值为0",
                "",
                ""
        ));

        // -XX:MaxDirectMemorySize
        set.add(new JvmParameterEntity(
                "MaxDirectMemorySize",
                new String[]{"all jdk"},
                new String[]{"-XX:MaxDirectMemorySize=1g"},
                "-",
                "-",
                "Sets the maximum total size (in bytes) of the New I/O (the java.nio package) direct-buffer allocations. By default, the size is set to 0, meaning that the JVM chooses the size for NIO direct-buffer allocations automatically.",
                "指定最大可用堆外内存, 该参数并不会立即占用指定的内存, 是限制程序最大可使用的堆外内存, 建议尽量写大一点",
                "使用此参数的正确姿势:-XX:MaxDirectMemorySize=____",
                "默认值为0",
                "",
                "没显式配置该值的时候，NIO direct memory可申请的空间的上限就是-Xmx减去一个survivor space的预留大小"
        ));

        // -XX:ThreadStackSize
        set.add(new JvmParameterEntity(
                "ThreadStackSize",
                new String[]{"all jdk"},
                new String[]{"-XX:ThreadStackSize=1024k","-XX:ThreadStackSize=1m"},
                "-",
                "-",
                "Sets the thread stack size",
                "设置Java线程栈的大小",
                "使用此参数的正确姿势:-XX:ThreadStackSize=____",
                "默认值为1024k",
                "",
                "-XX:ThreadStackSize的VM等价参数是-Xss"
        ));

        // -XX:MaxPermSize
        set.add(new JvmParameterEntity(
                "MaxPermSize",
                new String[]{"deprecated in 1.8"},
                new String[]{"-XX:MaxPermSize=256M"},
                "-",
                "-",
                "Sets the maximum permanent generation space size (in bytes). This option was deprecated in JDK 8, and superseded by the -XX:MaxMetaspaceSize option.",
                "设置永久代的最大值",
                "使用此参数的正确姿势:-XX:MaxPermSize=____",
                "大部分情况下默认值是64MB",
                "unsigned int",
                "此选项在jdk8中被弃用，并被-XX:MaxMetaspaceSize选项取代。"
        ));

        // -XX:PermSize
        set.add(new JvmParameterEntity(
                "PermSize",
                new String[]{"deprecated in 1.8"},
                new String[]{"-XX:PermSize=256M"},
                "-",
                "-",
                "Sets the space (in bytes) allocated to the permanent generation that triggers a garbage collection if it is exceeded. This option was deprecated un JDK 8, and superseded by the -XX:MetaspaceSize option.",
                "分配给永久代的空间，如果超过该值，将触发垃圾回收。",
                "使用此参数的正确姿势:-XX:PermSize=____",
                "默认值为64m",
                "unsigned int",
                "这个选项在jdk8中被弃用，取而代之的是-XX:MetaspaceSize选项"
        ));
        // PrintFlagsInitial

        // -XX:+HeapDumpOnOutOfMemoryError
        set.add(new JvmParameterEntity(
                "HeapDumpOnOutOfMemoryError",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables printing of messages at every GC.",
                "开启选项后，当JVM发生java.lang.OutOfMemoryError时，自动生成DUMP文件",
                "你可以通过-XX:+HeapDumpOnOutOfMemoryError开启，或者-XX:-HeapDumpOnOutOfMemoryError关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:+TraceClassLoading
        set.add(new JvmParameterEntity(
                "TraceClassLoading",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables tracing of classes as they are loaded. By default, this option is disabled and classes are not traced.",
                "开启选项后，跟踪类的加载信息",
                "你可以通过-XX:+TraceClassLoading开启，或者-XX:-TraceClassLoading关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:+TraceClassUnloading
        set.add(new JvmParameterEntity(
                "TraceClassUnloading",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables tracing of classes as they are unloaded. By default, this option is disabled and classes are not traced.",
                "开启该选项后，跟踪类的卸载信息",
                "你可以通过-XX:+TraceClassUnloading开启，或者-XX:-TraceClassUnloading关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -Xnoagent
        set.add(new JvmParameterEntity(
                "Xnoagent",
                new String[]{"deprecated in 1.5"},
                new String[]{""},
                "-",
                "-",
                "This is a deprecated option which is accepted and ignored. It need not be specified.wiki：https://www.oracle.com/technetwork/java/javase/tech/faqs-jsp-142584.html#QC4\n" ,
                "禁用默认的sun.tools.debug调试器",
                "",
                "",
                "",
                "-Xnoagent参数主要是JDK早期用于禁止JPDA远程调试用，现在已经弃用了"
        ));
        //=============Advanced Garbage Collection Options============================================
        // -XX:ActiveProcessorCount=x
        set.add(new JvmParameterEntity(
                "ActiveProcessorCount",
                new String[]{"@since 1.8+"},
                new String[]{""},
                "-",
                "-",
                "Overrides the number of CPUs that the VM uses to calculate the size of thread pools it uses for various operations such as Garbage Collection and ForkJoinPool." ,
                "指定jvm使用cpu个数，替代实际检测到的物理机CPU核数",
                "使用此参数的正确姿势:-XX:ActiveProcessorCount=____",
                "默认值 -1",
                "boolean",
                "仅限于在Docker中使用"
        ));

        // -XX:+AggressiveHeap
        set.add(new JvmParameterEntity(
                "AggressiveHeap",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables Java heap optimization. This sets various parameters to be optimal for long-running jobs with intensive memory allocation, based on the configuration of the computer (RAM and CPU). By default, the option is disabled and the heap is not optimized.\n" ,
                "开启此参数让jvm忽略Xmx参数，疯狂地占用物理内存和swap区",
                "你可以通过-XX:+AggressiveHeap开启，或者-XX:-AggressiveHeap关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:+AlwaysPreTouch
        set.add(new JvmParameterEntity(
                "AlwaysPreTouch",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables touching of every page on the Java heap during JVM initialization. This gets all pages into the memory before entering the main() method. The option can be used in testing to simulate a long-running system with all virtual memory mapped to physical memory. By default, this option is disabled and all pages are committed as JVM heap space fills." ,
                "JVM启动时使用所有可用的内存分页",
                "你可以通过-XX:+AlwaysPreTouch开启，或者-XX:-AlwaysPreTouch关闭",
                "默认关闭",
                "boolean",
                "如果堆内存较大(>=4G)，可添加 -XX:+AlwaysPreTouch 参数强制JVM在启动时分配内存，可使得后续运行更顺畅（副作用：启动速度变慢）"
        ));

        // -XX:+CMSClassUnloadingEnabled
        set.add(new JvmParameterEntity(
                "CMSClassUnloadingEnabled",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables class unloading when using the concurrent mark-sweep (CMS) garbage collector. This option is enabled by default. To disable class unloading for the CMS garbage collector, specify -XX:-CMSClassUnloadingEnabled." ,
                "使用CMS垃圾收集器时启用类卸载",
                "你可以通过-XX:+CMSClassUnloadingEnabled开启，或者-XX:-CMSClassUnloadingEnabled关闭",
                "默认开启",
                "boolean",
                "默认情况下，此选项处于启用状态。要禁用CMS垃圾收集器的类卸载，请指定-XX:-CMSClassUnloadingEnabled"
        ));

        // -XX:CMSExpAvgFactor=percent
        set.add(new JvmParameterEntity(
                "CMSExpAvgFactor",
                new String[]{"all jdk"},
                new String[]{"-XX:CMSExpAvgFactor=15"},
                "-",
                "-",
                "Sets the percentage of time (0 to 100) used to weight the current sample when computing exponential averages for the concurrent collection statistics. By default, the exponential averages factor is set to 25%." ,
                "设置一个时间百分比，用来加权并发回收统计的指数平均的样本",
                "使用此参数的正确姿势:-XX:CMSExpAvgFactor=____",
                "默认值为25",
                "unsigned int",
                ""
        ));

        // -XX:CMSInitiatingOccupancyFraction
        set.add(new JvmParameterEntity(
                "CMSInitiatingOccupancyFraction",
                new String[]{"all jdk"},
                new String[]{"-XX:CMSInitiatingOccupancyFraction=80"},
                "-",
                "-",
                "Sets the percentage of the old generation occupancy (0 to 100) at which to start a CMS collection cycle. The default value is set to -1. Any negative value (including the default) implies that -XX:CMSTriggerRatio is used to define the value of the initiating occupancy fraction." ,
                "设置年老代的内存占用率,当老年代达到该值时，触发CMS垃圾回收",
                "使用此参数的正确姿势:-XX:CMSInitiatingOccupancyFraction=____",
                "默认值为-1",
                "unsigned int",
                "如果CMSInitiatingOccupancyFraction的值为-1，那么使用MinHeapFreeRatio和CMSTriggerRatio的计算结果来决定触发CMS的条件，默认计算值为92%"
        ));

        // -XX:+CMSScavengeBeforeRemark
        set.add(new JvmParameterEntity(
                "CMSScavengeBeforeRemark",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables scavenging attempts before the CMS remark step. By default, this option is disabled." ,
                "在CMS GC前启动一次YGC，能够减少新生代对象对老年代对象的引用，降低remark的时间开销",
                "你可以通过-XX:+CMSScavengeBeforeRemark开启，或者-XX:-CMSScavengeBeforeRemark关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:CMSTriggerRatio=percent
        set.add(new JvmParameterEntity(
                "CMSTriggerRatio",
                new String[]{"all jdk"},
                new String[]{"-XX:CMSTriggerRatio=75"},
                "-",
                "-",
                "Sets the percentage (0 to 100) of the value specified by -XX:MinHeapFreeRatio that is allocated before a CMS collection cycle commences. The default value is set to 80%." ,
                "设置一个在CMS开始前的内存的触发百分比，针对的是由-XX:MinHeapFreeRatio分配的内存",
                "使用此参数的正确姿势:-XX:CMSTriggerRatio=____",
                "默认值为80",
                "unsigned int",
                "没有设定CMSInitiatingOccupancyFraction值时(该值默认为-1)，当老年代达到 ((100 - MinHeapFreeRatio) + (double)(CMSTriggerRatio * MinHeapFreeRatio)/100.0)/100.0 = 92% 触发CMS回收"
        ));

        // -XX:ConcGCThreads=threads
        set.add(new JvmParameterEntity(
                "ConcGCThreads",
                new String[]{"all jdk"},
                new String[]{"-XX:ConcGCThreads=2"},
                "-",
                "-",
                "Sets the number of threads used for concurrent GC. The default value depends on the number of CPUs available to the JVM" ,
                "设置用于并发GC的线程数",
                "",
                "默认值取决于JVM可用的cpu数量",
                "unsigned int",
                "以CMS GC为例，-XX:ConcGCThreads是指并发阶段例如：并发标记，标记清理，标记重置时GC线程数。"
        ));

        // -XX:+DisableExplicitGC
        set.add(new JvmParameterEntity(
                "DisableExplicitGC",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables the option that disables processing of calls to System.gc(). This option is disabled by default, meaning that calls to System.gc() are processed. If processing of calls to System.gc() is disabled, the JVM still performs GC when necessary",
                "默认情况下，此选项处于开启状态，这意味着忽略来自System.gc()方法触发的GC。",
                "你可以通过-XX:+DisableExplicitGC开启，或者-XX:-DisableExplicitGC关闭",
                "默认开启",
                "boolean",
                "",
                "/articles/2020/02/16/1581833097466.html"
        ));

        // -XX:+ExplicitGCInvokesConcurrent
        set.add(new JvmParameterEntity(
                "ExplicitGCInvokesConcurrent",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables invoking of concurrent GC by using the System.gc() request. This option is disabled by default and can be enabled only together with the -XX:+UseConcMarkSweepGC option.",
                "System.gc()原本会触发一次full gc,开启该选项之后，老年代的full gc变成了cms gc。该参数一定要和-XX:+UseConcMarkSweepGC一起使用",
                "你可以通过-XX:+ExplicitGCInvokesConcurrent开启，或者-XX:-ExplicitGCInvokesConcurrent关闭",
                "默认关闭",
                "boolean",
                "该选项仅对System.gc()触发的full gc起作用"
        ));

        // -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses
        set.add(new JvmParameterEntity(
                "ExplicitGCInvokesConcurrentAndUnloadsClasses",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables invoking of concurrent GC by using the System.gc() request and unloading of classes during the concurrent GC cycle. This option is disabled by default and can be enabled only together with the -XX:+UseConcMarkSweepGC option.",
                "System.gc()原本会触发一次full gc,开启该选项之后触发一次cms gc并且卸载类。该参数一定要和-XX:+UseConcMarkSweepGC一起使用。",
                "你可以通过-XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses开启，或者-XX:-ExplicitGCInvokesConcurrentAndUnloadsClasses关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:G1HeapRegionSize=size
        set.add(new JvmParameterEntity(
                "G1HeapRegionSize",
                new String[]{"all jdk"},
                new String[]{"-XX:G1HeapRegionSize=2m"},
                "-",
                "-",
                "Sets the size of the regions into which the Java heap is subdivided when using the garbage-first (G1) collector. The value can be between 1 MB and 32 MB. The default region size is determined ergonomically based on the heap size",
                "设置region的大小",
                "使用此参数的正确姿势:-XX:G1HeapRegionSize=____",
                "默认值与堆大小相关",
                "unsigned int",
                "每个region大小，取值在1MB~32MB之间且是2的次方，region个数不超过2048个，调整参数使得region个数尽可能多。不设置时，默认会根据Java堆的大小自动检测"
        ));

        // -XX:+G1PrintHeapRegions
        set.add(new JvmParameterEntity(
                "G1PrintHeapRegions",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables the printing of information about which regions are allocated and which are reclaimed by the G1 collector. By default, this option is disabled.",
                "输出regions被分配和回收的信息",
                "你可以通过-XX:+G1PrintHeapRegions开启，或者-XX:-G1PrintHeapRegions关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // todo
        // -XX:G1ReservePercent=percent
        set.add(new JvmParameterEntity(
                "G1ReservePercent",
                new String[]{"all jdk"},
                new String[]{"-XX:G1ReservePercent=10"},
                "-",
                "-",
                "Enables the printing of information about which regions are allocated and which are reclaimed by the G1 collector. By default, this option is disabled.",
                "设置一个堆内存的保留空间比例，从而降低使用G1时晋升失败的可能性。",
                "使用此参数的正确姿势:-XX:G1ReservePercent=____",
                "默认值为10",
                "unsigned int",
                ""
        ));

        // -XX:InitialHeapSize
        set.add(new JvmParameterEntity(
                "InitialHeapSize",
                new String[]{"all jdk"},
                new String[]{"-XX:InitialHeapSize=2g"},
                "-",
                "-",
                "Sets the number of threads used for concurrent GC. The default value depends on the number of CPUs available to the JVM" ,
                "设置Java堆初始大小",
                "使用此参数的正确姿势:-XX:InitialHeapSize=____",
                "?",
                "unsigned int",
                "-XX:InitialHeapSize的VM等价参数是-Xms"
        ));
        // -XX:InitialRAMPercentage=percent

        // -XX:InitialSurvivorRatio=ratio
        set.add(new JvmParameterEntity(
                "InitialSurvivorRatio",
                new String[]{"all jdk"},
                new String[]{"-XX:InitialSurvivorRatio=4"},
                "-",
                "-",
                "Sets the initial survivor space ratio used by the throughput garbage collector (which is enabled by the -XX:+UseParallelGC and/or -XX:+UseParallelOldGC options). " ,
                "设置初始的survivor空间占比，当使用throughput型的GC时有效（即-XX:+UseParallelGC 或 -XX:+UseParallelOldGC）",
                "使用此参数的正确姿势:-XX:InitialSurvivorRatio=____",
                "无默认值",
                "unsigned int",
                ""
        ));

        // -XX:InitiatingHeapOccupancyPercent=percent
        set.add(new JvmParameterEntity(
                "InitiatingHeapOccupancyPercent",
                new String[]{"all jdk"},
                new String[]{"-XX:InitiatingHeapOccupancyPercent=75"},
                "-",
                "-",
                "Sets the percentage of the heap occupancy (0 to 100) at which to start a concurrent GC cycle. It is used by garbage collectors that trigger a concurrent GC cycle based on the occupancy of the entire heap, not just one of the generations (for example, the G1 garbage collector)." ,
                "设置一个触发并发GC的堆占用百分比，对G1有效",
                "使用此参数的正确姿势:-XX:InitiatingHeapOccupancyPercent=____",
                "默认值为45%",
                "unsigned int",
                ""
        ));

        // -XX:MaxGCPauseMillis=time
        set.add(new JvmParameterEntity(
                "MaxGCPauseMillis",
                new String[]{"all jdk"},
                new String[]{"-XX:MaxGCPauseMillis=500"},
                "-",
                "-",
                "Sets a target for the maximum GC pause time (in milliseconds). This is a soft goal, and the JVM will make its best effort to achieve it. By default, there is no maximum pause time value." ,
                "设置一个最大的GC停顿时间（毫秒）,这是个软目标，JVM会尽最大努力去实现它",
                "使用此参数的正确姿势:-XX:MaxGCPauseMillis=____",
                "默认值是200ms",
                "unsigned int",
                ""
        ));

        // -XX:MaxHeapSize
        set.add(new JvmParameterEntity(
                "MaxHeapSize",
                new String[]{"all jdk"},
                new String[]{"-XX:MaxHeapSize=10g"},
                "-",
                "-",
                "Sets the maximum size (in byes) of the memory allocation pool. " ,
                "设置最大堆内存",
                "使用此参数的正确姿势:-XX:MaxHeapSize=____",
                "默认物理内存的1/64",
                "unsigned int",
                "-XX:MaxHeapSize的VM等价参数是-Xmx"
        ));

        // MinHeapFreeRatio
        set.add(new JvmParameterEntity(
                "MinHeapFreeRatio",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "",
                "GC后堆内存空闲比例小于该值，JVM会扩大堆直到-Xmx的最大限制",
                "",
                "默认值为40%",
                "unsigned int",
                ""
        ));

        // -XX:MaxHeapFreeRatio
        set.add(new JvmParameterEntity(
                "MaxHeapFreeRatio",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Sets the maximum allowed percentage of free heap space (0 to 100) after a GC event. If free heap space expands above this value, then the heap will be shrunk. By default, this value is set to 70%.",
                "GC后堆内存空闲比例大于该值，JVM会减少堆直到-Xms的最小限制",
                "使用此参数的正确姿势: -XX:MaxHeapFreeRatio=____",
                "默认值为70%",
                "unsigned int",
                ""
        ));

        // https://www.jianshu.com/p/b448c21d2e71
        // -XX:MaxMetaspaceSize
        set.add(new JvmParameterEntity(
                "MaxMetaspaceSize",
                new String[]{"@since 1.8+"},
                new String[]{"-XX:MaxMetaspaceSize=256m"},
                "-",
                "-",
                "" ,
                "MaxMetaspaceSize用于设置metaspace区域的最大值",
                "使用此参数的正确姿势: -XX:MaxMetaspaceSize=____",
                "默认值 -1",
                "unsigned int",
                ""
        ));

        // -XX:MaxNewSize=size
        set.add(new JvmParameterEntity(
                "MaxNewSize",
                new String[]{"all jdk"},
                new String[]{"-XX:MaxNewSize=1g"},
                "-",
                "-",
                "Sets the maximum size (in bytes) of the heap for the young generation (nursery). The default value is set ergonomically." ,
                "设置新生代的最大值",
                "使用此参数的正确姿势:-XX:MaxNewSize=____",
                "自动检测",
                "unsigned int",
                ""
        ));

        // -XX:MaxRAMPercentage=percent

        // done
        // XX:MaxTenuringThreshold=threshold
        set.add(new JvmParameterEntity(
                "MaxTenuringThreshold",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "" ,
                "晋升到老年代的对象年龄。每个对象在坚持过一次YGC之后，年龄就增加1，当超过这个参数时就进入老年代",
                "使用此参数的正确姿势:-XX:MaxTenuringThreshold=____",
                "默认值 15",
                "unsigned int",
                ""
        ));

        // -XX:MetaspaceSize
        set.add(new JvmParameterEntity(
                "MetaspaceSize",
                new String[]{"@since 1.8+"},
                new String[]{"-XX:MetaspaceSize=256m"},
                "-",
                "-",
                "" ,
                "设置元空间的大小",
                "使用此参数的正确姿势:-XX:MetaspaceSize=____",
                "默认值 20M",
                "unsigned int",
                "metaspace的大小表示首次使用不够而触发FGC的阈值，只对触发起作用"
        ));

        // -XX:MinRAMPercentage=percent

        // -XX:NewRatio=2
        // 新生代（Eden + 2*S）与老年代（不包括永久区）的比值
        set.add(new JvmParameterEntity(
                "NewRatio",
                new String[]{"all jdk"},
                new String[]{"-XX:NewRatio=2"},
                "-",
                "-",
                "Sets the ratio between young and old generation sizes. By default, this option is set to 2. " ,
                "老年代与新生代容量的比值",
                "使用此参数的正确姿势:-XX:NewRatio=____",
                "默认值 2",
                "unsigned int",
                ""
        ));

        // -XX:NewSize=size
        set.add(new JvmParameterEntity(
                "NewSize",
                new String[]{"all jdk"},
                new String[]{"-XX:NewSize=1000m"},
                "-",
                "-",
                "Sets the initial size of the heap for the young generation  " ,
                "设置新生代的初始值",
                "使用此参数的正确姿势:-XX:NewSize=____",
                "无默认值",
                "unsigned int",
                "-XX:NewSize的VM 等价参数是-Xmn."
        ));

        // -XX:ParallelGCThreads=threads
        set.add(new JvmParameterEntity(
                "ParallelGCThreads",
                new String[]{"all jdk"},
                new String[]{"-XX:ParallelGCThreads=2"},
                "-",
                "-",
                "Sets the number of threads used for parallel garbage collection in the young and old generations. The default value depends on the number of CPUs available to the JVM." ,
                "设置并行GC时的线程数",
                "使用此参数的正确姿势:-XX:ParallelGCThreads=____",
                "CPU数",
                "unsigned int",
                ""
        ));

        // -XX:+ParallelRefProcEnabled
        set.add(new JvmParameterEntity(
                "ParallelRefProcEnabled",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables parallel reference processing. By default, this option is disabled." ,
                "允许并行处理Reference，加快处理速度，缩短耗时",
                "你可以通过-XX:+ParallelRefProcEnabled开启，或者-XX:-ParallelRefProcEnabled关闭",
                "默认关闭",
                "boolean",
                "使用时请开启"
        ));

        // -XX:+PrintAdaptiveSizePolicy
        set.add(new JvmParameterEntity(
                "PrintAdaptiveSizePolicy",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables printing of information about adaptive generation sizing. By default, this option is disabled." ,
                "打印新生代、老年代自适应调整详情",
                "你可以通过-XX:+PrintAdaptiveSizePolicy开启，或者-XX:-PrintAdaptiveSizePolicy关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:-PrintGC
        set.add(new JvmParameterEntity(
                "PrintGC",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables printing of messages at every GC.",
                "开启了简单GC日志模式，为每一次新生代（young generation）的GC和每一次的Full GC打印一行信息",
                "你可以通过-XX:+PrintGC开启，或者-XX:-PrintGC关闭",
                "默认关闭",
                "boolean",
                "-XX:+PrintGC的VM等价参数是-verbose:gc"
        ));

        // -XX:+PrintGCApplicationConcurrentTime
        set.add(new JvmParameterEntity(
                "PrintGCApplicationConcurrentTime",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables printing of how much time elapsed since the last pause (for example, a GC pause). By default, this option is disabled.",
                "每次垃圾回收前，打印自最近一次暂停（例如GC暂停）以来经过的时间",
                "你可以通过-XX:+PrintGCApplicationConcurrentTime开启，或者-XX:-PrintGCApplicationConcurrentTime关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:+PrintGCApplicationStoppedTime
        set.add(new JvmParameterEntity(
                "PrintGCApplicationStoppedTime",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables printing of how much time the pause (for example, a GC pause) lasted. By default, this option is disabled.",
                "打印GC暂停的时间",
                "你可以通过-XX:+PrintGCApplicationStoppedTime开启，或者-XX:-PrintGCApplicationStoppedTime关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:+PrintGCDateStamps
        set.add(new JvmParameterEntity(
                "PrintGCDateStamps",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables printing of a date stamp at every GC.",
                "打印每次GC的发生时间(系统时间)",
                "你可以通过-XX:+PrintGCDateStamps开启，或者-XX:-PrintGCDateStamps关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // todo jdk版本
        // -XX:+PrintGCDetails
        set.add(new JvmParameterEntity(
                "PrintGCDetails",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables printing of detailed messages at every GC",
                "打印每次GC的细节信息",
                "你可以通过-XX:+PrintGCDetails开启，或者-XX:-PrintGCDetails关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // todo
        // -XX:+PrintStringDeduplicationStatistics
        set.add(new JvmParameterEntity(
                "PrintStringDeduplicationStatistics",
                new String[]{""},
                new String[]{""},
                "-",
                "-",
                "",
                "",
                "",
                "",
                "",
                ""
        ));

        // todo
        // -XX:+PrintTenuringDistribution
        set.add(new JvmParameterEntity(
                "PrintTenuringDistribution",
                new String[]{""},
                new String[]{""},
                "-",
                "-",
                "",
                "",
                "",
                "",
                "",
                ""
        ));

        // todo
        // -XX:+ScavengeBeforeFullGC
        set.add(new JvmParameterEntity(
                "ScavengeBeforeFullGC",
                new String[]{""},
                new String[]{""},
                "-",
                "-",
                "",
                "",
                "",
                "",
                "",
                ""
        ));

        // todo
        // -XX:SoftRefLRUPolicyMSPerMB=time
        set.add(new JvmParameterEntity(
                "SoftRefLRUPolicyMSPerMB",
                new String[]{""},
                new String[]{""},
                "-",
                "-",
                "",
                "",
                "",
                "",
                "",
                ""
        ));

        // todo
        // XX:StringDeduplicationAgeThreshold=threshold
        set.add(new JvmParameterEntity(
                "StringDeduplicationAgeThreshold",
                new String[]{""},
                new String[]{"-XX:+PrintGCDetails"},
                "-",
                "-",
                "",
                "",
                "",
                "",
                "",
                ""
        ));

        // SurvivorRatio
        set.add(new JvmParameterEntity(
                "SurvivorRatio",
                new String[]{"all jdk"},
                new String[]{"-XX:SurvivorRatio=12"},
                "-",
                "-",
                "Sets the ratio between eden space size and survivor space size. By default, this option is set to 8." ,
                "新生代中Eden区域和Survivor区域（From幸存区或To幸存区）容量的比值",
                "使用此参数的正确姿势:-XX:SurvivorRatio=____",
                "默认值为 8",
                "unsigned int",
                "如果我们通过设置-Xmn1000m来指定新生代分配的空间大小，那么Eden则会分配1000M * 0.8 = 800M，Survivor一共分配1000M * 0.2 = 200M的内存空间。一般情况下该参数使用默认值即可，除非你在JVM优化领域有着非常丰富的经验。"
        ));

        // -XX:PretenureSizeThreshold
        set.add(new JvmParameterEntity(
                "PretenureSizeThreshold",
                new String[]{"@since 1.4.2+"},
                new String[]{""},
                "-",
                "-",
                "." ,
                "设定大对象的阈值,大对象直接在老年代分配",
                "使用此参数的正确姿势:-XX:PretenureSizeThreshold=____",
                "默认值为 0",
                "unsigned int",
                "默认值是0，意思是不管对象多大都是先在eden中分配内存"
        ));

        // -XX:TargetSurvivorRatio=percent
        // https://www.jianshu.com/p/989d3b06a49d
        set.add(new JvmParameterEntity(
                "TargetSurvivorRatio",
                new String[]{"all jdk"},
                new String[]{"-XX:TargetSurvivorRatio=60"},
                "-",
                "-",
                "Sets the desired percentage of survivor space (0 to 100) used after young garbage collection. By default, this option is set to 50%" ,
                "设置一次YGC后Survivor区的占用率",
                "Survivor空间分配200M的内存空间,并且设定-XX:TargetSurvivorRatio=60,则YGC之后Survivor空间还有200m*60%=120m的对象留存",
                "默认值为50%",
                "unsigned int",
                "一般情况下该参数使用默认值即可。"
        ));

        // TLABSize
        set.add(new JvmParameterEntity(
                "TLABSize",
                new String[]{"all jdk"},
                new String[]{"-XX:TLABSize=512k"},
                "-",
                "-",
                "Sets the initial size of a thread-local allocation buffer (TLAB). " ,
                "设置线程本地缓存区的初始大小",
                "使用此参数的正确姿势: -XX:TLABSize=____",
                "",
                "",
                ""
        ));

        // UseAdaptiveSizePolicy
        // 是否采取动态控制策略，会动态调整java堆中各个区域大小及进入老年代的年龄
        // https://www.jianshu.com/p/7414fd6862c5
        set.add(new JvmParameterEntity(
                "UseAdaptiveSizePolicy",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables the use of adaptive generation sizing. This option is enabled by default. To disable adaptive generation sizing, specify -XX:-UseAdaptiveSizePolicy and set the size of the memory allocation pool explicitly (see the -XX:SurvivorRatio option)." ,
                "开启自适应GC策略模式",
                "如果开启该选项，新生代的大小、eden和survivior的比例、晋升老年代的对象年龄等参数会被自动调整，以达到在堆大小、吞吐量和停顿时间之间的平衡点",
                "默认开启",
                "boolean",
                "JDK1.8默认使用UseParallelGC垃圾回收器，该垃圾回收器默认启动了AdaptiveSizePolicy"
        ));

        //  HandlePromotionFailure  JDK1.6后废弃

        // -XX:+UseCMSInitiatingOccupancyOnly
        set.add(new JvmParameterEntity(
                "UseCMSInitiatingOccupancyOnly",
                new String[]{"all jdk"},
                new String[]{"-XX:+UseCMSInitiatingOccupancyOnly"},
                "-",
                "-",
                "Enables the use of the occupancy value as the only criterion for initiating the CMS collector. By default, this option is disabled and other criteria may be used." ,
                "开启后使用CMSInitiatingOccupancyFraction值作为启动CMS收集器的唯一条件",
                "开启该选项后指定JVM总是使用-XX:CMSInitiatingOccupancyFraction的值作为老年代的空间使用率限制来启动CMS垃圾回收。如果没有使用开启该选项，那么JVM只是利用这个值来启动第一次CMS垃圾回收，后面都是使用JVM自动计算出来的值",
                "默认关闭",
                "boolean",
                ""
        ));

        // CMSFullGCsBeforeCompaction
        set.add(new JvmParameterEntity(
                "CMSFullGCsBeforeCompaction",
                new String[]{"all jdk"},
                new String[]{"-XX:CMSFullGCsBeforeCompaction=9"},
                "-",
                "-",
                "Enables the use of the occupancy value as the only criterion for initiating the CMS collector. By default, this option is disabled and other criteria may be used." ,
                "开启后使用CMSInitiatingOccupancyFraction值作为启动CMS收集器的唯一条件",
                "开启该选项后指定JVM总是使用-XX:CMSInitiatingOccupancyFraction的值作为老年代的空间使用率限制来启动CMS垃圾回收。如果没有使用开启该选项，那么JVM只是利用这个值来启动第一次CMS垃圾回收，后面都是使用JVM自动计算出来的值",
                "默认关闭",
                "boolean",
                ""
        ));

        // CMSParallelInitialMarkEnabled

        // -XX:UseConcMarkSweepGC
        set.add(new JvmParameterEntity(
                "UseConcMarkSweepGC",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Use concurrent mark-sweep collection for the old generation",
                "允许对老年代使用CMS垃圾收集器",
                "你可以通过-XX:+UseConcMarkSweepGC开启，或者-XX:-UseConcMarkSweepGC关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:+UseG1GC
        set.add(new JvmParameterEntity(
                "UseG1GC",
                new String[]{"@since 1.7+"},
                new String[]{""},
                "-",
                "-",
                "Enables the use of the garbage-first (G1) garbage collector. It is a server-style garbage collector, targeted for multiprocessor machines with a large amount of RAM. It meets GC pause time goals with high probability, while maintaining good throughput. The G1 collector is recommended for applications requiring large heaps (sizes of around 6 GB or larger) with limited GC latency requirements (stable and predictable pause time below 0.5 seconds).",
                "开启或者关闭G1垃圾收集器",
                "你可以通过-XX:+UseG1GC开启，或者-XX:-UseG1GC关闭",
                "默认关闭",
                "boolean",
                "当堆内存较大时(>=6G)推荐使用G1收集器"
        ));

        // UseGCOverheadLimit
        set.add(new JvmParameterEntity(
                "UseGCOverheadLimit",
                new String[]{"@since 1.6+"},
                new String[]{""},
                "-",
                "-",
                "Enables the use of a policy that limits the proportion of time spent by the JVM on GC before an OutOfMemoryError exception is thrown. This option is enabled, by default and the parallel GC will throw an OutOfMemoryError if more than 98% of the total time is spent on garbage collection and less than 2% of the heap is recovered. When the heap is small, this feature can be used to prevent applications from running for long periods of time with little or no progress. To disable this option, specify -XX:-UseGCOverheadLimit.",
                "限制GC的运行时间，如果GC耗时过长，就抛OOM",
                "你可以通过-XX:+UseGCOverheadLimit开启，或者-XX:-UseGCOverheadLimit关闭",
                "默认开启",
                "boolean",
                "建议关闭"
        ));

        // UseNUMA
        set.add(new JvmParameterEntity(
                "UseNUMA",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables performance optimization of an application on a machine with nonuniform memory architecture (NUMA) by increasing the application's use of lower latency memory. By default, this option is disabled and no optimization for NUMA is made. The option is only available when the parallel garbage collector is used (-XX:+UseParallelGC).",
                "对象分配优先使用 NUMA 本地内存",
                "你可以通过-XX:+UseNUMA开启，或者-XX:-UseNUMA关闭",
                "默认关闭",
                "boolean",
                "-XX:+UseNUMA只对ParallelGC的Eden space起作用,对TO_SPACE/FROM_SPACE都不起作用"
        ));

        // -XX:+UseParallelGC
        set.add(new JvmParameterEntity(
                "UseParallelGC",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables the use of the parallel scavenge garbage collector (also known as the throughput collector) to improve the performance of your application by leveraging multiple processors.",
                "新生代使用并行收集器，老年代使用串行收集器",
                "你可以通过-XX:+UseParallelGC开启，或者-XX:-UseParallelGC关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:+UseParallelOldGC
        set.add(new JvmParameterEntity(
                "UseParallelOldGC",
                new String[]{"@since 1.5+"},
                new String[]{""},
                "-",
                "-",
                "Enables the use of the parallel garbage collector for full GCs. By default, this option is disabled. Enabling it automatically enables the -XX:+UseParallelGC option.",
                "老年代和新生代都使用并行垃圾收集器。开启此选项将自动开启-XX:+UseParallelGC 选项",
                "你可以通过-XX:+UseParallelOldGC开启，或者-XX:-UseParallelOldGC关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // -XX:+UseParNewGC
        set.add(new JvmParameterEntity(
                "UseParNewGC",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables the use of parallel threads for collection in the young generation.By default, this option is disabled. It is automatically enabled when you set the -XX:+UseConcMarkSweepGC option. Using the -XX:+UseParNewGC option without the -XX:+UseConcMarkSweepGC option was deprecated in JDK 8.",
                "允许对新生代使用并行垃圾收集器。ParNew收集器是Serial收集器的多线程版本",
                "你可以通过-XX:+UseParNewGC开启，或者-XX:-UseParNewGC关闭",
                "不开启",
                "boolean",
                "在jdk8 当开启-XX:+UseConcMarkSweepGC选项时，-XX:+UseParNewGC将自动启用"
        ));

        // -XX:+UseSerialGC
        set.add(new JvmParameterEntity(
                "UseSerialGC",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables the use of the serial garbage collector. This is generally the best choice for small and simple applications that do not require any special functionality from garbage collection. By default, this option is disabled and the collector is chosen automatically based on the configuration of the machine and type of the JVM.",
                "使用串行垃圾收集器",
                "你可以通过-XX:+UseParallelGC开启，或者-XX:-UseParallelGC关闭",
                "默认关闭",
                "boolean",
                "开启该参数会使新生代和老年代都使用串行回收器。串行回收器是最基本、历史最悠久的收集器，它是一个单线程收集器。一旦回收器开始运行时，整个系统都要停止。Client模式下默认开启，其他模式默认关闭"
        ));

        // -XX:+UseSHM
        set.add(new JvmParameterEntity(
                "UseSHM",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "On Linux, enables the JVM to use shared memory to setup large pages.",
                "在Linux环境下支持JVM使用共享内存来设置大页",
                "你可以通过-XX:+UseSHM开启，或者-XX:-UseSHM关闭",
                "默认关闭",
                "boolean",
                ""
        ));

        // XX:+UseStringDeduplication
        set.add(new JvmParameterEntity(
                "UseStringDeduplication",
                new String[]{"since@ 1.8+"},
                new String[]{""},
                "-",
                "-",
                "Enables string deduplication. By default, this option is disabled. To use this option, you must enable the garbage-first (G1) garbage collector. See the -XX:+UseG1GC option.",
                "允许去除堆中重复的字符串",
                "你可以通过-XX:+UseStringDeduplication开启，或者-XX:-UseStringDeduplication关闭",
                "默认关闭",
                "boolean",
                "仅在G1收集器使用，并且JDK的版本大于JDK8 update20，那么可以尝试开启-XX:+UseStringDeduplication，如果你的应用中存在大量长时间存活的对象，那结果肯定是很香"
        ));

        // -XX:+UseTLAB
        // https://blog.gceasy.io/2018/12/23/usestringdeduplication/
        set.add(new JvmParameterEntity(
                "UseTLAB",
                new String[]{"all jdk"},
                new String[]{""},
                "-",
                "-",
                "Enables the use of thread-local allocation blocks (TLABs) in the young generation space. This option is enabled by default. To disable the use of TLABs, specify -XX:-UseTLAB.",
                "优先在本地线程缓冲区中分配对象，避免分配内存时的锁定过程",
                "你可以通过-XX:+UseTLAB开启，或者-XX:-UseTLAB关闭",
                "默认开启",
                "boolean",
                ""
        ));
        //=====================Deprecated and Removed Options=====================
    }
}