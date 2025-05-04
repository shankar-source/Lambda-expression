package LambdaExpresion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This class demonstrates Java features using lambda expressions.
 * Includes functional interfaces, Streams API, exception handling, 
 * file operations, event handling, database queries, and web requests.
 */
public class LambdaExample {

    public static void main(String[] args) {
        LambdaExample examples = new LambdaExample();

        System.out.println("Function Example: Getting String Length");
        examples.functionExample();

        System.out.println("Predicate Example: Checking Even Numbers");
        examples.predicateExample();

        System.out.println("Consumer Example: Printing Greetings");
        examples.consumerExample();

        System.out.println("Supplier Example: Generating Messages");
        examples.supplierExample();

        System.out.println("Custom Functional Interface Example");
        examples.customFunctionalInterfaceExample();

        System.out.println("Filtering Names Using Stream API");
        examples.streamFilterExample();

        System.out.println("Mapping Names to Uppercase");
        examples.streamMapExample();

        System.out.println("Reducing Numbers Using Stream API");
        examples.streamReduceExample();

        System.out.println("Method References Example");
        examples.methodReferenceExample();

        System.out.println("Handling Nulls Using Optional");
        examples.optionalExample();

        System.out.println("Sorting Data Using Lambda");
        examples.sortingWithLambda();

        System.out.println("Grouping Data Using Streams");
        examples.groupingWithStreams();

        System.out.println("Multithreading Using Lambda");
        examples.multithreadingExample();

        System.out.println("Parallel Processing Using Streams");
        examples.parallelStreamExample();

        System.out.println("Exception Handling Inside Lambda");
        examples.lambdaWithExceptionHandling();

        System.out.println("File Operations Using Lambda");
        examples.fileOperationsExample();

        System.out.println("Simulating Database Queries Using Lambda");
        examples.databaseSimulationExample();

        System.out.println("Making HTTP Requests Using Lambda");
        examples.httpClientExample();
    }

    public void functionExample() {
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of 'Ramesh': " + stringLength.apply("Ramesh"));//6
    }

    public void predicateExample() {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 8 even? " + isEven.test(8)); //true
        System.out.println("Is 11 even? " + isEven.test(11)); //false
    }

    public void consumerExample() {
        Consumer<String> greet = name -> System.out.println("Namaste, " + name);
        greet.accept("Vikas");
    }

    public void supplierExample() {
        Supplier<String> supplyGreeting = () -> "Welcome to India!";
        System.out.println(supplyGreeting.get());
    }

    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    public void customFunctionalInterfaceExample() {
        MathOperation addition = (a, b) -> a + b;
        MathOperation multiplication = (a, b) -> a * b;
        System.out.println("Addition: " + addition.operate(10, 5)); 
        System.out.println("Multiplication: " + multiplication.operate(10, 5)); 
    }

    public void streamFilterExample() {
        List<String> names = Arrays.asList("Amit", "Rahul", "Sita", "Krishna", "Arjun", "Manoj");
        List<String> filteredNames = names.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList());
        System.out.println("Filtered names: " + filteredNames);//amit arjun
    }

    public void streamMapExample() {
        List<String> names = Arrays.asList("amit", "rahul", "sita");
        List<String> upperNames = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperNames);
    }

    public void streamReduceExample() {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Sum of numbers: " + sum);
    }

    public void methodReferenceExample() {
        List<String> names = Arrays.asList("Vikram", "Santosh", "Pooja");
        names.forEach(System.out::println);
    }

    public void optionalExample() {
        Optional<String> city = Optional.ofNullable(null);
        System.out.println("City (or default): " + city.orElse("Delhi"));
    }

    public void sortingWithLambda() {
        List<String> cities = Arrays.asList("Mumbai", "Kolkata", "Bengaluru", "Delhi");
        cities.sort(String::compareTo);
        System.out.println("Sorted cities: " + cities);
    }

    public void groupingWithStreams() {
        List<String> names = Arrays.asList("Raj", "Ramesh", "Sita", "Sanjay", "Arjun", "Asha");
        Map<Character, List<String>> groupedNames = names.stream().collect(Collectors.groupingBy(name -> name.charAt(0)));
        System.out.println("Grouped names by first letter: " + groupedNames);
    }

    public void multithreadingExample() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Task 1 executed by " + Thread.currentThread().getName()));
        executor.submit(() -> System.out.println("Task 2 executed by " + Thread.currentThread().getName()));
        executor.shutdown();
    }

    public void parallelStreamExample() {
        List<String> names = Arrays.asList("Ram", "Krishna", "Arjun", "Manoj", "Sita", "Meera");
        names.parallelStream().forEach(name -> System.out.println(Thread.currentThread().getName() + " processed: " + name));
    }

    public void lambdaWithExceptionHandling() {
        Consumer<Integer> safePrint = i -> {
            try {
                if (i == 0) throw new ArithmeticException("Division by zero!");
                System.out.println(10 / i);
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        };

        safePrint.accept(2);
        safePrint.accept(0);
    }

    public void fileOperationsExample() {
        try {
            Path path = Files.createTempFile("lambda", ".txt");
            Files.write(path, Arrays.asList("Hello from Java Lambda!"));
            Files.lines(path).forEach(System.out::println);
            Files.delete(path);
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }

    public void databaseSimulationExample() {
        List<String> database = Arrays.asList("Ramesh", "Sita", "Rahul", "Amit");
        String searchQuery = "Sita";
        Optional<String> result = database.stream().filter(name -> name.equalsIgnoreCase(searchQuery)).findFirst();
        System.out.println("Database Query Result: " + result.orElse("Not Found"));
    }

    public void httpClientExample() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/posts/1")).GET().build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(System.out::println).join();
    }
}
