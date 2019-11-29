package com.spring.mode.decorator;

/**
 * 具体组件
 * @author 三多
 * @Time 2019/9/19
 */
public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
