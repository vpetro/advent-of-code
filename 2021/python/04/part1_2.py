from importlib import resources
from itertools import islice

def read_data(input_filename: str):
    text_input = resources.open_text('resources', input_filename)

    numbers = [int(i) for i in text_input.readline().split(',')]

    text_input.readline()

    boards = []
    board = []

    for line in text_input.readlines():
        if line == '\n':
            boards.append(board)
            board = []
            continue

        board.extend(
            [int(number.strip()) for number in line.split(' ') if number.strip() != '' ])

    boards.append(board)
    return numbers, boards

def iterate_boards(numbers, boards, won_boards, not_won_boards):
    if not boards:
        return won_boards, not_won_boards

    b, bs = boards[0], boards[1:]
    r = check(numbers, b)
    if r != -1:
        won_boards.append((r, b))
    else:
        not_won_boards.append(b)
    return iterate_boards(numbers, bs, won_boards, not_won_boards)

def solve(numbers: list[int], data: list[int]):
    results = []
    for i in range(len(numbers)):
        ns = numbers[:i]
        won_boards, not_won_boards = iterate_boards(ns, data, [], [])
        results.extend(won_boards)
        if len(not_won_boards) == 0:
            return results
        data = not_won_boards
    return results

def check(numbers: list[int], table: list[int]):
    for i in range(5):
        row = list(islice(table, 5*i, 5*(i+1)))
        col = list(islice(table, i, 25, 5))
        if all((i in numbers for i in row)) or all((i in numbers for i in col)):
            return sum([i for i in table if i not in numbers]) * numbers[-1]
    return -1


if __name__ == "__main__":
    numbers, boards = read_data("input")
    result = solve(numbers, boards)
    print('part1 result: {}'.format(result[0][0]))
    print('part2 result: {}'.format(result[-1][0]))
