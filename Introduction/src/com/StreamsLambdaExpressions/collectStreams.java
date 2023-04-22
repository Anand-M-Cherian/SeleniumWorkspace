package com.StreamsLambdaExpressions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class collectStreams {

    public static void main(String[] args) {

        // print the sorted list of unqiue values

        List<Integer> numbers = Arrays.asList(3, 2, 2, 7, 5, 1, 9, 7);
        System.out.println(numbers.stream().sorted().collect(Collectors.toSet()));
    }
}
