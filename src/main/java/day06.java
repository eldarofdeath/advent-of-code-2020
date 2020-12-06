import util.readFile;

import java.util.*;

abstract class day06 {
    static void run() {
        String fileName = "src/main/resources/day6.txt";
        List<String> customsDeclarationForms = readFile.readFileAsParagraphs(fileName);
        part1(customsDeclarationForms);
        part2(customsDeclarationForms);
    }

    private static void part1(List<String> customsDeclarationForms) {
        int count = 0;
        for (String cdf : customsDeclarationForms) {
            Set<Character> chars = new HashSet<>();
            char[] helper = cdf.replace(" ", "").toCharArray();
            for (char aHelper : helper) {
                chars.add(aHelper);
            }
            count += chars.size();
        }
        System.out.println("Day 6 part 1");
        System.out.println(count);
    }

    private static void part2(List<String> customsDeclarationForms) {
        int count = 0;
        for (String cdf : customsDeclarationForms) {
            int numberOfPeople = cdf.split(" ").length;
            Map<Character, Integer> map = new HashMap<>();
            char[] helper = cdf.replace(" ", "").toCharArray();
            for (char aHelper : helper) {
                if (!map.keySet().contains(aHelper)) {
                    map.put(aHelper, 1);
                } else {
                    map.put(aHelper, map.get(aHelper) + 1);
                }
            }
            count += map.values().stream().filter(v -> v == numberOfPeople).count();
        }
        System.out.println("Day 6 part 2");
        System.out.println(count);

    }
}
