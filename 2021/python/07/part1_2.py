from importlib import resources
from collections import defaultdict

def read_data(input_filename: str):
    fh = resources.open_text('resources', input_filename)
    data = []
    for line in fh.readlines():
        data.extend([int(i) for i in line.strip().split(',')])
    return data


def solve(data, part):
    results = defaultdict(list)
    for i in range(min(data), max(data)):
        if part == 1:
            r = sum([abs(pos - i) for pos in data])
        if part == 2:
            r = sum([(abs(pos - i) * (1 + abs(pos - i)))/2 for pos in data])
        results[r].append(i)
    cost = min(results)
    aliagn_on = results[cost] 
    return cost, aliagn_on

if __name__ == "__main__":
    data = read_data("input")
    result = solve(data,2)
    print(f'{result}')
