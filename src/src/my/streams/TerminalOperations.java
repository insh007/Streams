package my.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperations {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5);

        // 1. collect
        list.stream().skip(3).collect(Collectors.toList());

        // 2. forEach
        list.stream().forEach((x) -> System.out.println(x));

        // 3. reduce - Combine elements to produce a single result
        Optional<Integer> reduceSum = list.stream().reduce(Integer::sum);
        System.out.println(reduceSum.get());

        // 4. count
        System.out.println(list.stream().filter(x -> x%2==0).count());

        // 5. anyMatch, allMatch, noneMatch

        boolean b = list.stream().anyMatch(x -> x % 2 == 0);
        System.out.println(b); // true

        boolean b1 = list.stream().allMatch(x -> x > 0);
        System.out.println(b1);

        boolean b2 = list.stream().noneMatch(x -> x < 0);
        System.out.println(b2);

        // Example : Counting Occurrence of a Character
        // Note: sentence.chars() returns an IntStream
        String sentence = "Hello World";
        System.out.println(sentence.chars().filter(x -> x == 'l').count());

        // Stateful Operation - knows about the other elements like sorted, distinct
        // Stateless Operation - does not know about the other elements like map, filter

        // 6. findFirst, findAny
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());

        // 7. toArray()
        Object[] array = Stream.of(1, 2, 3).toArray();

        // 8. min / max
        System.out.println("max: " + Stream.of(2, 44, 69).max((o1, o2) -> o1 - o2)); // 69
        System.out.println("max: " + Stream.of(2, 44, 69).max(Comparator.naturalOrder())); // 69
        System.out.println("min: " + Stream.of(2, 44, 69).max((o1, o2) -> o2 - o1)); // 2
        System.out.println("min: " + Stream.of(2, 44, 69).min(Comparator.naturalOrder())); // 2

        // 9. forEachOrdered - used when you are using parallelStream to print result in order
        List<Integer> numbers0 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Using forEach with parallel stream:");
        numbers0.parallelStream().forEach(System.out::println); // 7, 6, 8, 9, 10, 3, 1, 4, 5, 2
        System.out.println("Using forEachOrdered with parallel stream:");
        numbers0.parallelStream().forEachOrdered(System.out::println); // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

        // Example
        // Streams cannot be reused after a terminal operation has been called
        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");
        Stream<String> stream = names.stream();
        stream.forEach(System.out::println); // terminal operation
//        List<String> list1 = stream.map(String::toUpperCase).toList(); // exception

    }
}