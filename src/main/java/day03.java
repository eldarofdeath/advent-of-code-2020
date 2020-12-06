import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class day03 {

    static void run() {
        String fileName = "src/main/resources/day3.txt";
        List<char[]> map = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            map = stream.map(String::toCharArray).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Day 3 part 1");
        System.out.println(traverseTrees(map, 1, 3));
        System.out.println("Day 3 part 2");
        long result = traverseTrees(map, 1, 1) * traverseTrees(map, 1, 3) *
                traverseTrees(map, 1, 5) * traverseTrees(map, 1, 7) * traverseTrees(map, 2, 1);
        System.out.println(result);
    }

    private static long traverseTrees(List<char[]> map, int down, int right) {
        int row = 0;
        int column = 0;
        long count = 0;
        while (row < map.size() - 1) {
            row += down;
            column = (column + right) % map.get(row).length;
            char ch = map.get(row)[column];
            if (ch == '#') {
                count++;
            }
        }
        return count;
    }

}
