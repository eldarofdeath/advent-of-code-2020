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

    public static List<String> readFileAsParagraphs(String fileName){
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> paragraphs = new ArrayList<>();
        StringBuilder helper = new StringBuilder();
        for (String x : lines) {
            if (!x.isEmpty()) {
                helper.append(" ").append(x);
            } else {
                paragraphs.add(helper.toString().trim());
                helper = new StringBuilder();
            }
        }
        paragraphs.add(helper.toString().trim());
        return paragraphs;
    }
}
