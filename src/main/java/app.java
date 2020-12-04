import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class app {

    public static void main(String[] args) {
        dayFour();
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

    private static void dayThree() {
        String fileName = "src/main/resources/day3.txt";
        List<char[]> map = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            map = stream.map(String::toCharArray).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Day 4 part 1");
        System.out.println(traverseTrees(map, 1, 3));
        System.out.println("Day 4 part 2");
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
        System.out.println(count);
        return count;
    }

    private static void dayFour() {
        String fileName = "src/main/resources/day4.txt";
        List<String> map = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            map = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> passports = new ArrayList<>();
        StringBuilder helper = new StringBuilder();
        for (String x : map) {
            if (!x.isEmpty()) {
                helper.append(" ").append(x);
            } else {
                passports.add(helper.toString().trim());
                helper = new StringBuilder();
            }
        }
        passports.add(helper.toString().trim());
        long validPassports = passports.stream().filter(pas -> pas.contains("byr") && pas.contains("iyr")
                && pas.contains("eyr") && pas.contains("hgt")
                && pas.contains("hcl") && pas.contains("ecl") && pas.contains("pid")).count();
        System.out.println("Day 4 part 1");
        System.out.println(validPassports);

        //Part 2
        long validPass2 = 0;
        for (String pass : passports) {
            List<String> splited = Arrays.asList(pass.split(" "));
            if (splited.size() < 7) {
                continue;
            }
            int validCondition = 0;
            for (String sp : splited) {
                String[] cells = sp.split(":");
                switch (cells[0]) {
                    case "byr":
                        if (checkByr(cells[1]))
                            validCondition++;
                        break;
                    case "iyr":
                        if (checkIyr(cells[1]))
                            validCondition++;
                        break;
                    case "eyr":
                        if (checkEyr(cells[1]))
                            validCondition++;
                        break;
                    case "hgt":
                        if (checkHgt(cells[1]))
                            validCondition++;
                        break;
                    case "hcl":
                        if (checkHcl(cells[1]))
                            validCondition++;
                        break;
                    case "ecl":
                        if (checkEcl(cells[1]))
                            validCondition++;
                        break;
                    case "pid":
                        if (checkPid(cells[1]))
                            validCondition++;
                        break;
                    default:
                }
            }
            if (validCondition == 7)
                validPass2++;
        }
        System.out.println("Day 4 part 2");
        System.out.println(validPass2);

    }


    //    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    private static boolean checkByr(String value) {
        return Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002;
    }

    //    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    private static boolean checkIyr(String value) {
        return Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020;
    }

    //    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    private static boolean checkEyr(String value) {
        return Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030;
    }

    //    hgt (Height) - a number followed by either cm or in:
    //    If cm, the number must be at least 150 and at most 193.
    //    If in, the number must be at least 59 and at most 76.
    private static boolean checkHgt(String value) {
        if (value.contains("cm")) {
            value = value.replace("cm", "");
            return Integer.parseInt(value) >= 150 && Integer.parseInt(value) <= 193;
        } else if (value.contains("in")) {
            value = value.replace("in", "");
            return Integer.parseInt(value) >= 59 && Integer.parseInt(value) <= 76;
        }
        return false;
    }

    //    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    private static boolean checkHcl(String value) {
        return value.matches("^#[\\da-f]{6}$");
    }

    //ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    private static boolean checkEcl(String value) {
        return value.equals("amb") || value.equals("blu")
                || value.equals("brn") || value.equals("gry")
                || value.equals("grn") || value.equals("hzl")
                || value.equals("oth");
    }

    //pid (Passport ID) - a nine-digit number, including leading zeroes.
    private static boolean checkPid(String value) {
        return value.matches("^\\d{9}$");
    }

}
