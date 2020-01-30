package com.jtrace.zeus.jvm.parameters.parameters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xule05
 * @date 2020/1/30 下午1:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JavaInfoEntity {
    String hostname = "";
    String javaInfo = "";
    String env="";
}
