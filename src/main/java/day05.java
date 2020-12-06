import util.readFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class day05 {

    static void run() {
        List<String> lines = readFile.readFileLineByLine("src/main/resources/day5.txt");
        Map<Integer, List<Integer>> seatMap = new HashMap<>();

        List<Integer> possibleSeats = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            possibleSeats.add(i);
        }
        List<Integer> possibleRows = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            possibleRows.add(i);
            seatMap.put(i, new ArrayList<>(possibleSeats));
        }

        int biggestID = 0;
        for (String line : lines) {
            List<Integer> substrRow = new ArrayList<>(possibleRows);
            List<Integer> substrSeat = new ArrayList<>(possibleSeats);
            for (char c : line.toCharArray()) {
                if (c == 'F') {
                    substrRow = getLowerHalf(substrRow);
                } else if (c == 'B') {
                    substrRow = getUpperHalf(substrRow);
                } else if (c == 'L') {
                    substrSeat = getLowerHalf(substrSeat);
                } else if (c == 'R') {
                    substrSeat = getUpperHalf(substrSeat);
                }
            }
            // Remove seat from available seats
            int finalSubstrSeat = substrSeat.get(0);
            seatMap.get(substrRow.get(0)).removeIf(i -> i == finalSubstrSeat);
            // get biggest Id
            int number = substrRow.get(0) * 8 + substrSeat.get(0);
            if (number > biggestID) {
                biggestID = number;
            }
        }
        System.out.println("Day 5 part 1");
        System.out.println(biggestID);

        System.out.println("Day 5 part 2");
        System.out.println(getMySeatId(seatMap));
    }

    static List<Integer> getLowerHalf(List<Integer> list) {
        int size = list.size();
        return new ArrayList<>(list.subList(0, (size) / 2));
    }

    static List<Integer> getUpperHalf(List<Integer> list) {
        int size = list.size();
        return new ArrayList<>(list.subList((size) / 2, size));
    }

    static int getMySeatId(Map<Integer, List<Integer>> seatMap) {
        for (int i = 1; i < seatMap.keySet().size() - 1; i++) {
            if (!seatMap.get(i).isEmpty() && seatMap.get(i - 1).isEmpty() && seatMap.get(i + 1).isEmpty())
                return i * 8 + seatMap.get(i).get(0);
        }
        return 0;
    }
}