import java.util.Scanner;

public class ElfGarden {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = 11; 
        int cols = 11; 
        char[][] map = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            map[i] = line.toCharArray();
        }

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

        int[] count = new int[]{0};

        countGardenPlots(map, startRow, startCol, 64, count);

        System.out.println("Number of garden plots reachable in exactly 64 steps: " + count[0]);
    }

    private static void countGardenPlots(char[][] map, int row, int col, int steps, int[] count) {
        if (steps == 0) {
            count[0]++;
            return;
        }

        char original = map[row][col];
        map[row][col] = 'X';

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];

            if (isValid(map, newRow, newCol)) {
                countGardenPlots(map, newRow, newCol, steps - 1, count);
            }
        }

        map[row][col] = original;
    }

    private static boolean isValid(char[][] map, int row, int col) {
        int rows = map.length;
        int cols = map[0].length;
        return row >= 0 && row < rows && col >= 0 && col < cols && map[row][col] == '.';
    }
}
