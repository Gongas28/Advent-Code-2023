#include <stdio.h>
#include <string.h>

#define MAX_ROWS 10
#define MAX_COLS 10

int findLineOfReflection(char pattern[MAX_ROWS][MAX_COLS], int rows, int cols) {
    int sum = 0;

    for (int col = 0; col < cols / 2; col++) {
        int reflectedCol = cols - col - 1;
        int colMatches = 1;

        for (int row = 0; row < rows; row++) {
            if (pattern[row][col] != pattern[row][reflectedCol]) {
                colMatches = 0;
                break;
            }
        }

        if (colMatches) {
            sum += col;
            break;
        }
    }

    for (int row = 0; row < rows / 2; row++) {
        int reflectedRow = rows - row - 1;
        int rowMatches = 1;

        for (int col = 0; col < cols; col++) {
            if (pattern[row][col] != pattern[reflectedRow][col]) {
                rowMatches = 0;
                break;
            }
        }

        if (rowMatches) {
            sum += 100 * row;
            break;
        }
    }

    return sum;
}

int main() {
    char pattern[MAX_ROWS][MAX_COLS];
    int rows, cols;

    printf("Enter the number of rows and columns: ");
    scanf("%d %d", &rows, &cols);

    printf("Enter the patterns:\n");
    for (int i = 0; i < rows; i++) {
        scanf("%s", pattern[i]);
    }

    int result = findLineOfReflection(pattern, rows, cols);

    printf("The line of reflection sum is: %d\n", result);

    return 0;
}
