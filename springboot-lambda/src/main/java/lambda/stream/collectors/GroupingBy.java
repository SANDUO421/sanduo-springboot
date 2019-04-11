package lambda.stream.collectors;

import lambda.stream.entity.Person;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * groupingBy/partitioningBy
 * @author 三多
 * @Time 2019/4/11
 */
public class GroupingBy {
    public static void main(String[] args) {
        //按照年龄归组
        Map<Integer, List<Person>> map = Stream.generate(new PersonSupplier()).limit(100).collect(Collectors.groupingBy(Person::getAge));
        Iterator<Map.Entry<Integer, List<Person>>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer, List<Person>> persons  = it.next();
            System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
        }
        //按照未成年人和成年人归组
        /**
         * partitioningBy 其实是一种特殊的 groupingBy，它依照条件测试的是否两种结果
         * 来构造返回的数据结构，get(true) 和 get(false) 能即为全部的元素对象。
         */
        Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()).
                limit(100).
                collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println("Children number: " + children.get(true).size());
        System.out.println("Adult number: " + children.get(false).size());
        
    }

    public static class PersonSupplier implements Supplier<Person> {
        private int index = 0;
        private Random random = new Random();
        @Override
        public Person get() {
            return new Person(index++, "StormTestUser" + index, random.nextInt(100));
        }
    }

}
