#include <iostream>
#include <vector>

// Function to calculate the total load on the north support beams
int calculateTotalLoad(const std::vector<std::string>& platform) {
    int totalLoad = 0;
    int rows = platform.size();
    int cols = platform[0].size();

    for (int col = 0; col < cols; ++col) {
        for (int row = rows - 1; row >= 0; --row) {
            if (platform[row][col] == 'O') {
                totalLoad += row + 1; // Add the load caused by the rounded rock
                break; // Move to the next column after handling this rock
            }
        }
    }

    return totalLoad;
}

// Function to tilt the platform to the north
void tiltToNorth(std::vector<std::string>& platform) {
    int rows = platform.size();
    int cols = platform[0].size();

    for (int col = 0; col < cols; ++col) {
        int emptyRow = -1; // Row with an empty space

        // Find the first empty space or cube-shaped rock in the column
        for (int row = 0; row < rows; ++row) {
            if (platform[row][col] == '.') {
                emptyRow = row;
                break;
            } else if (platform[row][col] == '#') {
                break;
            }
        }

        // Move all rounded rocks to the top of the column
        for (int row = emptyRow + 1; row < rows; ++row) {
            if (platform[row][col] == 'O') {
                std::swap(platform[row][col], platform[emptyRow][col]);
                ++emptyRow;
            }
        }
    }
}

int main() {
    // Example platform
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

    // Tilt the platform to the north
    tiltToNorth(platform);

    // Calculate and print the total load on the north support beams
    int totalLoad = calculateTotalLoad(platform);
    std::cout << "Total load on the north support beams: " << totalLoad << std::endl;

    return 0;
}
