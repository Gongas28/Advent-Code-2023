def min_heat_loss(grid):
    rows, cols = len(grid), len(grid[0])
    
    dp = [[float('inf')] * cols for _ in range(rows)]
    on
    dp[0][0] = int(grid[0][0])
    
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    
    for r in range(rows):
        for c in range(cols):
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                
                if 0 <= nr < rows and 0 <= nc < cols:
                    new_heat_loss = dp[r][c] + int(grid[nr][nc])
                    
                    dp[nr][nc] = min(dp[nr][nc], new_heat_loss)
    
    return dp[rows - 1][cols - 1]

grid = [
    "2413432311323",
    "3215453535623",
    "3255245654254",
    "3446585845452",
    "4546657867536",
    "1438598798454",
    "4457876987766",
    "3637877979653",
    "4654967986887",
    "4564679986453",
    "1224686865563",
    "2546548887735",
    "4322674655533",
]

result = min_heat_loss(grid)
print(result)
