def is_valid_coordinate(x, y, rows, cols):
    return 0 <= x < rows and 0 <= y < cols

def get_adjacent_numbers(grid, x, y):
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0), (1, 1), (1, -1), (-1, 1), (-1, -1)]
    rows, cols = len(grid), len(grid[0])
    
    adjacent_numbers = []
    for dx, dy in directions:
        nx, ny = x + dx, y + dy
        if is_valid_coordinate(nx, ny, rows, cols) and grid[nx][ny].isdigit():
            adjacent_numbers.append(int(grid[nx][ny]))
    
    return adjacent_numbers

def sum_of_part_numbers(engine_schematic):
    grid = [list(line) for line in engine_schematic.split('\n') if line]
    rows, cols = len(grid), len(grid[0])
    
    total_sum = 0
    for i in range(rows):
        for j in range(cols):
            if grid[i][j].isdigit():
                adjacent_numbers = get_adjacent_numbers(grid, i, j)
                total_sum += sum(adjacent_numbers)
    
    return total_sum

engine_schematic = """
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
"""

result = sum_of_part_numbers(engine_schematic)
print(result)
