package com.jtrace.zeus.jvm.parameters.rules.parameter.gc;

public enum GcCollectorEnum {
    /**
     * GC的种类
     */
    SERIAL(1, "Serial"),
    PAR_NEW(2, "ParNew"),
    PARALLEL_SCAVENGE(3, "Parallel Scavenge"),
    SERIAL_OLD(4, "Serial Old"),
    PARALLEL_OLD(5, "Parallel Old"),
    CMS(6, "CMS"),
    G1(7, "G1"),
    ZGC(8, "zgc"),
    UNKNOWN(10, "Unknown");

    private int value;
    private String name;

    private GcCollectorEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}