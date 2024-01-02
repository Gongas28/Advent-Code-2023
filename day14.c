#include <stdio.h>
#include <stdlib.h>

#define ROWS 10
#define COLS 10

int calculateTotalLoad(char platform[ROWS][COLS]) {
    int totalLoad = 0;
    int rows = ROWS;
    int cols = COLS;

    for (int col = 0; col < cols; ++col) {
        for (int row = rows - 1; row >= 0; --row) {
            if (platform[row][col] == 'O') {
                totalLoad += row + 1;
                break;
            }
        }
    }

    return totalLoad;
}

void tiltToNorth(char platform[ROWS][COLS]) {
    int rows = ROWS;
    int cols = COLS;

    for (int col = 0; col < cols; ++col) {
        int emptyRow = -1;

        for (int row = 0; row < rows; ++row) {
            if (platform[row][col] == '.') {
                emptyRow = row;
                break;
            } else if (platform[row][col] == '#') {
                break;
            }
        }

        for (int row = emptyRow + 1; row < rows; ++row) {
            if (platform[row][col] == 'O') {
                char temp = platform[row][col];
                platform[row][col] = '.';
                platform[emptyRow][col] = temp;
                ++emptyRow;
            }
        }
    }
}

int main() {
    char platform[ROWS][COLS] = {
        "O....#....",
        "O.OO#....#",
        ".....##...",
        "OO.#O....O",
        ".O.....O#.",
        "O.#..O.#.#",
        "..O..#O..O",
        ".......O..",
        "#....###..",
        "#OO..#...."
    };

    tiltToNorth(platform);

    int totalLoad = calculateTotalLoad(platform);
    printf("Total load on the north support beams: %d\n", totalLoad);

    return 0;
}
