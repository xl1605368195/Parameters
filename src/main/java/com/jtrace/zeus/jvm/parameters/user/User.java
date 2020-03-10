package com.jtrace.zeus.jvm.parameters.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author xule05
 * @date 2020/3/10 上午10:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    /**
     * 访问的ip地址
     */
    private String ip;

    /**
     * 访问的日期
     */
    private Date datetime;

    /**
     * ip 归属地
     */
    private String location;
}
