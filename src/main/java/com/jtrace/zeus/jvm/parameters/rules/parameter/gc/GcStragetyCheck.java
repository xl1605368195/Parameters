package com.jtrace.zeus.jvm.parameters.rules.parameter.gc;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xule05
 * @date 2020/2/2 下午10:27
 */
@Data
public class GcStragetyCheck {

    private GcCollectorEnum youngCollector;

    private GcCollectorEnum oldCollector;

    /**
     * 单位 G
     */
    private int maxMemorySize;


    private GcStragetyCheck(HashMap<String, String> parametersValuesMap, int maxMemorySize) {
        setGcType(parametersValuesMap);
        this.maxMemorySize = maxMemorySize;
    }

    private void setGcType(HashMap<String, String> parametersValuesMap) {
        // 顺序不要更改
        String useParNewGc = parametersValuesMap.get("UseParNewGC");
        if (StringUtils.isNotBlank(useParNewGc) && StringUtils.equals(useParNewGc, "true")) {
            youngCollector = GcCollectorEnum.PAR_NEW;
            oldCollector = GcCollectorEnum.SERIAL_OLD;
        }

        String useConcMarkSweepGc = parametersValuesMap.get("UseConcMarkSweepGC");
        if (StringUtils.isNotBlank(useConcMarkSweepGc) && StringUtils.equals(useConcMarkSweepGc, "true")) {
            oldCollector = GcCollectorEnum.CMS;
        }

        String useParallelGc = parametersValuesMap.get("UseParallelGC");
        if (StringUtils.isNotBlank(useParallelGc) && StringUtils.equals(useParallelGc, "true")) {
            youngCollector = GcCollectorEnum.PARALLEL_SCAVENGE;
            oldCollector = GcCollectorEnum.SERIAL_OLD;
        }

        String useParallelOldGc = parametersValuesMap.get("UseParallelOldGC");
        if (StringUtils.isNotBlank(useParallelOldGc) && StringUtils.equals(useParallelOldGc, "true")) {
            youngCollector = GcCollectorEnum.PARALLEL_SCAVENGE;
            oldCollector = GcCollectorEnum.PARALLEL_OLD;
        }

        String useSerialGc = parametersValuesMap.get("UseSerialGC");
        if (StringUtils.isNotBlank(useSerialGc) && StringUtils.equals(useSerialGc, "true")) {
            youngCollector = GcCollectorEnum.SERIAL;
            oldCollector = GcCollectorEnum.SERIAL_OLD;
        }

        String useG1GC = parametersValuesMap.get("UseG1GC");
        if (StringUtils.isNotBlank(useG1GC) && StringUtils.equals(useG1GC, "true")) {
            youngCollector = GcCollectorEnum.G1;
            oldCollector = GcCollectorEnum.G1;
        }
    }

}
