package my.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEvaluationDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Intermediate Operation
        Stream<String> stream = names.stream()
                .filter(name -> {
                    System.out.println("Filtering: " + name);
                    return name.length() > 3;
                });

        System.out.println("Before terminal operation");

        List<String> result = stream.collect(Collectors.toList());  // Terminal Operation

        System.out.println("After terminal operation");
        System.out.println(result);

        /*
        * Output:
        *   Before terminal operation
            Filtering: Alice
            Filtering: Bob
            Filtering: Charlie
            Filtering: David
            After terminal operation
            [Alice, Charlie, David]
        * */
    }
}