import util.readFile;

import java.util.Arrays;
import java.util.List;

abstract class day04 {
    static void run() {
        String fileName = "src/main/resources/day4.txt";
        List<String> passports = readFile.readFileAsParagraphs(fileName);
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
