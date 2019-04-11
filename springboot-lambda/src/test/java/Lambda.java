import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试Lambda
 *
 * @author 三多
 * @Time 2019/4/3
 */
public class Lambda {


    public static void main(String[] args) {

        /**
         * 小写转大写：单参数语法
         */
        List<String> proNames = Arrays.asList(new String[]{"Ni", "Hao", "Lambda"});
        List<String> collect = proNames.parallelStream().map(name -> {
            return name.toLowerCase();
        }).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("---------------------------------------");

        /**
         *
         *当lambda表达式只包含一条语句时，可以省略大括号、return和语句结尾的分号
         */
        List<String> lowercaseNames2 = proNames.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());
        System.out.println(lowercaseNames2);

        System.out.println("---------------------------------------");
        /**
         * 方法引用和lambda一样是Java8新语言特性
         * Class or instance :: method
         */

        List<String> lowercaseNames3 = proNames.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println(lowercaseNames3);
        System.out.println("---------------------------------------");
        /**
         * lambda表达式可使用的变量
         * lambda表达式可以访问给它传递的变量，访问自己内部定义的变量，同时也能访问它外部的变量。
         * 不过lambda表达式访问外部变量有一个非常重要的限制：变量不可变（只是引用不可变，而不是真正的不可变）。
         * 因为变量waibu被lambda表达式引用，所以编译器会隐式的把其当成final来处理。
         */
        String waibu = "lambda :";
        List<String> stream = proNames.stream().map(params -> {
            long timeMillis = System.currentTimeMillis();
            return waibu + params + " -----:" + timeMillis;
        }).collect(Collectors.toList());
        System.out.println(stream);
        System.out.println("---------------------------------------");
        /**
         * lambda表达式中的this概念
         */
       new Lambda().whatThis();
    }

    public void whatThis() {
        //转全小写
        List<String> proStrs = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
        List<String> collect = proStrs.parallelStream().map(str -> {
            System.out.println(this.getClass().getSimpleName());
            return str.toLowerCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out :: println);
    }
}
