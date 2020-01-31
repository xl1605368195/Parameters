package com.jtrace.zeus.jvm.parameters.rules;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * @author xule05
 * @date 2020/1/30 下午8:42
 */
public @interface ScanParameter {
}
