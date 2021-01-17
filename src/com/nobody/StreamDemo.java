package com.nobody;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @Description Stream的创建例子
 * @Author Mr.nobody
 * @Date 2021/1/17
 * @Version 1.0
 */
public class StreamDemo {

    public static void main(String[] args) {
        // 1.集合创建Stream
        // 创建集合
        List<String> list = Arrays.asList("张三", "李四", "王五");
        // 创建一个串行流
        Stream<String> stream = list.stream();
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();

        // 2.数组创建Stream
        // 创建数组
        String[] persons = {"张三", "李四", "王五"};
        // 创建一个串行流
        Stream<String> stream1 = Arrays.stream(persons);
        // 创建一个并行流
        Stream<String> parallelStream1 = Arrays.stream(persons).parallel();

        // 3.Stream静态方法生成Stream
        Stream<String> stream2 = Stream.of("张三", "李四", "王五");

        // 输出5个奇数 1 3 5 7 9
        Stream<Integer> stream3 = Stream.iterate(1, x -> x + 2).limit(5);
        stream3.forEach(System.out::println);

        // 生成2个UUID
        Stream<UUID> stream4 = Stream.generate(UUID::randomUUID).limit(2);
        stream4.forEach(System.out::println);
    }

}
