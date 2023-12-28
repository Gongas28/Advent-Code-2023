def extrapolate_oasis_report(oasis_report):
    def get_next_sequence(sequence):
        return [sequence[i] + sequence[i + 1] for i in range(len(sequence) - 1)]

    extrapolated_values = []

    for history in oasis_report:
        while any(history):
            history = get_next_sequence(history)

        extrapolated_values.append(history[-1])

    return sum(extrapolated_values)

oasis_report = [
    [0, 3, 6, 9, 12, 15],
    [1, 3, 6, 10, 15, 21],
    [10, 13, 16, 21, 30, 45],
]

result = extrapolate_oasis_report(oasis_report)
print(result)
