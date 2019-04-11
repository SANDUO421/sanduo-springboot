import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * min/max/distinct
 * min 和 max 的功能也可以通过对 Stream 元素先排序，再 findFirst 来实现，但前者的性能会更好，为 O(n)，
 * 而 sorted 的成本是 O(n log n)。同时它们作为特殊的 reduce 方法被独立出来也是因为求最大最小值是很常见的操作。
 * @author 三多
 * @Time 2019/4/11
 */
public class LambdaDistinct {
    public static void main(String[] args) throws Exception {
        BufferedReader br = getBufferedReader();
        //求最长
        int longest = br.lines().mapToInt(String::length).max().getAsInt();
        br.close();
        System.out.println(longest);

        BufferedReader brr = getBufferedReader();
        //distinct
        List<String> words = brr.lines().
                flatMap(line -> Stream.of(line.split(" "))).
                filter(word -> word.length() > 0).
                map(String::toLowerCase).
                distinct().
                sorted().
                collect(Collectors.toList());
        brr.close();
        System.out.println(words);
    }

    private static BufferedReader getBufferedReader() throws FileNotFoundException {
        String file = LambdaDistinct.class.getResource("length.log").getFile();
        return new BufferedReader(new FileReader(file));
    }
}
