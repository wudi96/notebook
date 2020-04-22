package com.notebook.spi;

import java.util.ServiceLoader;

/**
 * SPI机制
 * @author luorigong
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<SayWord> sayWords = ServiceLoader.load(SayWord.class);
        for (SayWord sayWord : sayWords) {
            System.out.println(sayWord.saySomething());
        }
    }
}
