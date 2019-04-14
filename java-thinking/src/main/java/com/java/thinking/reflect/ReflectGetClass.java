package com.java.thinking.reflect;

/**
 * GetClass 获取
 * getName
 * getSimpleName
 * getCanonicalName
 * getInterfaces
 * getSuperclass
 *
 * @author 三多
 * @Time 2019/4/14
 */
public class ReflectGetClass {
    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("com.java.thinking.reflect.FancyToy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can't find FancyToy");
            System.exit(1);
        }
        ToyTest.printInfo(c);
        //获取实现的接口
        for (Class face : c.getInterfaces()) {
            ToyTest.printInfo(face);
        }
        //获取直接父类（class引用）
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            //调用默认的构造函数，newInstance()创建的类，必须带默认构造器
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Cannot Access");
            System.exit(1);
        }
        ToyTest.printInfo(obj.getClass());

    }

}

interface HasBatteries {
}

interface WaterProof {
}

interface Shoots {
}

class Toy {
    Toy() {
    }

    Toy(int i) {
    }
}

class FancyToy extends Toy implements HasBatteries, WaterProof, Shoots {
    FancyToy() {
        super(1);
    }
}

class ToyTest {
    /**
     * 获取类的详细信息
     *
     * @param c
     */
    static void printInfo(Class c) {
        //获取全限定名称，如：com.java.thinking.reflect.FancyToy
        System.out.println("Class Name:" + c.getName() + ",is Interface?[" + c.isInterface() + "]");
        //获取类名（不含包名）：如FancyToy
        System.out.println("Simple Name:" + c.getSimpleName());
        //获取标准类名（全限定名）：如com.java.thinking.reflect.FancyToy
        System.out.println("Canonical Name:" + c.getCanonicalName());

    }
}