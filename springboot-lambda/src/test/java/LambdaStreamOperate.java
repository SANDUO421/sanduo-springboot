import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 三多
 * @Time 2019/4/11
 */
public class LambdaStreamOperate {
    public static void main(String[] args) {
        /**
         * 流的操作
         */
        List<Integer> num = Arrays.asList(1, 2, 3, 4);
        //1->1 映射
        List<Integer> collect = num.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(collect);

        /**
         * flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，
         * 最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。
         */
        //1->n :
        Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 4),
                Arrays.asList(5, 6)
        ).flatMap(childList -> childList.stream()).collect(Collectors.toList()).forEach(System.out::println);


    }
}
