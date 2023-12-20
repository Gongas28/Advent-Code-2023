def sum_calibration_values(calibration_document):
    total_sum = 0

    for line in calibration_document:
        # Extract the first and last digits from each line
        first_digit = int(line[0])
        last_digit = int(line[-1])

        # Combine the digits to form a two-digit number
        calibration_value = first_digit * 10 + last_digit

        # Add the calibration value to the total sum
        total_sum += calibration_value

    return total_sum

# Example calibration document
calibration_document = [
    "1abc2",
    "pqr3stu8vwx",
    "a1b2c3d4e5f",
    "treb7uchet"
]

# Calculate the sum of calibration values
result = sum_calibration_values(calibration_document)

# Print the result
print(result)
