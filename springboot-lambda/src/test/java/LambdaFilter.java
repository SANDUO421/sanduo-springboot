import com.google.common.io.Resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Filter
 * @author 三多
 * @Time 2019/4/11
 */
public class LambdaFilter {
    public static void main(String[] args) {
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        // 留下偶数
        Integer[] evens =
                Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        Arrays.stream(evens).forEach(System.out::print);
        Stream.of(evens).forEach(System.out::print);
        //把单词挑出来

        BufferedReader reader = null;
        try {
            String file = LambdaFilter.class.getClassLoader().getResource("movie.txt").getFile();
            System.out.println(file);
            reader = new BufferedReader(new FileReader(new File(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> output = reader.lines().
                flatMap(line -> Stream.of(line.split(" "))).
                filter(word -> word.length() > 0).
                collect(Collectors.toList());
        System.out.println(output);
    }
}