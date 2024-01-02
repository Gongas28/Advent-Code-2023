def calculate_lava_volume(dig_plan):
    grid = [['.' for _ in range(1000)] for _ in range(1000)]

    x, y = 500, 500

    for instruction in dig_plan:
        direction, distance, color = instruction.split()
        distance = int(distance)

        for _ in range(distance):
            if direction == 'R':
                x += 1
            elif direction == 'L':
                x -= 1
            elif direction == 'U':
                y += 1
            elif direction == 'D':
                y -= 1

            grid[y][x] = '#'

    lava_volume = sum(row.count('#') for row in grid)
    
    return lava_volume

dig_plan = [
    "R 6 (#70c710)",
    "D 5 (#0dc571)",
    "L 2 (#5713f0)",
    "D 2 (#d2c081)",
    "R 2 (#59c680)",
    "D 2 (#411b91)",
    "L 5 (#8ceee2)",
    "U 2 (#caa173)",
    "L 1 (#1b58a2)",
    "U 2 (#caa171)",
    "R 2 (#7807d2)",
    "U 3 (#a77fa3)",
    "L 2 (#015232)",
    "U 2 (#7a21e3)"
]

result = calculate_lava_volume(dig_plan)
print("The lagoon can hold {} cubic meters of lava.".format(result))
