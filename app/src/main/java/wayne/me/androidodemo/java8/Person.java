package wayne.me.androidodemo.java8;

/**
 * Created by Wayne on 2017/10/30.
 */

public class Person {
    public int age;
    public String name;

    public Person() {
    }

    public Person(int age) {
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