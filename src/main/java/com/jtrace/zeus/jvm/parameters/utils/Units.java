package com.jtrace.zeus.jvm.parameters.utils;

/**
 * @author xule05
 * @date 2020/1/31 上午11:53
 */
public class Units {

    /**
     * 统一 b,k,m,g=>g
     */
    public static double getGNumber(String valueStr) {
        double tmp = 1;
        String value = valueStr.substring(0, valueStr.length() - 1);
        return Double.parseDouble(value) * getF(tmp, valueStr);
    }

    /**
     * 统一 b,k,m,g=>m
     */
    public static double getMNumber(String valueStr) {
        double tmp = 1024;
        String value = valueStr.substring(0, valueStr.length() - 1);
        return Double.parseDouble(value) * getF(tmp, valueStr);
    }


    /**
     * 统一 b,k,m,g=>k
     */
    public static double getKNumber(String valueStr) {
        double tmp = 1024 * 1024;
        String value = valueStr.substring(0, valueStr.length() - 1);
        return Double.parseDouble(value) * getF(tmp, valueStr);
    }

    /**
     * 统一 b,k,m,g=>b
     */
    public static double getNumber(String valueStr) {
        double tmp = 1024 * 1024 * 1024;
        String value = valueStr.substring(0, valueStr.length() - 1);
        return Double.parseDouble(value) * getF(tmp, valueStr);
    }

    private static double getF(double tmp, String valueStr) {
        double f;
        if (valueStr.endsWith("g") || valueStr.endsWith("G")) {
            f = tmp;
        } else if (valueStr.endsWith("m") || valueStr.endsWith("M")) {
            f = tmp / 1024;
        } else if (valueStr.endsWith("k") || valueStr.endsWith("K")) {
            f = tmp / 1024 / 1024;
        } else {
            f = tmp / 1024 / 1024 / 1024;
        }
        return f;
    }
}
