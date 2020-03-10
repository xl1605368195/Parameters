package com.jtrace.zeus.jvm.parameters.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xule05
 * @date 2020/3/10 下午1:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IPInfoEntity {
    String ip;
    String pro;
    String proCode;
    String city;
    String cityCode;
    String region;
    String regionCode;
    String addr;
    String regionNames;
    String err;
}
