def is_possible_game(subsets):
    red_count, green_count, blue_count = 12, 13, 14
    
    for subset in subsets:
        subset_counts = {'red': 0, 'green': 0, 'blue': 0}
        
        for item in subset:
            color, count = item.split()
            subset_counts[color] += int(count)
        
        if (
            subset_counts['red'] > red_count or
            subset_counts['green'] > green_count or
            subset_counts['blue'] > blue_count
        ):
            return False
    
    return True

def possible_games_sum(input_data):
    possible_games = []
    
    for line in input_data:
        game_id, *revealed_subsets = line.split(': ')[1].split('; ')
        revealed_subsets = [subset.split(', ') for subset in revealed_subsets]
        
        if is_possible_game(revealed_subsets):
            possible_games.append(int(game_id))
    
    return sum(possible_games)

input_data = [
    "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
    "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
    "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
    "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
    "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
]

result = possible_games_sum(input_data)
print(result)
