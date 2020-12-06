package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class readFile {

    public static List<String> readFileLineByLine(String fileName) {
        List<String> map = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            map = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
