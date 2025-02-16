package my.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOperations {
    public static void main(String[] args) {
        // Intermediate operations transform a stream into another stream
        // They are lazy, meaning they don't execute until a Terminal Operation is invoked

        // 1. filter
        List<String> list = Arrays.asList("Aman", "Ram", "Shyam", "Mohit", "Aman");
        Stream<String> filteredStream = list.stream().filter(x -> x.startsWith("A")); // No Filtering at this point, it will happen once you give any Terminal Operation
        System.out.println(filteredStream);
        long res = filteredStream.count();// Terminal Operation (Here Filtering occur & then it will count)
        System.out.println(res);

        // 2. map
        Stream<String> stringStream = list.stream().map(String::toUpperCase);
        System.out.println(stringStream);

        // 3. sorted
        Stream<String> sortedStream = list.stream().sorted();
        Stream<String> sortedStreamUsingComparator = list.stream().sorted(((a, b) -> a.length() - b.length()));

        // 4. distinct
        System.out.println(list.stream().filter(x -> x.startsWith("A")).distinct().count());

        // 5. limit
        Stream.iterate(1, x -> x+1).limit(100).count();

        // 6. skip
        Stream.iterate(1, x -> x+1).skip(10).limit(100).count();

        // 7. flatMap
        // Handle streams of collections, lists, or arrays where each element is itself a collection
        // Flatten nested structures (e.g., lists within lists) so that they can be processed as a single sequence of elements
        // Transform and flatten elements at the same time.

        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "kiwi"),
                Arrays.asList("pear", "grape")
        );
        System.out.println(listOfLists.get(1).get(1)); // kiwi

        System.out.println(listOfLists.stream().
                flatMap(x -> x.stream()).
                map(x -> x.toUpperCase()).
                collect(Collectors.toList())); // [APPLE, BANANA, ORANGE, KIWI, PEAR, GRAPE]

    }
}