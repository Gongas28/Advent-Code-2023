def count_arrangements(row, groups):
    if not row:
        return 1
    if row[0] == '#':
        return count_arrangements(row[1:], groups[1:])
    if row[0] == '.':
        return count_arrangements(row[1:], groups)
    if row[0] == '?':
        return count_arrangements(row.replace('?', '#', 1), groups[1:]) + count_arrangements(row.replace('?', '.', 1), groups)

def solve_puzzle(input_lines):
    total_arrangements = 0
    for line in input_lines:
        row, groups = line.strip().split()
        groups = list(map(int, groups.split(',')))
        total_arrangements += count_arrangements(row, groups)
    return total_arrangements

input_lines = [
    "???.### 1,1,3",
    ".??..??...?##. 1,1,3",
    "?#?#?#?#?#?#?#? 1,3,1,6",
    "????.#...#... 4,1,1",
    "????.######..#####. 1,6,5",
    "?###???????? 3,2,1"
]

result = solve_puzzle(input_lines)
print("Total arrangements:", result)
