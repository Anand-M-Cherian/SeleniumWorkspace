package com.StreamsLambdaExpressions;

import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MapStreams {

    public static void main(String[] args) {

        System.out.println("======starting with A and uppercase=======");
        Stream.of("Anand" ,"Jibin", "Nishon", "Amalu", "Allen", "Anu", "Anna", "Calvin").filter(s -> {
            return s.startsWith("A");
        }).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));

        System.out.println();

        System.out.println("======ending with 'n' and uppercase=======");
        List<String> mCOusins = Arrays.asList("Anand" ,"Jibin", "Nishon", "Amalu", "Allen", "Anu", "Anna", "Calvin");
        mCOusins.stream().filter(s -> {
            return s.endsWith("n");
        }).sorted().map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));

        ArrayList<String> pCosuins = new ArrayList<String>();
        pCosuins.add("Alwin");
        pCosuins.add("Ambily");
        pCosuins.add("Neenu");
        pCosuins.add("Henna");
        pCosuins.add("Nevin");
        pCosuins.add("Nimal");
        pCosuins.add("Chippi");

        System.out.println();

        // Merge Streams
        Stream<String> allCousins = Stream.concat(pCosuins.stream(), mCOusins.stream());
        System.out.println("=======All Cousins==========");
        // allCousins.sorted().forEach(s -> System.out.println(s));

        // check condition in stream
        Assert.assertTrue(allCousins.anyMatch(s -> {
            return s.equalsIgnoreCase("Chippi");
        }));
    }
}
