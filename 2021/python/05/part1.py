from importlib import resources
from collections import defaultdict

def read_data(input_filename: str):
    fh = resources.open_text('resources', input_filename)
    lines = []
    for line in fh.readlines():
        start, finish = line.split(' -> ')
        x1, y1 = [int(i) for i in start.split(',')]
        x2, y2 = [int(i) for i in finish.split(',')]
        lines.append([x1, y1, x2, y2])
    return lines

def get_change(i, j):
    mod = 0
    if i > j:
        mod = -1
    elif i < j:
        mod = 1
    return mod, abs(i - j)

def generate_diagonal(x1, y1, x2, y2, predicate):
    xmod, xdiff = get_change(x1, x2)
    ymod, ydiff = get_change(y1, y2)

    if not predicate(xdiff, ydiff):
        return []

    coords = [(x1 + (xmod*i), y1 + (ymod*i)) for i in range(1+(max(xdiff, ydiff)))]
    return coords

def solve(data: list[list[int]], predicate):
    grid = defaultdict(int)
    for (x1, y1, x2, y2) in data:
        for pair in generate_diagonal(x1, y1, x2, y2, predicate):
            grid[pair] += 1

    return sum([value >= 2 for _, value in grid.items()])

if __name__ == "__main__":
    data = read_data("input")
    result1 = solve(data, lambda x,y: 0 in (x,y))
    result2 = solve(data, lambda x,y: True)
    print(f'part 1 result: {result1}')
    print(f'part 2 result: {result2}')
