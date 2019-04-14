package com.java.thinking.reflect;

/**
 * reflect demo
 * 传统的RTTI:编译器
 * 运行期：反射
 * @author 三多
 * @Time 2019/4/14
 */
public class ReflectForName {
    public static void main(String[] args) {
        new Candy();
        System.out.println("After creating Candy");
        try {
           Class.forName("com.java.thinking.reflect.Gum");
        }catch (ClassNotFoundException e){
            System.out.println("Couldn't find Gum");
         }
        System.out.println("After Class forName(\"Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");

    }
}
class Gum {
    static {
        System.out.println("loading Gum");
    }
}
class Cookie {
    static {
        System.out.println("loading Cookie");
    }
}
class Candy {
    static {
        System.out.println("loading Candy");
    }
}
