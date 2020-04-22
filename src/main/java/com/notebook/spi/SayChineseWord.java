package com.notebook.spi;

/**
 * @author luorigong
 */
public class SayChineseWord implements SayWord {
    @Override
    public String saySomething() {
        return "你好";
    }
}
