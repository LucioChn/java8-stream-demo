package com.nobody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Mr.nobody
 * @Date 2021/1/17
 * @Version 1.0
 */
public class StudentStreamDemo {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(16);
        students.add(new Student("1", "张三", 18, "male", 88));
        students.add(new Student("2", "李四", 17, "male", 60));
        students.add(new Student("3", "王五", 18, "male", 100));
        students.add(new Student("4", "赵六", 20, "male", 10));
        students.add(new Student("5", "董七", 14, "female", 95));
        students.add(new Student("6", "幺八", 21, "male", 55));
        students.add(new Student("7", "老九", 20, "female", 66));
        students.add(new Student("8", "小明", 18, "male", 100));
        students.add(new Student("9", "小红", 22, "female", 95));
        students.add(new Student("10", "小张", 25, "male", 90));

        // 1.遍历forEach
        System.out.println("---------------------------forEach-------------------------------");
        students.stream().forEach(System.out::println);

        // 2.过滤filter
        System.out.println("---------------------------filter-------------------------------");
        // 过滤出成绩100分的学生
        List<Student> students1 = students.stream().filter(student -> student.getScore() == 100)
                .collect(Collectors.toList());
        students1.forEach(System.out::println);

        // 3.查找find
        System.out.println("---------------------------find-------------------------------");
        // 串行流，匹配第一个
        Optional<Student> studentOptional =
                students.stream().filter(student -> student.getAge() >= 20).findFirst();
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            System.out.println(student);
        }
        // 上面输出语句可简写如下
        // studentOptional.ifPresent(System.out::println);
        // 并行流，匹配任意一个，findFirst一般用于并行流
        Optional<Student> studentOptiona2 =
                students.parallelStream().filter(student -> student.getAge() >= 20).findAny();
        studentOptiona2.ifPresent(System.out::println);

        // 4.匹配match
        System.out.println("---------------------------match-------------------------------");
        // 是否存在100分的学生
        boolean anyMatch = students.stream().anyMatch(student -> student.getScore() == 100);
        // 是否全部学生都100分
        boolean allMatch = students.stream().allMatch(student -> student.getScore() == 100);
        // 是否全部学生都没有100分
        boolean noneMatch = students.stream().noneMatch(student -> student.getScore() == 100);
        System.out.println(anyMatch);
        System.out.println(allMatch);
        System.out.println(noneMatch);

        // 5.映射map
        System.out.println("---------------------------map-------------------------------");
        // 获取每个学生的姓名
        List<String> studentNames =
                students.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(studentNames);
        // 每个学生的成绩加10分
        List<Double> studentScores = students.stream().map(student -> student.getScore() + 10)
                .collect(Collectors.toList());
        System.out.println(studentScores);

        List<String> list = Arrays.asList("a-b-c-d", "g-h-i");
        List<String> list1 = list.stream().flatMap(s -> Arrays.stream(s.split("-")))
                .collect(Collectors.toList());
        System.out.println(list1);

        // 6.截取流 skip limit
        System.out.println("---------------------------limit-------------------------------");
        // limit方法用于获取指定数量的流。例如下面演示取出学习成绩大于70的5个人
        List<Student> students2 = students.stream().filter(student -> student.getScore() > 70)
                .limit(5).collect(Collectors.toList());
        students2.forEach(System.out::println);
        // 跳过第一个再取2个
        List<Student> students8 = students.stream().skip(1).limit(2).collect(Collectors.toList());
        // 获取5个int随机数,按从小到大排序
        Random random = new Random();
        random.ints().limit(5).sorted().forEach(System.out::println);

        // 7.排序sorted
        System.out.println("---------------------------sorted-------------------------------");
        // 按成绩升序
        List<Student> students3 = students.stream().sorted(Comparator.comparing(Student::getScore))
                .collect(Collectors.toList());
        System.out.println("按成绩升序");
        students3.forEach(System.out::println);
        // 按成绩降序
        List<Student> students4 =
                students.stream().sorted(Comparator.comparing(Student::getScore).reversed())
                        .collect(Collectors.toList());
        System.out.println("按成绩降序");
        students4.forEach(System.out::println);
        // 按成绩升序，再按年龄升序
        List<Student> students5 = students.stream()
                .sorted(Comparator.comparing(Student::getScore).thenComparing(Student::getAge))
                .collect(Collectors.toList());
        System.out.println("按成绩升序，再按年龄升序");
        students5.forEach(System.out::println);
        // 按成绩升序，再按年龄降序
        List<Student> students6 = students.stream().sorted((s1, s2) -> {
            if (s1.getScore() != s2.getScore()) {
                return (int) (s1.getScore() - s2.getScore());
            } else {
                return (s2.getAge() - s1.getAge());
            }
        }).collect(Collectors.toList());
        System.out.println("按成绩升序，再按年龄降序");
        students6.forEach(System.out::println);

        // 8.去重distinct
        System.out.println("---------------------------distinct-------------------------------");
        List<String> list2 = Arrays.asList("a", "b", "a", "c", "f", "e", "f");
        List<String> list3 = list2.stream().distinct().collect(Collectors.toList());
        Set<String> stringSet = list2.stream().collect(Collectors.toSet()); // list转set也可以达到去重效果
        System.out.println(list3);
        System.out.println(stringSet);

        // 9.统计
        System.out.println("---------------------------statistics-------------------------------");
        // 总和统计summaryStatistics
        DoubleSummaryStatistics doubleSummaryStatistics =
                students.stream().mapToDouble(Student::getScore).summaryStatistics();
        System.out.println("平均值：" + doubleSummaryStatistics.getAverage());
        System.out.println("总个数：" + doubleSummaryStatistics.getCount());
        System.out.println("最大值：" + doubleSummaryStatistics.getMax());
        System.out.println("最小值：" + doubleSummaryStatistics.getMin());
        System.out.println("总和值：" + doubleSummaryStatistics.getSum());
        // 统计个数
        long count = students.stream().count();
        // 平均值
        Double averageScore =
                students.stream().collect(Collectors.averagingDouble(Student::getScore));
        // 最大值和最小值
        Optional<Double> maxScore = students.stream().map(Student::getScore).max(Double::compare);
        Optional<Double> minScore = students.stream().map(Student::getScore).min(Double::compare);
        // 求和
        double sumScore = students.stream().mapToDouble(Student::getScore).sum();
        // 一次性统计所有
        DoubleSummaryStatistics doubleSummaryStatistics1 =
                students.stream().collect(Collectors.summarizingDouble(Student::getScore));
        System.out.println("单个维度计算：");
        System.out.println("统计个数：" + count);
        System.out.println("平均值：" + averageScore);
        maxScore.ifPresent(aDouble -> System.out.println("最大值：" + aDouble));
        minScore.ifPresent(aDouble -> System.out.println("最小值：" + aDouble));
        System.out.println("求和：" + sumScore);
        System.out.println("一次性统计所有：" + doubleSummaryStatistics1);

        // 10.规约
        System.out.println("---------------------------reduce-------------------------------");
        List<Integer> integerList = Arrays.asList(6, 7, 1, 10, 11, 7, 13, 20);
        // 求和
        Optional<Integer> sum1 = integerList.stream().reduce(Integer::sum);
        // 求和,基于10的基础上
        Integer sum2 = integerList.stream().reduce(10, Integer::sum);
        // 求最大值
        Optional<Integer> max1 = integerList.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值,基于与50比较的基础上
        Integer max2 = integerList.stream().reduce(50, Integer::max);
        Optional<Integer> min = integerList.stream().reduce(Integer::min);
        // 求乘积
        Optional<Integer> product = integerList.stream().reduce((x, y) -> x * y);
        System.out.println("原始集合：" + integerList);
        System.out.println("集合求和：" + sum1.get() + "," + sum2);
        System.out.println("集合求最大值：" + max1.get() + "," + max2);
        System.out.println("集合求最小值：" + min.get());
        System.out.println("集合求积：" + product.get());

        // 11.归集 toList，toSet，toMap
        System.out.println(
                "---------------------------toList，toSet，toMap-------------------------------");
        // 获取学生名字，形成新的list集合
        List<String> studentNames1 =
                students.stream().map(Student::getName).collect(Collectors.toList());
        // 获取年龄大于等于15的年龄set集合
        Set<Integer> ageSet = students.stream().filter(student -> student.getAge() >= 15)
                .map(Student::getAge).collect(Collectors.toSet());
        // 创建学生ID和学生实体的map
        Map<String, Student> studentMap =
                students.stream().collect(Collectors.toMap(Student::getId, student -> student));
        System.out.println(studentNames1);
        System.out.println(ageSet);
        studentMap.forEach((key, value) -> System.out.println(key + ":" + value));


        // 12.分组 partitioningBy，groupingBy
        System.out.println(
                "---------------------------partitioningBy，groupingBy-------------------------------");
        // 按条件学生成绩是否大于等于60，划分为2个组
        Map<Boolean, List<Student>> studentScorePart = students.stream()
                .collect(Collectors.partitioningBy(student -> student.getScore() >= 60));
        // 按性别分组
        Map<String, List<Student>> studentSexMap =
                students.stream().collect(Collectors.groupingBy(Student::getSex));
        // 按年龄分组
        Map<Integer, List<Student>> studentAgeMap =
                students.stream().collect(Collectors.groupingBy(Student::getAge));
        // 先按性别分组，再按年龄分组
        Map<String, Map<Integer, List<Student>>> collect = students.stream().collect(
                Collectors.groupingBy(Student::getSex, Collectors.groupingBy(Student::getAge)));
        System.out.println("按条件学生成绩是否大于等于60，划分为2个组:");
        studentScorePart.forEach((aBoolean, students7) -> {
            System.out.println("成绩大于等于60?:" + aBoolean);
            students7.forEach(System.out::println);
        });
        System.out.println("按性别分组:");
        studentSexMap.forEach((sex, students7) -> {
            System.out.println("性别?:" + sex);
            students7.forEach(System.out::println);
        });
        System.out.println("按年龄分组:");
        studentAgeMap.forEach((age, students7) -> {
            System.out.println("年龄:" + age);
            students7.forEach(System.out::println);
        });
        System.out.println("先按性别分组，再按年龄分组:");
        collect.forEach((sex, integerListMap) -> {
            System.out.println("性别：" + sex);
            integerListMap.forEach((age, students7) -> {
                System.out.println("年龄:" + age);
                students7.forEach(System.out::println);
            });
        });

        // 13.合并
        System.out.println("---------------------------joining-------------------------------");
        String joinName = students.stream().map(Student::getName).collect(Collectors.joining(", "));
        System.out.println(joinName);

    }
}
