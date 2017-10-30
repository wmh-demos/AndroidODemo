package wayne.me.androidodemo.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Wayne on 2017/10/30.
 */

public class Person {

    public interface Transformer<F, T> {
        T transform(F obj);
    }

    public static <F, T> List<T> transform(List<F> list, Transformer<F, T> transformer) {
        ArrayList<T> result = new ArrayList<>(list.size());
        for (F obj : list) {
            result.add(transformer.transform(obj));
        }
        return result;
    }

    public void test() {
        List<Integer> ageList = Arrays.asList(1, 2, 3, 4, 5);

        //原始写法
        List<Person> list1 = transform(ageList, new Transformer<Integer, Person>() {
            @Override
            public Person transform(Integer age) {
                return Person.from(age);
            }
        });

        //使用Lambda表达式
        List<Person> list2 = transform(ageList, obj -> Person.from(age));

        //method引用：对static method的引用 (ContainingClass::staticMethodName)
        List<Person> list3 = transform(ageList, Person::from);

        //method引用：对constructor的引用 (ClassName::new
        List<Person> list4 = transform(ageList, Person::new);

        //method引用：对一个特定对象的instance method的引用 (containingObject::instanceMethodName)
        List<Person> list5 = transform(ageList, this::createPerson);

        list1.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.compareByAge(o2);
            }
        });
        list1.sort((o1, o2) -> o1.compareByAge(o2));
        //method引用：对一个特定类型的任意对象的instance method的引用 (ContainingType::methodName)
        list1.sort(Person::compareByAge);
    }

    public int age;
    public String name;

    public Person() {
    }

    private Person(int age) {
        this.age = age;
        this.name = "P-" + age;
    }

    public Person createPerson(int age) {
        return new Person(age);
    }

    public static Person from(int age) {
        return new Person(age);
    }

    public int compareByAge(Person other) {
        return age - other.age;
    }

    @Override
    public String toString() {
        return "Person[name=" + name + ", age=" + age + "]";
    }
}