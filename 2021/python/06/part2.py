from importlib import resources
from collections import defaultdict

def read_data(input_filename: str):
    fh = resources.open_text('resources', input_filename)
    data = defaultdict(int)
    for line in fh.readlines():
        for i in line.split(','):
            index = int(i)
            data[index] += 1
    return data

def solve(data: list[int], days: int):
    for k in range(days):
        new_data = defaultdict(int)
        for age, count in data.items():
            if age == 0:
                new_data[8] += count
                new_data[6] += count
            else:
                new_data[age-1] += count
        data = new_data
    return sum(data.values())

if __name__ == "__main__":
    data = read_data("input")
    result = solve(data, 256)
    print(f'{result}')
