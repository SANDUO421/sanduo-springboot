import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import javax.sound.midi.Soundbank;
import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 方法
 *
 * @author 三多
 * @Time 2019/4/3
 */
public class LambdaStream {
    /**
     * 1.Stream是元素的集合，这点让Stream看起来用些类似Iterator；
     * 2.可以支持顺序和并行的对原Stream进行汇聚的操作；
     * <p>
     * 大家可以把Stream当成一个装饰后的Iterator。原始版本的Iterator，用户只能逐个遍历元素并对其执行某些操作；
     * 包装后的Stream，用户只要给出需要对其包含的元素执行什么操作，比如“过滤掉长度大于10的字符串”、“获取每个
     * 字符串的首字母”等，具体这些操作如何应用到每个元素上，就给Stream就好了！原先是人告诉计算机一步一步怎么做，
     * 现在是告诉计算机做什么，计算机自己决定怎么做。当然这个“怎么做”还是比较弱的。
     */
    public static void main(String[] args) {
        //Lists是Guava中的一个工具类
        List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
        Stream<Integer> integerStream = nums.stream().filter(num -> num != null);
        //long count = integerStream.count();
        // System.out.println(count);
        List<Integer> collect = integerStream.collect(Collectors.toList());
        collect.forEach(System.out::println);

        /**
         * 怎么得到Stream
         * 最常用的创建Stream有两种途径：
         * 1.通过Stream接口的静态工厂方法（注意：Java8里接口可以带静态方法）；
         * 2.通过Collection接口的默认方法（默认方法：Default method，
         * 也是Java8中的一个新特性，就是接口中的一个带有实现的方法）–stream()，把一个Collection对象转换成Stream
         */

        /**
         * 使用Stream静态方法来创建Stream
         */
        Stream<Integer> data = Stream.of(1, 2, 3, 4, 5);
        Stream<String> str = Stream.of("taobao");
        /**
         *  generator方法：生成一个无限长度的Stream，其元素的生成是通过给定的Supplier
         *  （这个接口可以看成一个对象的工厂，每次调用返回一个给定类型的对象）
         */
        Stream<Double> dstr = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });
        System.out.println(dstr.limit(4));
        Stream<Double> generate = Stream.generate(() -> Math.random());
        System.out.println(generate.limit(100).toArray());
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
        //这段代码就是先获取一个无限长度的正整数集合的Stream，然后取出前10个打印。千万记住使用limit方法，不然会无限打印下去。
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

        /**
         * 通过Collection子类获取Stream
         *
         * Collection接口有一个stream方法，所以其所有子类都可以获取对应的Stream对象。
         */
        System.out.println("-------------------------------");
        /**
         * 转换Stream
         */
        Stream.of(1, 2, 2, 3, 4, 5, 6, 6, 6, 7).distinct().forEach(x -> System.out.println(x));
        System.out.println("-----------------------");
        String[] strs = {"123455", "1223", "12236775"};
        Arrays.stream(strs).mapToLong(p -> {
            // System.out.println(p);
            return Long.parseLong(String.valueOf(p));
        }).forEach(System.out::println);
        System.out.println("-----------------------");
        /**
         * 转换Stream
         */
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);
        System.out.println("-------------------------------------------");
        /**
         * 整体
         * 这段代码演示了上面介绍的所有转换方法（除了flatMap），简单解释一下这段代码的含义：给定一个Integer类型的List，获取其对应的Stream对象，然后进行过滤掉null，再去重，再每个元素乘以2，再每个元素被消费的时候打印自身，在跳过前两个元素，最后去前四个元素进行加和运算(解释一大堆，很像废话，因为基本看了方法名就知道要做什么了。这个就是声明式编程的一大好处！)
         * 。大家可以参考上面对于每个方法的解释，看看最终的输出是什么。
         */
        List<Integer> numss = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + numss.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());
        System.out.println("-------------------------------------------");
        /**
         * 可变汇聚
         */
        List<Integer> numsss = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        List<Integer> numsWithoutNull = numsss.stream().filter(num -> num != null).
                collect(() -> new ArrayList<Integer>(),
                        (list, item) -> list.add(item),
                        (list1, list2) -> list1.addAll(list2));
        numsWithoutNull.forEach(System.out::println);

        List<Integer> nn = nums.stream().filter(num -> num != null).
                collect(Collectors.toList());
    }

}
