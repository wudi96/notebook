package com.notebook.spi;

/**
 * @author luorigong
 */
public class SayEgWord implements SayWord {
    @Override
    public String saySomething() {
        return "hello";
    }
}
