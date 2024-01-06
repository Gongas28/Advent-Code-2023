import java.util.HashSet;
import java.util.Set;

public class LongestHike {
    public static void main(String[] args) {
        String[] hikingTrails = {
                "#.#####################",
                "#.......#########...###",
                "#######.#########.#.###",
                "###.....#.>.>.###.#.###",
                "###v#####.#v#.###.#.###",
                "###.>...#.#.#.....#...#",
                "###v###.#.#.#########.#",
                "###...#.#.#.......#...#",
                "#####.#.#.#######.#.###",
                "#.....#.#.#.......#...#",
                "#.#####.#.#.#########v#",
                "#.#...#...#...###...>.#",
                "#.#.#v#######v###.###v#",
                "#...#.>.#...>.>.#.###.#",
                "#####v#.#.###v#.#.###.#",
                "#.....#...#...#.#.#...#",
                "#.#########.###.#.#.###",
                "#...###...#...#...#.###",
                "###.###.#.###v#####v###",
                "#...#...#.#.>.>.#.>.###",
                "#.###.###.#.###.#.#v###",
                "#.....###...###...#...#",
                "#####################.#"
        };

        int longestHike = findLongestHike(hikingTrails);
        System.out.println("The longest hike is " + longestHike + " steps long.");
    }
  private static int findLongestHike(String[] hikingTrails) {
        int maxSteps = 0;

        for (int i = 0; i < hikingTrails[0].length(); i++) {
            if (hikingTrails[0].charAt(i) == '.') {
                Set<String> visited = new HashSet<>();
                visited.add("0," + i);
                int steps = dfs(hikingTrails, visited, 0, i);
                maxSteps = Math.max(maxSteps, steps);
            }
        }

        return maxSteps;
    }

    private static int dfs(String[] hikingTrails, Set<String> visited, int row, int col) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int maxSteps = 0;

        for (int dir = 0; dir < 4; dir++) {
            int newRow = row + dr[dir];
            int newCol = col + dc[dir];

            if (newRow >= 0 && newRow < hikingTrails.length && newCol >= 0 && newCol < hikingTrails[0].length()) {
                char currentTile = hikingTrails[newRow].charAt(newCol);
                if (currentTile == '.' && !visited.contains(newRow + "," + newCol)) {
                    visited.add(newRow + "," + newCol);
                    int steps = 1 + dfs(hikingTrails, visited, newRow, newCol);
                    maxSteps = Math.max(maxSteps, steps);
                    visited.remove(newRow + "," + newCol);
                }
            }
        }

        return maxSteps;
    }
}
