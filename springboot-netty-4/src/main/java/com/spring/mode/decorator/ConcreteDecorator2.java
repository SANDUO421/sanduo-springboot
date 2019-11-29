package com.spring.mode.decorator;

/**
 * 具体装饰：添加新功能
 * @author 三多
 * @Time 2019/9/19
 */
public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }

    /**
     * 添加额外的功能
     */
    private void  doAnotherThing(){
        System.out.println("功能C");
    }
}
