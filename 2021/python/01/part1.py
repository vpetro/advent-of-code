from importlib import resources
from functools import reduce


def test_data():
    return [199, 200, 208, 210, 200, 207, 240, 269, 260, 263]


def read_data(input_filename):
    text_input = resources.open_text("resources", input_filename)
    data = [int(line.strip()) for line in text_input]
    return data


def solve(data):
    return reduce(lambda i, j: i + j, (i < j for (i, j) in zip(data, data[1:])))


if __name__ == "__main__":
    test_result = solve(test_data())
    print(test_result)
    data = read_data("input")
    result = solve(data)
    print(result)
