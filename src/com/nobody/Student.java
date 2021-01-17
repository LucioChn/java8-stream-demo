package com.nobody;

/**
 * @Description 学生类
 * @Author Mr.nobody
 * @Date 2021/1/17
 * @Version 1.0
 */
public class Student {
    // 主键
    private String id;
    // 姓名
    private String name;
    // 年龄
    private int age;
    // 性别
    private String sex;
    // 成绩
    private double score;

    public Student(String id, String name, int age, String sex, double score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", age=" + age
                + ", sex=" + sex + ", score=" + score + '}';
    }
}
