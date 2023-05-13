package com.jwt.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        String str = "i am learning   java";
        str = str.replaceAll(" ","");
        System.out.println(str);
        List<String> collect = Arrays.stream(str.split("")).collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()
        )).entrySet().stream().filter(t -> t.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(collect);
    }
}
