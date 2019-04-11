import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * limit and skip
 * @author 三多
 * @Time 2019/4/11
 */
public class LambdaLimit {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 10000; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        /**
         * 这是一个有 10，000 个元素的 Stream，但在 short-circuiting 操作 limit 和 skip 的作用下，
         * 管道中 map 操作指定的 getName() 方法的执行次数为 limit 所限定的 10 次，
         * 而最终返回结果在跳过前 3 个元素后只有后面 7 个返回。
         */
        List<String> personList2 = persons.stream().
                map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
        System.out.println("personList2:"+personList2);

        List<Person> personss = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i);
            personss.add(person);
        }
        //limit 和 skip 对 sorted 后的运行次数无影响
        List<Person> personList = personss.stream().sorted((p1, p2) ->
                p1.getName().compareTo(p2.getName())).limit(2).collect(Collectors.toList());
        System.out.println("personList:"+personList);
    }


    public static class Person {
        public int no;
        private String name;
        private int age;

        public Person() {
        }

        public Person (int no, String name) {
            this.no = no;
            this.name = name;
        }

        public Person(int no, String name, int age) {
            this.no = no;
            this.name = name;
            this.age = age;
        }

        public String getName() {
            System.out.println(name);
            return name;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
