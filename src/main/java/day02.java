import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

abstract class day02 {
    static void run() {
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
            System.out.println("Day 2 part 1");
            System.out.println(correctPasswordPart1);
            System.out.println("Day 2 part 2");
            System.out.println(correctPasswordPart2);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
