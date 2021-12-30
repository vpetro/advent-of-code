from importlib import resources

def read_data(input_filename: str):
    fh = resources.open_text('resources', input_filename)
    data = []
    for line in fh.readlines():
        data.extend(list(map(int, line.strip().split(','))))
    return data


def solve(data: list[int], days: int):
    for k in range(days):
        for i in range(len(data)):
            if data[i] == 0:
                data[i] = 6
                data.append(8)
            else:
                data[i] -= 1
    return len(data)

if __name__ == "__main__":
    data = read_data("input")
    result = solve(data, 80)
    print(f'{result}')


