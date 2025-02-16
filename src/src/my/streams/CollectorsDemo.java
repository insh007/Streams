package my.streams;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsDemo {
    public static void main(String[] args) {
        // Collectors is a utility class
        // provides a set of methods to create common collectors

        // 1. Collecting to a List
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> res = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(res); // [Alice]

        // 2. Collecting to a Set
        List<Integer> nums = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        Set<Integer> set = nums.stream().collect(Collectors.toSet());
        System.out.println(set); // [1, 2, 3, 4, 5] - unique elements

        // 3. Collecting to a Specific Collection
        ArrayDeque<String> collect = names.stream().collect(Collectors.toCollection(() -> new ArrayDeque<>())); // convert into another collection

        // 4. Joining Strings
        // Concatenates stream elements into a single String
        String concatenatedNames = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println(concatenatedNames); // ALICE, BOB, CHARLIE

        // 5. Summarizing Data
        // Generates statistical summary (count, sum, min, average, max)

        List<Integer> numbers = Arrays.asList(2, 3, 5, 7, 11);
        IntSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingInt(x -> x));
        System.out.println("Count: " + stats.getCount()); // Count: 5
        System.out.println("Sum: " + stats.getSum()); // Sum: 28
        System.out.println("Min: " + stats.getMin()); // Min: 2
        System.out.println("Average: " + stats.getAverage()); // Average: 5.6
        System.out.println("Max: " + stats.getMax()); // Max: 11

        // 6. Calculating Averages
        Double average = numbers.stream().collect(Collectors.averagingInt(x -> x));
        System.out.println("Average: " + average); // Average: 5.6

        // 7. Counting Elements
        Long count = numbers.stream().collect(Collectors.counting());
        System.out.println("Count: " + count); // Count: 5

        // 8. Grouping Elements
        List<String> words = Arrays.asList("hello", "world", "java", "streams", "collecting");
        System.out.println(words.stream().collect(Collectors.groupingBy(x -> x.length()))); // {4=[java], 5=[hello, world], 7=[streams], 10=[collecting]}

        // group and then perform some action
        System.out.println(words.stream().collect(Collectors.groupingBy(x -> x.length(), Collectors.joining(", ")))); // {4=java, 5=hello, world, 7=streams, 10=collecting}
        System.out.println(words.stream().collect(Collectors.groupingBy(x -> x.length(), Collectors.counting()))); // {4=1, 5=2, 7=1, 10=1}

        // to return any map - TreeMap, LinkedHashMap etc
        TreeMap<Integer, Long> treeMap = words.stream().collect(Collectors.groupingBy(x -> x.length(), TreeMap::new, Collectors.counting()));
        System.out.println(treeMap); // {4=1, 5=2, 7=1, 10=1}

        // 9. Partitioning Elements
        //  Partitions elements into two groups (true and false) based on a predicate
        System.out.println(words.stream().collect(Collectors.partitioningBy(x -> x.length() > 5))); // {false=[hello, world, java], true=[streams, collecting]}

        // 10. Mapping and Collecting
        // Applies a mapping function before collecting
        System.out.println(words.stream().collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.toList()))); // [HELLO, WORLD, JAVA, STREAMS, COLLECTING]
        System.out.println(words.stream().map(x -> x.toUpperCase()).collect(Collectors.toList())); //    Same Output -[HELLO, WORLD, JAVA, STREAMS, COLLECTING]

        // Example 1: Collecting Names by Length
        List<String> l1 = Arrays.asList("Anna", "Bob", "Alexander", "Brian", "Alice");
        System.out.println(l1.stream().collect(Collectors.groupingBy(String::length))); // {3=[Bob], 4=[Anna], 5=[Brian, Alice], 9=[Alexander]}

        // Example 2: Counting Word Occurrences
        String sentence = "hello world hello java world";
        String[] s = sentence.split(" "); // convert string into array
        System.out.println(s[3]); // java
        System.out.println(Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(x -> x, Collectors.counting()))); // {java=1, world=2, hello=2}

        // Example 3: Partitioning Even and Odd Numbers
        List<Integer> l2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(l2.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0))); // {false=[1, 3, 5], true=[2, 4, 6]}

        // Example 4: Summing Values in a Map
        Map<String, Integer> items = new HashMap<>();
        items.put("Apple", 10);
        items.put("Banana", 20);
        items.put("Orange", 15);
        System.out.println(items.values().stream().reduce(Integer::sum)); // Optional[45]
        System.out.println(items.values().stream().collect(Collectors.summingInt(x -> x))); // 45

        // Example 5: Creating a Map from Stream Elements
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");
        System.out.println(fruits.stream().collect(Collectors.toMap(x -> x.toUpperCase(), x -> x.length())));  // {CHERRY=6, APPLE=5, BANANA=6}

        // Example 6:
        List<String> words2 = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        System.out.println(words2.stream().collect(Collectors.toMap(
                k -> k, // Key: The word itself
                v -> 1, // Value: Assign an initial count of 1
                (x, y) -> x + y // Merge function: If key already exists, add values
        ))); // {orange=1, banana=2, apple=3}

    }
}