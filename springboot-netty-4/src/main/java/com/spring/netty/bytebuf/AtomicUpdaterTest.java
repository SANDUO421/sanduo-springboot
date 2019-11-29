package com.spring.netty.bytebuf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Netty 的引用计数器
 *
 * @author 三多
 * @Time 2019/11/9
 */
public class AtomicUpdaterTest {
    public static void main(String[] args) {
        Person person = new Person();
        //第一种方式
       /* for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(person.age++);
            });
            thread.start();
        }*/

        //第二种方式
        AtomicIntegerFieldUpdater<Person> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(fieldUpdater.getAndIncrement(person));
            });
            thread.start();
        }
    }
}

class Person {
    volatile int age = 1;
}
