from importlib import resources
from collections import defaultdict

def read_data(input_filename):
    text_input = resources.open_text('resources', input_filename)
    return [line.strip() for line in text_input]


def most_least(data):
    result = defaultdict(dict)
    for line in data:
        for i, value in enumerate(line):
            result[i][value] = result[i].get(value, 0) + 1
            zero = result[i].get('0', 0)
            one = result[i].get('1', 0)
            result[i].update({
                'm': 0 if zero > one else 1,
                'l': 1 if zero > one else 0
                })

    return result

def filter_value_oxygen(most_least_row_data):
    zero = most_least_row_data['0']
    one = most_least_row_data['1']
    if zero > one:
        return '0'
    return '1'

def filter_value_co2(most_least_row_data):
    zero = most_least_row_data['0']
    one = most_least_row_data['1']
    if zero > one:
        return '1'
    elif one > zero:
        return '0'
    return '0'

def filtering(filter_func, numbers, most_least_data):

    def helper(col: int, most_frequent: str, numbers: list[str], most_least_data: dict[int, dict]):
        filtered = [number for number in numbers if number[col] == most_frequent]
        most_least_filtered = most_least(filtered)
        col = col + 1

        if len(filtered) == 1:
            return filtered

        return helper(col, filter_func(most_least_filtered[col]), filtered, most_least_filtered)

    result = helper(0, filter_func(most_least_data[0]), numbers, most_least_data)
    return int(result[0], 2)


if __name__ == "__main__":
    numbers = read_data("input")
    most_least_data = most_least(numbers)
    oxygen_result = filtering(filter_value_oxygen, numbers, most_least_data)
    co2_result = filtering(filter_value_co2, numbers, most_least_data)
    print("oxygen result: {}".format(oxygen_result))
    print("CO2 result: {}".format(co2_result))
    print('final result: {}'.format(oxygen_result * co2_result))
