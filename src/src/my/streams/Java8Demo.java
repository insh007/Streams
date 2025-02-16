package my.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Java8Demo {
    public static void main(String[] args) {
        // Lamda Expression
        // Interface Declaration/reference = Lamda Expression(method implementation)
        MathOperation sumOperation = (a, b) -> a+b;
        int res = sumOperation.operate(5,5);
        System.out.println(res); // 10

        // Interface Declaration/reference = Lamda Expression(method implementation)
        Runnable r1 = () -> System.out.println("Runnable");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(() -> System.out.println("Runnable method"));

        // ==================================================================================

        // Predicate --> Functional interface ( Boolean valued function test() )
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(5)); // false
        System.out.println(isEven.test(2)); // true
        Predicate<String> isWordStartingWithI = x -> x.startsWith("I");
        Predicate<String> isWordEndsWithD = x -> x.endsWith("d");
        Predicate<String> and = isWordStartingWithI.and(isWordEndsWithD);
        System.out.println(and.test("Inshad")); // true

        // ==================================================================================

        // Function --> work for you & return ( apply() )
        Function<Integer, Integer> doubleIt = x -> 2 * x;
        Function<Integer, Integer> tripleIt = x -> 3 * x;
        System.out.println(doubleIt.apply(100)); // 200
        System.out.println(doubleIt.andThen(tripleIt).apply(5)); // 30

        Function<Integer, Integer> identity = Function.identity();
        Integer result = identity.apply(5);
        System.out.println(result); // 5

        // ==================================================================================

        // Consumer --> It takes an argument but not return anything ( accept() )
        Consumer<Integer> consumer = x -> System.out.println(x);
        consumer.accept(55); // 55

        List<Integer> list = Arrays.asList(4, 3, 1);
        Consumer<List<Integer>> printList = x -> {
            for(Integer i : x){
                System.out.println(i);
            }
        };
        printList.accept(list); // 4 3 1

        // ==================================================================================

        // Supplier --> It does not take any argument but return ( get() )
        Supplier<String> supplier = () -> "Hello World";
        System.out.println(supplier.get()); // Hello World

        // Combined Example
        Predicate<Integer> predicate = x -> x % 2 == 0;
        Function<Integer, Integer> function = x -> x * x;
        Consumer<Integer> consumer1 = x -> System.out.println(x);
        Supplier<Integer> supplier1 = () -> 10;

        if(predicate.test(supplier1.get())){
            consumer1.accept(function.apply(supplier1.get())); // 100
        }

        // BiPredicate, BiConsumer, BiFunction
        BiPredicate<Integer, Integer> isSumEven = (a,b) -> (a+b) % 2 == 0;
        System.out.println(isSumEven.test(5,5)); // true

        BiConsumer<Integer, String> biConsumer = (a,b) -> {
            System.out.println(a);
            System.out.println(b);
        };
        biConsumer.accept(4,"Insh");

        BiFunction<String, String, Integer> biFunction = (a,b) -> a.length() + b.length();
        System.out.println(biFunction.apply("c", "dd"));

        // UnaryOperator, BinaryOperator
        Function<Integer, Integer> doub = x -> 2 * x; // same, if argument type is same then we can replace Function with UnaryOperator
        System.out.println(doub.apply(5)); // 10
        UnaryOperator<Integer> db = x -> 2 * x;       // same
        System.out.println(db.apply(5)); // 10

        BiFunction<Integer, Integer, Integer> biFunction1 = (a,b) -> a+b; // if argument type is sane then we can replace BiFunction with BinaryOperator
        System.out.println(biFunction1.apply(2,2)); // 4 // same
        BinaryOperator<Integer> binaryOperator = (a,b) -> a+b;
        System.out.println(binaryOperator.apply(2,2)); // same

        // Method reference --> use method without invoking & in place of lamda expression
        List<String> students = Arrays.asList("Inshad", "Ali", "Khan");
        students.forEach(x -> System.out.println(x));
        students.forEach(System.out::println); // Using Method reference

        // Constructor reference -->
        List<String> names = Arrays.asList("Samsung", "Moto", "Oppo");
        List<MobilePhone> mobilePhoneList = names.stream().map(x -> new MobilePhone(x)).collect(Collectors.toList());
        mobilePhoneList.forEach(System.out::println);
        List<MobilePhone> mobilePhoneLs = names.stream().map(MobilePhone::new).collect(Collectors.toList()); // Using Constructor reference
        mobilePhoneLs.forEach(System.out::println);

    }
}

class MobilePhone {
    String name;

    public MobilePhone(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "name='" + name + '\'' +
                '}';
    }
}

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}