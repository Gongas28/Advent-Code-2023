def convert(seed, seed_to_soil, soil_to_fertilizer, fertilizer_to_water, water_to_light, light_to_temperature, temperature_to_humidity, humidity_to_location):
    soil = seed
    fertilizer = soil
    water = fertilizer
    light = water
    temperature = light
    humidity = temperature
    location = humidity

    for entry in seed_to_soil:
        dest_range, src_range, length = entry
        if seed in range(src_range, src_range + length):
            soil = dest_range + (seed - src_range)

    for entry in soil_to_fertilizer:
        dest_range, src_range, length = entry
        if soil in range(src_range, src_range + length):
            fertilizer = dest_range + (soil - src_range)

    for entry in fertilizer_to_water:
        dest_range, src_range, length = entry
        if fertilizer in range(src_range, src_range + length):
            water = dest_range + (fertilizer - src_range)

    for entry in water_to_light:
        dest_range, src_range, length = entry
        if water in range(src_range, src_range + length):
            light = dest_range + (water - src_range)

    for entry in light_to_temperature:
        dest_range, src_range, length = entry
        if light in range(src_range, src_range + length):
            temperature = dest_range + (light - src_range)

    for entry in temperature_to_humidity:
        dest_range, src_range, length = entry
        if temperature in range(src_range, src_range + length):
            humidity = dest_range + (temperature - src_range)

    for entry in humidity_to_location:
        dest_range, src_range, length = entry
        if humidity in range(src_range, src_range + length):
            location = dest_range + (humidity - src_range)

    return location

seed_to_soil = [(50, 98, 2), (52, 50, 48)]
soil_to_fertilizer = [(0, 15, 37), (37, 52, 2), (39, 0, 15)]
fertilizer_to_water = [(49, 53, 8), (0, 11, 42), (42, 0, 7), (57, 7, 4)]
water_to_light = [(88, 18, 7), (18, 25, 70)]
light_to_temperature = [(45, 77, 23), (81, 45, 19), (68, 64, 13)]
temperature_to_humidity = [(0, 69, 1), (1, 0, 69)]
humidity_to_location = [(60, 56, 37), (56, 93, 4)]

seeds = [79, 14, 55, 13]

lowest_location = float('inf')
for seed in seeds:
    location = convert(seed, seed_to_soil, soil_to_fertilizer, fertilizer_to_water, water_to_light, light_to_temperature, temperature_to_humidity, humidity_to_location)
    lowest_location = min(lowest_location, location)

print("O número de localização mais baixo é:", lowest_location)
