package com.spring.mode.decorator;

/**
 * 装饰器（核心所在）
 * @author 三多
 * @Time 2019/9/19
 */
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
