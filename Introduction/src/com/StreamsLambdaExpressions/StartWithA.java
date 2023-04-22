package com.StreamsLambdaExpressions;

import java.util.ArrayList;

public class StartWithA {

    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<String>();
        names.add("Anand");
        names.add("Jibin");
        names.add("Nishon");
        names.add("Amalu");
        names.add("Allen");
        names.add("Anu");
        names.add("Anna");
        names.add("Calvin");

        // Convert to Stream > Intermediate op > Terminal op
        // Terminal op wont work if intermidiate op returns false
        // Without terminal op there is no life for intermiate op

        Long count = names.stream().filter(s -> {
            return s.startsWith("A");
        }).count();     // count always returns long datatype

        // Streams operate on elements of collection in a parallel manner

        System.out.println(count);

        // forEach -> operates on each element returned by the filtered stream
        names.stream().filter(s -> {
            return s.length() > 4;
        }).forEach(s -> System.out.println(s));

        // limit the filtered stream
        names.stream().filter(s -> {
            return s.length() > 4;
        }).limit(1).forEach(s -> System.out.println(s));

    }
}
