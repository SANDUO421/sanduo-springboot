import java.util.stream.Stream;

/**
 * iterate
 * iterate 跟 reduce 操作很像，接受一个种子值
 * 注意：
 * 在 iterate 时候管道必须有 limit 这样的操作来限制 Stream 大小。
 * @author 三多
 * @Time 2019/4/11
 */
public class LambdaIterate {
    public static void main(String[] args) {
        Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));
    }

}
