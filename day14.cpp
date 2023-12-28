#include <iostream>
#include <vector>

int calculateTotalLoad(const std::vector<std::string>& platform) {
    int totalLoad = 0;
    int rows = platform.size();
    int cols = platform[0].size();

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

void tiltToNorth(std::vector<std::string>& platform) {
    int rows = platform.size();
    int cols = platform[0].size();

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
                std::swap(platform[row][col], platform[emptyRow][col]);
                ++emptyRow;
            }
        }
    }
}

int main() {
    std::vector<std::string> platform = {
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
    std::cout << "Total load on the north support beams: " << totalLoad << std::endl;

    return 0;
}
