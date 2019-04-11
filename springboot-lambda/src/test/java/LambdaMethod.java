/**
 * lambda 方法的测试
 * @author 三多
 * @Time 2019/4/3
 */
public class LambdaMethod {

    public static void main(String[] args) {

        /**
         * 方法引用
         *
         * objectName::instanceMethod
         * ClassName::staticMethod
         * ClassName::instanceMethod
         * 前两种方式类似，等同于把lambda表达式的参数直接当成instanceMethod|staticMethod的参数来调用。比如System.out::println等同于x->System.out.println(x)；Math::max等同于(x, y)->Math.max(x,y)。
         * 最后一种方式，等同于把lambda表达式的第一个参数当成instanceMethod的目标对象，其他剩余参数当成该方法的参数。比如String::toLowerCase等同于x->x.toLowerCase()。
         * 可以这么理解，前两种是将传入对象当参数执行方法，后一种是调用传入对象的方法。
         */

        /**
         * 构造器引用
         *
         * 构造器引用语法如下：ClassName::new，把lambda表达式的参数当成ClassName构造器的参数 。例如BigDecimal::new等同于x->new BigDecimal(x)。
         */


    }
}
