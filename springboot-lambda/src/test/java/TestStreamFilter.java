import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 三多
 * @Time 2019/4/11
 */
public class TestStreamFilter {

    public static void main(String[] args) {
        //获取students
        ArrayList<Student> list = new TestStreamFilter().getStudents();
        System.out.println(list);
        //java 7 的排序
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //升序
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(list);
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //降序
                return o2.getAge() - o1.getAge();
            }
        });
        System.out.println(list);
        //降序
        List<Student> list1 = list.stream().filter(t -> t.getAge() > 10).sorted(Comparator.comparing(Student::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println(list1);
        //升序
        List<Student> list2 = list.parallelStream().filter(t -> t.getAge() > 20).sorted(Comparator.comparing(Student::getAge))
                .collect(Collectors.toList());
        System.out.println(list2);
        /**
         * 构造流的几种常见操作
         */
        //初始化一个stream
        Stream<String> stream = Stream.of("a", "b", "c");
        System.out.println("1" + stream);
        //Array
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        System.out.println("2" + stream);
        stream = Arrays.stream(strArray);
        System.out.println("3" + stream);
        //collection
        List<String> strList = Arrays.asList(strArray);
        stream = strList.stream();
        System.out.println("4" + stream);

        /**
         * 基本三种数据流的操作
         */
        IntStream.of(new int[]{6, 3, 2, 4}).forEachOrdered(System.out::print);
        System.out.println();
        IntStream.of(new int[]{6, 3, 2, 4}).sorted().forEach(System.out::print);
        System.out.println();
        //开区间
        IntStream.range(1, 6).forEachOrdered(System.out::print);
        System.out.println();
        //闭区间
        IntStream.rangeClosed(2, 5).forEachOrdered(System.out::print);
        System.out.println();

        /**
         * 流转换为其它数据结构
         * 一个 Stream 只可以使用一次，上面的代码为了简洁而重复使用了数次。
         */
        Stream<String> stream2 = Stream.of("a", "b", "c");
        // 1. Array
        //String[] strArray1 = stream2.toArray(String[]::new);
        // 2. Collection
        //List<String> list3 = stream2.collect(Collectors.toList());
        //List<String> list4 = stream2.collect(Collectors.toCollection(ArrayList::new));
        //Set set1 = stream2.collect(Collectors.toSet());
        //Stack stack1 = stream2.collect(Collectors.toCollection(Stack::new));
        // 3. String
        String str = stream2.collect(Collectors.joining()).toString();
        System.out.println(str);





    }

    /**
     * 获取List对象
     *
     * @return
     */
    private ArrayList<Student> getStudents() {
        ArrayList<Student> list = new ArrayList<Student>();

        Student stu1 = new Student();
        stu1.setAge(19);
        stu1.setUsable(true);

        Student stu2 = new Student();
        stu2.setAge(30);
        stu2.setUsable(true);

        Student stu3 = new Student();
        stu3.setAge(27);
        stu3.setUsable(false);

        list.add(stu1);
        list.add(stu2);
        list.add(stu3);
        return list;
    }

    class Student {
        private int age;


        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public boolean isUsable() {
            return usable;
        }

        public void setUsable(boolean usable) {
            this.usable = usable;
        }

        private boolean usable;

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", usable=" + usable +
                    '}';
        }

    }
}