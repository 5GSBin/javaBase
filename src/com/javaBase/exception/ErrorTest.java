package com.javaBase.exception;

/**
 * java虚拟机都无法解决的问题--Error   例如资源耗尽，jvm系统内部错误
 */

public class ErrorTest {

    public static void main(String[] args) {
        //1.栈溢出：java.lang.StackOverflowError
//        main(args);
        //2.堆溢出:java.lang.OutOfMemoryError: Java heap space----OOM
        Integer[] arr = new Integer[1024*1024*1024];
    }

}
