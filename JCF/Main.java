package JCF;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<Person> hSet = new HashSet<Person>();
        Person person1 = new Person("name", "surname", 123);
        Person person2 = new Person("name", "surame", 123);
        hSet.add(person1);
        hSet.add(person2);
        System.out.println(hSet.size());
    }
}
