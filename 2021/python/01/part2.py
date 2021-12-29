from importlib import resources
from itertools import islice


def test_data():
    return [199, 200, 208, 210, 200, 207, 240, 269, 260, 263]

def read_data(input_filename):
    text_input = resources.open_text('resources', input_filename)
    data = [int(line.strip()) for line in text_input]
    return data

def solve(data):
    count = 0
    prev_sum = -1 

    for window in sliding_window(data, 3):
        cur_sum = sum(window)
        if prev_sum == -1:
            prev_sum = cur_sum
            continue
        if cur_sum > prev_sum:
            count += 1
        prev_sum = cur_sum
    return count


def sliding_window(seq, window_size):
    end = window_size
    for i in range(len(seq) - (window_size - 1)):
        result = list(islice(seq, i, end))
        end += 1
        yield result


if __name__ == "__main__":
    data = read_data('input')
    result = part2(data)
    print(result)
