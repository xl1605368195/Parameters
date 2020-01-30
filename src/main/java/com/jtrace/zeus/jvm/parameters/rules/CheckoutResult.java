package com.jtrace.zeus.jvm.parameters.rules;

/**
 * @author xule05
 * @date 2020/1/30 下午8:46
 */
public class CheckoutResult {

    private Level level;

    private String title = "";

    private String conent = "";

    private String tips = "";

    public static CheckoutResult ok(String title) {
        return new CheckoutResult(Level.OK, title);
    }

    public CheckoutResult(Level level, String title, String conent, String tips) {
        this.level = level;
        this.title = title;
        this.conent = conent;
        this.tips = tips;
    }

    public CheckoutResult(Level level, String title) {
        this.level = level;
        this.title = title;
    }
}
