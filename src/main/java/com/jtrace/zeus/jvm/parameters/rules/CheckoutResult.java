package com.jtrace.zeus.jvm.parameters.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xule05
 * @date 2020/1/30 下午8:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckoutResult {

    private Level level;

    private String category;

    private String title = "";

    private String conent = "";

    private String tips = "";

    public static CheckoutResult ok(String title,String category) {
        return new CheckoutResult(Level.OK, title,category);
    }

    public CheckoutResult(Level level, String title,String category) {
        this.level = level;
        this.title = title;
        this.category=category;
    }
}
