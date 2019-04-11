import java.util.stream.Stream;

/**
 * reduce 阶段
 * 主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），然后依照运算规则（BinaryOperator），
 * 和前面 Stream 的第一个、第二个、第 n 个元素组合。从这个意义上说，
 * 字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。例如 Stream 的 sum 就相当于
 * @author 三多
 * @Time 2019/4/11
 */
public class LamdbaReduce {
    public static void main(String[] args) {

        //链接字符串 concat = "stream"
        String concat = Stream.of("s", "t", "r", "e", "a", "m").reduce("", String::concat);
        System.out.println(concat);
        //求最小值，最大值
        Double min = Stream.of(-1.0, 2.13, 3.3, 4.4, 5.5).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("min:"+min);
        //求和,起始值10
        Integer sum = Stream.of(1, 4, 5, 6, 3, 2).reduce(0, Integer::sum);
        System.out.println("sum:"+sum);
        //求和无起始值
        Integer sum2 = Stream.of(1, 4, 5, 6, 3, 2).reduce(Integer::sum).get();
        //过滤字符串链接
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println(concat);



    }
}
