def simulate_falling(snapshot):
    bricks = [list(map(int, line.split('~'))) for line in snapshot]

    # Sort bricks based on their z-coordinate in descending order
    bricks.sort(key=lambda x: max(x[2], x[5]), reverse=True)

    # Dictionary to store the height each column reaches
    column_heights = {}

    for brick in bricks:
        x1, y1, z1, x2, y2, z2 = brick

        # Calculate the height each end of the brick reaches
        height1 = max(z1, z2)
        height2 = min(z1, z2)

        # Find the maximum height in the column
        max_height = max(column_heights.get((x1, y1), 0), column_heights.get((x2, y2), 0))

        # Update the column height based on the brick's height
        column_heights[(x1, y1)] = max_height + height1 - height2
        column_heights[(x2, y2)] = max_height + height1 - height2

    return column_heights

def can_disintegrate(brick, column_heights):
    x1, y1, _, x2, y2, _ = brick

    # Check if removing the brick would not affect the stability of other bricks
    return column_heights.get((x1, y1), 0) == 0 and column_heights.get((x2, y2), 0) == 0

def count_safe_disintegrations(snapshot):
    column_heights = simulate_falling(snapshot)
    bricks = [list(map(int, line.split('~'))) for line in snapshot]
    safe_disintegrations = 0

    for brick in bricks:
        if can_disintegrate(brick, column_heights):
            safe_disintegrations += 1

    return safe_disintegrations

# Example usage with the provided input
snapshot_input = [
    "1,0,1~1,2,1",
    "0,0,2~2,0,2",
    "0,2,3~2,2,3",
    "0,0,4~0,2,4",
    "2,0,5~2,2,5",
    "0,1,6~2,1,6",
    "1,1,8~1,1,9"
]

result = count_safe_disintegrations(snapshot_input)
print(f"Number of safe disintegrations: {result}")
