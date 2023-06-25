package com.ebrightmoon.architecture;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test() {
        Person person = new Person(19);
        Person person2 = new Person(18);
        modify(person, person2);
        System.out.println(person.getAge());
        System.out.println(person2.getAge());
    }

    public static void modify(Person p1, Person p2) {
        p1.setAge(30);
        p2 = new Person(29);
        p2.setAge(31);

    }

}