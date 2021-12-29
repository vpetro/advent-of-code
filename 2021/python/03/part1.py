from importlib import resources
from collections import defaultdict

def read_data(input_filename):
    text_input = resources.open_text('resources', input_filename)
    return text_input


def solve(data):
    result = defaultdict(dict)
    for line in data:
        for i, value in enumerate(line.strip()):
            result[i][value] = result[i].get(value, 0) + 1
            zero = result[i].get('0', 0)
            one = result[i].get('1', 0)
            result[i].update({
                'm': 0 if zero > one else 1,
                'l': 1 if zero > one else 0
            })

    gamma = ""
    epsilon = ""

    for key in sorted(result.keys()):
        gamma += str(result[key]['m'])
        epsilon += str(result[key]['l'])

    return int(gamma, 2) * int(epsilon, 2)



            

if __name__ == "__main__":
    data = read_data("input")
    result = solve(data)
    print(result)
