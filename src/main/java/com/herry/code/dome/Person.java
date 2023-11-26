package com.herry.code.dome;

public class Person {
    private String name;
    private int age;

    // 构造方法、getter和setter等省略

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        if (age != person.age) {
            return false;
        }
        return name != null ? name.equals(person.name) : person.name == null;
    }

}
//
//class HashMapExample {
//    public static void main(String[] args) {
//        HashMap<Person, String> map = new HashMap<>();
//
//        Person person1 = new Person("Alice", 25);
//        Person person2 = new Person("Bob", 30);
//
//        map.put(person1, "Value 1");
//        map.put(person2, "Value 2");
//
//        // 使用自定义对象作为键进行检索
//        Person searchKey = new Person("Alice", 25);
//        String value = map.get(searchKey);
//        System.out.println(value);  // 输出 "Value 1"
//    }
//}

