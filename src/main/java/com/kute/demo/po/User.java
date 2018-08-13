package com.kute.demo.po;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * Created by kute on 2018/1/11.
 */
public class User extends Bean {

    private static final long serialVersionUID = 9210067005711579807L;

    private int id;

    private String name;

    private int age;

    public User() {
    }

    public User(int id) {
        this.id = id;
        this.name = RandomStringUtils.randomAlphabetic(10);
        this.age = RandomUtils.nextInt(10, 100);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
