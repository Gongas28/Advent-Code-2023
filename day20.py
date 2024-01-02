def process_pulse(module_config, pulses):
    flip_flop_states = {}
    conjunction_states = {}
    low_pulse_count = 0
    high_pulse_count = 0

    for pulse in pulses:
        module, pulse_type = pulse.split(' -')
        pulse_type = pulse_type.strip()

        if module.startswith('%'):
            if pulse_type == 'low':
                flip_flop_states[module] = not flip_flop_states.get(module, False)
                if not flip_flop_states[module]:
                    high_pulse_count += 1

        elif module.startswith('&'):
            input_module = module[1:]
            conjunction_states[input_module] = pulse_type

            if all(state == 'high' for state in conjunction_states.values()):
                low_pulse_count += 1
            else:
                high_pulse_count += 1

        elif module == 'broadcaster':
            low_pulse_count += 1
            high_pulse_count += 1

        else:
            if pulse_type == 'low':
                low_pulse_count += 1
            else:
                high_pulse_count += 1

    return low_pulse_count, high_pulse_count

def simulate_button_pushes(module_config, num_pushes):
    pulses = ['button -low-> broadcaster']
    total_low_pulses = 0
    total_high_pulses = 0

    for _ in range(num_pushes):
        for pulse in pulses:
            source_module, destinations = pulse.split(' -> ')
            destination_modules = destinations.split(', ')
            total_low, total_high = process_pulse(module_config, [f'{module} -low->' for module in destination_modules])
            total_low_pulses += total_low
            total_high_pulses += total_high

        pulses = []
        for destination_module in destination_modules:
            pulses.extend([f'{destination_module} -{pulse_type}->' for pulse_type in ['low', 'high']])

    return total_low_pulses, total_high_pulses

module_config = [
    'broadcaster -> a, b, c',
    '%a -> b',
    '%b -> c',
    '%c -> inv',
    '&inv -> a'
]

num_pushes = 1000

result = simulate_button_pushes(module_config, num_pushes)

product = result[0] * result[1]

print(f'Total low pulses: {result[0]}')
print(f'Total high pulses: {result[1]}')
print(f'Product of total low pulses and total high pulses: {product}')
