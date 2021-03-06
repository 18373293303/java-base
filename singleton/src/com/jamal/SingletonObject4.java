package com.jamal;

/**
 * 双重检测锁，添加class类锁，实现了懒加载，也解决了多线程安全问题
 */
public class SingletonObject4 {
    private static SingletonObject4 instance;

    private SingletonObject4(){

    }

    public static SingletonObject4 getInstance(){
        /**
         * 双重检测锁解决了单例、性能、安全问题，在双重检测锁情况下锁最多加两次
         * 双重检测锁可能带来空指针异常的问题，这个问题的由于JVM的优化、指令重排序造成的
         * 你在构造器中，操作比较多，JVM会在构造函数其他操作没有实例化完成的情况下，将instance实例返回
         * 其他线程调用方法是可能该方法或者属性还没有初始化完成，所以会造成空指针异常问题
         *
         */

        if (instance == null)
            synchronized (SingletonObject4.class){
                if (instance == null){
                    instance = new SingletonObject4();
                }
            }

        return instance;
    }
}
