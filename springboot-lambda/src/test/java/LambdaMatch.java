import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Match
 * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
 * anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
 * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
 *
 * 它们都不是要遍历全部元素才能返回结果。例如 allMatch 只要一个元素不满足条件，就 skip 剩下的所有元素
 * @author 三多
 * @Time 2019/4/11
 */
public class LambdaMatch {
    public static void main(String[] args) {

        List<LambdaLimit.Person> persons = new ArrayList();
        persons.add(new LambdaLimit.Person(1, "name" + 1, 10));
        persons.add(new LambdaLimit.Person(2, "name" + 2, 21));
        persons.add(new LambdaLimit.Person(3, "name" + 3, 34));
        persons.add(new LambdaLimit.Person(4, "name" + 4, 6));
        persons.add(new LambdaLimit.Person(5, "name" + 5, 55));

        boolean isAllAdult  = persons.stream().allMatch(p -> p.getAge() > 20);
        System.out.println("All are adult? " + isAllAdult);

        boolean isThereAnyChild = persons.stream().
                anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child? " + isThereAnyChild);

    }


}
