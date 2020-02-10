package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.rules.parameter.heap.HeapParametersCheck;
import com.jtrace.zeus.jvm.parameters.utils.ParseParameterUtils;
import org.springframework.stereotype.Service;

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
                ParseParameterUtils.parseAdvancedOptions(maps, tmp);
            } else if (tmp.startsWith("-X")) {
                ParseParameterUtils.parseNonStandardOptions(maps, tmp);
            } else if (tmp.startsWith("-") & !tmp.startsWith("-D")) {
                ParseParameterUtils.parseStandardOptions(maps, tmp);
            } else {
                // ignore.... 未知参数
            }
        }
        return maps;
    }

    /**
     * step2:参数检查
     */
    public List<CheckoutResult> checkParameters(HashMap<String, String> parametersValuesMap, String version, int totalMem) {
        List<CheckoutResult> results = new LinkedList<>();
        HeapParametersCheck heapParametersCheck=new HeapParametersCheck(parametersValuesMap,totalMem,version);
        heapParametersCheck.checkout(results);
//        checkGlobalParameters(parametersValuesMap,results);
//        checkHeapParameters(parametersValuesMap, totalMem, version, results);
        return results;
    }

}
