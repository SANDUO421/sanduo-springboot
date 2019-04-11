import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * sorted 排序：
 * 对 Stream 的排序通过 sorted 进行，它比数组的排序更强之处在于你可以首先对 Stream 进行各类
 * map、filter、limit、skip 甚至 distinct 来减少元素数量后，再排序，这能帮助程序明显缩短执行时间。
 * @author 三多
 * @Time 2019/4/11
 */
public class LambdaSorted {
    public static void main(String[] args) {
        List<LambdaLimit.Person> people = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LambdaLimit.Person person = new LambdaLimit.Person(i,"name"+i);
            people.add(person);
        }
        Stream<LambdaLimit.Person> sorted = people.stream().limit(2).sorted((p1, p2) -> p1.getName().compareTo(p2.getName()));
        System.out.println(sorted.collect(Collectors.toList()));
    }
}
