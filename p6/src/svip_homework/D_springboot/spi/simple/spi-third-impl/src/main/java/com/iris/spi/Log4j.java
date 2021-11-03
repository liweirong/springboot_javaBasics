package com.iris.spi;

/**
 * @author iris
 * @date 2021/11/3
 */
public class Log4j implements Log{
    @Override
    public void debug() {
        System.out.println("log4j debug ---------");
    }
}
