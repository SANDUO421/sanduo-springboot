import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 自定义Supplier
 *
 *Stream.generate() 还接受自己实现的 Supplier
 * @author 三多
 * @Time 2019/4/11
 */
public class LambdaSupplier {
    public static void main(String[] args) {
        Stream.generate(new PersonSupplier()).
                limit(10).
                forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));
    }

    public static class PersonSupplier implements Supplier<LambdaLimit.Person> {
        private int index = 0;
        private Random random = new Random();
        @Override
        public LambdaLimit.Person get() {
            return new LambdaLimit.Person(index++, "StormTestUser" + index, random.nextInt(100));
        }
    }

}
