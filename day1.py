def sum_calibration_values(calibration_document):
    total_sum = 0

    for line in calibration_document:

        first_digit = int(line[0])
        last_digit = int(line[-1])

        calibration_value = first_digit * 10 + last_digit

        total_sum += calibration_value

    return total_sum

calibration_document = [
    "1abc2",
    "pqr3stu8vwx",
    "a1b2c3d4e5f",
    "treb7uchet"
]

result = sum_calibration_values(calibration_document)

print(result)
