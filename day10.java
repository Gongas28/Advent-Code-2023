import java.util.ArrayDeque;
import java.util.Queue;

public class PipeLoop {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) {
        String[] sketch = {
                ".....",
                ".F-7.",
                ".|. .",
                ".L-J.",
                "....."
        };

        int steps = findFarthestPoint(sketch);
        System.out.println("Steps to the farthest point: " + steps);
    }

    public static int findFarthestPoint(String[] sketch) {
        int rows = sketch.length;
        int cols = sketch[0].length();

        int startX = -1, startY = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (sketch[i].charAt(j) == 'S') {
                    startX = i;
                    startY = j;
                    break;
                }
            }
        }

        int[][] distances = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distances[i][j] = -1;
            }
        }

        bfs(sketch, startX, startY, distances);

        int maxDistance = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (distances[i][j] > maxDistance) {
                    maxDistance = distances[i][j];
                }
            }
        }

        return maxDistance;
    }

    private static void bfs(String[] sketch, int startX, int startY, int[][] distances) {
        int rows = sketch.length;
        int cols = sketch[0].length();
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{startX, startY});
        distances[startX][startY] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int direction = 0; direction < 4; direction++) {
                int newX = x + dx[direction];
                int newY = y + dy[direction];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && distances[newX][newY] == -1) {
                    char pipe = sketch[newX].charAt(newY);
                    if (isValidMove(pipe, direction)) {
                        distances[newX][newY] = distances[x][y] + 1;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
        }
    }

    private static boolean isValidMove(char pipe, int direction) {
        switch (pipe) {
            case 'S':
            case 'F':
                return direction == 1 || direction == 2;
            case '7':
                return direction == 2 || direction == 3;
            case 'L':
                return direction == 0 || direction == 1;
            case 'J':
                return direction == 0 || direction == 3;
            case '-':
                return direction == 1 || direction == 3;
            case '|':
                return direction == 0 || direction == 2;
            default:
                return false;
        }
    }
}
