def hash_algorithm(s):
    current_value = 0
    for char in s:
        current_value += ord(char)
        current_value *= 17
        current_value %= 256
    return current_value

def sum_of_hashes(init_sequence):
    steps = init_sequence.split(',')
    result_sum = 0
    for step in steps:
        key, value = step.split('=')
        result_sum += hash_algorithm(value)
    return result_sum

init_sequence = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"

result_sum = sum_of_hashes(init_sequence)

print(result_sum)
