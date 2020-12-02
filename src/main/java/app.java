import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class app {

    public static void main(String[] args) {
//        dayOne();
        dayTwo();
    }

    private static void dayOne() {
        String fileName = "src/main/resources/day1.txt";
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

    private static void dayTwo() {
        String fileName = "src/main/resources/day2.txt";

        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            AtomicInteger correctPasswordPart1 = new AtomicInteger();
            AtomicInteger correctPasswordPart2 = new AtomicInteger();
            stream.forEach(line -> {
                String[] splited = line.split("\\s+");
                int minCount = Integer.parseInt(splited[0].split("-")[0]);
                int maxCount = Integer.parseInt(splited[0].split("-")[1]);
                char targetChar = splited[1].charAt(0);
                String sequence = splited[2];
                if (sequence.charAt(minCount - 1) == targetChar ^ sequence.charAt(maxCount - 1) == targetChar) {
                    correctPasswordPart2.getAndIncrement();
                }
                long count = sequence.chars().filter(c -> c == targetChar).count();
                if (count >= minCount && count <= maxCount) {
                    correctPasswordPart1.getAndIncrement();
                }
            });
            System.out.println("Day2 part1: " + correctPasswordPart1);
            System.out.println("Day2 part2: " + correctPasswordPart2);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
