package my.streams;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStreams {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5}; // Array - primitive int
        IntStream stream = Arrays.stream(numbers); // returns primitive stream

        Integer[] arr = {1, 2, 3, 4, 5}; // Array - using wrapper class
        Stream<Integer> stream1 = Arrays.stream(arr); // returns Integer Wrapper class stream

        System.out.println(IntStream.range(1, 5).boxed().collect(Collectors.toList()));
        System.out.println(IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList()));

        IntStream.of(1, 2, 3);

        DoubleStream doubles = new Random().doubles(5);
//        System.out.println(doubles.sum());
//        System.out.println(doubles.min());
//        System.out.println(doubles.max());
//        System.out.println(doubles.average());
//        doubles.summaryStatistics()
//        doubles.mapToInt(x -> (int) (x + 1));
        System.out.println(doubles.boxed().collect(Collectors.toList()));

        IntStream intStream = new Random().ints(5);
        System.out.println(intStream.boxed().collect(Collectors.toList()));
    }
}