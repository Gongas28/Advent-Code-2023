import java.util.Arrays;

public class BeamSimulation {
    public static void main(String[] args) {
        String[] contraption = {
            ".|...\\....",
            "|.-.\\.....",
            ".....|-...",
            "........|.",
            "..........",
            ".........\\",
            "..../.\\\\..",
            ".-.-/..|..",
            ".|....-|.\\",
            "..//.|...."
        };

        int energizedTiles = simulateBeam(contraption);

        System.out.println("Number of energized tiles: " + energizedTiles);
    }

    private static int simulateBeam(String[] contraption) {
        int rows = contraption.length;
        int cols = contraption[0].length();
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = contraption[i].toCharArray();
        }

        int energizedTiles = 0;
        int x = 0, y = 0; 
        int dx = 1, dy = 0; 

        while (x >= 0 && x < cols && y >= 0 && y < rows) {
            char currentTile = grid[y][x];

            if (currentTile == '.') {
                energizedTiles++;
            } else if (currentTile == '/' || currentTile == '\\') {
                int temp = dx;
                dx = (currentTile == '/') ? -dy : dy;
                dy = (currentTile == '/') ? -temp : temp;
            } else if (currentTile == '|' || currentTile == '-') {
                int tempX = x + dx;
                int tempY = y + dy;

                energizedTiles += (tempX >= 0 && tempX < cols && tempY >= 0 && tempY < rows && grid[tempY][tempX] == '.') ? 1 : 0;
            }

            x += dx;
            y += dy;
        }

        return energizedTiles;
    }
}
