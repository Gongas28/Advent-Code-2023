import java.util.Scanner;

public class ElfGarden {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the input map
        int rows = 11; // Number of rows in the example map
        int cols = 11; // Number of columns in the example map
        char[][] map = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            map[i] = line.toCharArray();
        }

        // Find the starting position
        int startRow = -1, startCol = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }

        // Initialize the count of reachable garden plots
        int[] count = new int[]{0};

        // Solve the problem
        countGardenPlots(map, startRow, startCol, 64, count);

        // Print the result
        System.out.println("Number of garden plots reachable in exactly 64 steps: " + count[0]);
    }

    private static void countGardenPlots(char[][] map, int row, int col, int steps, int[] count) {
        // Base case: If steps are exhausted, increment the count
        if (steps == 0) {
            count[0]++;
            return;
        }

        // Mark the current position as visited
        char original = map[row][col];
        map[row][col] = 'X'; // 'X' denotes visited

        // Explore in all four directions
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];

            // Check if the new position is a valid garden plot
            if (isValid(map, newRow, newCol)) {
                // Recursively explore the new position with reduced steps
                countGardenPlots(map, newRow, newCol, steps - 1, count);
            }
        }

        // Restore the original state
        map[row][col] = original;
    }

    private static boolean isValid(char[][] map, int row, int col) {
        int rows = map.length;
        int cols = map[0].length;
        return row >= 0 && row < rows && col >= 0 && col < cols && map[row][col] == '.';
    }
}
