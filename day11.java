import java.util.*;

public class GalaxyShortestPath {

    public static void main(String[] args) {
        String[] input = {
            "...#......",
            ".......#..",
            "#.........",
            "..........",
            "......#...",
            ".#........",
            ".........#",
            "..........",
            ".......#..",
            "#...#....."
        };

        int result = sumOfShortestPaths(input);
        System.out.println("Sum of shortest paths: " + result);
    }

    public static int sumOfShortestPaths(String[] input) {
        char[][] universe = new char[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            universe[i] = input[i].toCharArray();
        }

        Map<Character, Integer> galaxyNumbers = new HashMap<>();
        int galaxyCount = 0;
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[0].length; j++) {
                if (universe[i][j] == '#') {
                    galaxyNumbers.put(universe[i][j], ++galaxyCount);
                }
            }
        }

        int sumOfShortestPaths = 0;
        for (char galaxy1 : galaxyNumbers.keySet()) {
            for (char galaxy2 : galaxyNumbers.keySet()) {
                if (galaxy1 != galaxy2) {
                    int pathLength = shortestPath(universe, galaxyNumbers.get(galaxy1), galaxyNumbers.get(galaxy2));
                    sumOfShortestPaths += pathLength;
                }
            }
        }

        return sumOfShortestPaths;
    }
}
