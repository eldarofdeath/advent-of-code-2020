import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class app {

    public static void main(String[] args) {
        String fileName = "src/main/resources/input.txt";

        System.out.println("Day 1 part 1");
        System.out.println(String.valueOf(dayOnePartOne(fileName)));

        System.out.println("Day 1 part 2");
        System.out.println(String.valueOf(dayOnePartTwo(fileName)));
    }

    private static int dayOnePartOne(String fileName) {
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : list) {
            for (String line2 : list) {
                if (Integer.parseInt(line) + Integer.parseInt(line2) == 2020) {
                    return Integer.parseInt(line) * Integer.parseInt(line2);
                }
            }
        }
        return 0;
    }

    private static int dayOnePartTwo(String fileName) {
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream.filter(line -> Integer.parseInt(line) < 1000).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String first : list) {
            for (String second : list) {
                for (String third : list) {
                    if (Integer.parseInt(first) + Integer.parseInt(second) + Integer.parseInt(third) == 2020) {
                        return Integer.parseInt(first) * Integer.parseInt(second) * Integer.parseInt(third);
                    }
                }

            }
        }
        return 0;
    }
}
