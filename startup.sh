nohup java  -server -Xms2g -Xmx2g -Xss1m -XX:+UseConcMarkSweepGC
-XX:MaxMetaspaceSize=256M -XX:NewRatio=4 -Xloggc:/usr/local/tools/software/parameters/gclog/jvm-parameters.%t.gc.log
-XX:ErrorFile=/usr/local/tools/software/parameters/jvm-parameters.20200129.vmerr.log
-XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly
-XX:+AlwaysPreTouch -XX:CMSFullGCsBeforeCompaction=5 -XX:+UseCMSCompactAtFullCollection
-XX:+PrintGCApplicationStoppedTime -XX:+PrintTenuringDistribution -XX:+HeapDumpOnOutOfMemoryError
-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps
-XX:+PrintClassHistogram -jar  ./target/parameters-0.0.1-SNAPSHOT.jar
--server.port=80 >/dev/null 2>&1 &