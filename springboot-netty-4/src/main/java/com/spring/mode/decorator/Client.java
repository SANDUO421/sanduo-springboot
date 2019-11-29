package com.spring.mode.decorator;

/**
 * @author 三多
 * @Time 2019/9/19
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomething();

    }
}
